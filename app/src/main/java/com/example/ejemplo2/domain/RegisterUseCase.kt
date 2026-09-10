package com.example.ejemplo2.domain
class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(nombre: String, apellido: String, email: String, contrasena: String,
        confirmacionContrasena: String): Result<Unit> {
        if (
            nombre.isBlank() || apellido.isBlank() || email.isBlank() || contrasena.isBlank() ||
            confirmacionContrasena.isBlank()) {
            return Result.failure(Exception("Todos los campos son obligatorios"))
        }
        if (contrasena!= confirmacionContrasena) {
            return Result.failure(Exception("Las contraseñas no coinciden"))
        }
        return repository.registrar(nombre = nombre.trim(), apellido = apellido.trim(), email = email.trim(), contrasena= contrasena)
    }
}