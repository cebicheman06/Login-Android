package com.example.ejemplo2.ui.signin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemplo2.domain.SignInUseCase
import kotlinx.coroutines.launch

class SignInViewModel(private val signInUseCase: SignInUseCase) : ViewModel() {
    var uiState by mutableStateOf(SignInUiState())
        private set
    fun emailCambiado(email: String) {
        uiState = uiState.copy(email = email, error = null)
    }
    fun contrasenaCambiada(contrasena: String) {
        uiState = uiState.copy(contrasena = contrasena, error = null)
    }
    fun iniciarSesion() {
        viewModelScope.launch { uiState = uiState.copy(cargando = true, error = null)
            val result = signInUseCase(uiState.email, uiState.contrasena)
            uiState = if (result.isSuccess) {
                uiState.copy(
                    cargando = false,
                    exitoIniciarSesion = true,
                    nombreUsuario = result.getOrNull().orEmpty())
            } else { uiState.copy(
                    cargando = false,
                    error = result.exceptionOrNull()?.message ?: "Error desconocido")
            }
        }
    }
    fun resetearLogin() {
        uiState = SignInUiState()
    }
}
data class SignInUiState(
    val email: String = "",
    val contrasena: String = "",
    val cargando: Boolean = false,
    val error: String? = null,
    val exitoIniciarSesion: Boolean = false,
    val nombreUsuario: String = ""
)