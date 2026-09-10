package com.example.ejemplo2.domain

interface AuthRepository {
    suspend fun iniciarSesion(email: String, contrasena: String): Result<String>
    suspend fun registrar(nombre: String, apellido: String, email: String, contrasena: String): Result<Unit>
}
