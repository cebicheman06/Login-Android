package com.example.ejemplo2.ui.register
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemplo2.domain.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    var uiState by mutableStateOf(RegisterUiState())
        private set
    fun nombreCambiado(value: String) {
        uiState = uiState.copy(nombre = value, error = null)
    }
    fun apellidoCambiado(value: String) {
        uiState = uiState.copy(apellido= value, error = null)
    }
    fun emailCambiado(value: String) {
        uiState = uiState.copy(email = value, error = null)
    }
    fun onPasswordChange(value: String) {
        uiState = uiState.copy(contrasena = value, error = null)
    }
    fun onConfirmPasswordChange(value: String) {
        uiState = uiState.copy(confirmacionContrasena = value, error = null)
    }
    fun registrar() { viewModelScope.launch {
            uiState = uiState.copy(cargando = true, error = null)
            val resultado = registerUseCase(nombre = uiState.nombre, apellido = uiState.apellido,
                email = uiState.email, contrasena = uiState.contrasena, confirmacionContrasena = uiState.confirmacionContrasena)
            uiState = if (resultado.isSuccess) {
                uiState.copy(cargando = false, exitoIniciarSesion = true)
            } else {
                uiState.copy(cargando = false, error = resultado.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }
    fun resetearRegistro() {
        uiState = RegisterUiState()
    }
}

data class RegisterUiState(
    val nombre: String = "",
    val apellido: String = "",
    val email: String = "",
    val contrasena: String = "",
    val confirmacionContrasena: String = "",
    val cargando: Boolean = false,
    val error: String? = null,
    val exitoIniciarSesion: Boolean = false
)