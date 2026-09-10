package com.example.ejemplo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejemplo2.data.AuthRepositoryImpl
import com.example.ejemplo2.data.local.AppDatabase
import com.example.ejemplo2.domain.RegisterUseCase
import com.example.ejemplo2.domain.SignInUseCase
import com.example.ejemplo2.ui.dashboard.DashboardScreen
import com.example.ejemplo2.ui.register.RegisterScreen
import com.example.ejemplo2.ui.register.RegisterViewModel
import com.example.ejemplo2.ui.signin.SignInScreen
import com.example.ejemplo2.ui.signin.SignInViewModel
import com.example.ejemplo2.ui.theme.Ejemplo2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplo2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val database = remember {
                        AppDatabase.getDatabase(applicationContext)
                    }
                    val userDao = remember {
                        database.userDao()
                    }
                    val repository = remember {
                        AuthRepositoryImpl(userDao)
                    }
                    val signInUseCase = remember {
                        SignInUseCase(repository)
                    }
                    val registerUseCase = remember {
                        RegisterUseCase(repository)
                    }
                    val signInViewModel: SignInViewModel = viewModel(
                        factory = object : ViewModelProvider.Factory { override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                @Suppress("UNCHECKED_CAST")
                                return SignInViewModel(signInUseCase) as T
                            }
                        }
                    )
                    val registerViewModel: RegisterViewModel = viewModel(factory = object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                @Suppress("UNCHECKED_CAST")
                                return RegisterViewModel(registerUseCase) as T
                            }
                        }
                    )
                    var currentScreen by remember {
                        mutableStateOf("login")
                    }
                    when (currentScreen) {
                        "login" -> {
                            SignInScreen(
                                viewModel = signInViewModel,
                                exitoiniciarSesion = {
                                    currentScreen = "dashboard"
                                },
                                registrado = {
                                    registerViewModel.resetearRegistro()
                                    currentScreen = "register"
                                }
                            )
                        }
                        "register" -> {
                            RegisterScreen(
                                viewModel = registerViewModel,
                                onBackToLogin = {
                                    registerViewModel.resetearRegistro()
                                    currentScreen = "login"
                                },
                                onSuccess = {
                                    registerViewModel.resetearRegistro()
                                    signInViewModel.resetearLogin()
                                    currentScreen = "login"
                                }
                            )
                        }
                        "dashboard" -> {
                            DashboardScreen(
                                userName = signInViewModel.uiState.nombreUsuario,
                                onLogout = {
                                    signInViewModel.resetearLogin()
                                    currentScreen = "login"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}