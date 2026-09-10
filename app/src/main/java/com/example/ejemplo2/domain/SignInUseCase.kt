package com.example.ejemplo2.domain

class SignInUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, contrasena: String): Result<String> {
        if (email.isBlank() || contrasena.isBlank()) {
            return Result.failure(Exception("El correo y la contraseña no pueden estar vacíos"))
        }
        return repository.iniciarSesion(email = email.trim(), contrasena = contrasena)
    }
}