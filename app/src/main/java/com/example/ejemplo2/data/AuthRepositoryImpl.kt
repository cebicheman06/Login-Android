package com.example.ejemplo2.data

import com.example.ejemplo2.data.local.UserDao
import com.example.ejemplo2.data.local.UserEntity
import com.example.ejemplo2.domain.AuthRepository

class AuthRepositoryImpl(private val userDao: UserDao) : AuthRepository {
    override suspend fun iniciarSesion(email: String, contrasena: String): Result<String> {
        return try {
            val usuario = userDao.login(email = email.trim(), contrasena = contrasena)
            if (usuario != null) {
                val nombreCompleto = "${usuario.nombre} ${usuario.apellido}".trim()
                Result.success(nombreCompleto)
            } else {
                Result.failure(Exception("Correo o contraseña incorrectos"))
            }
        }
        catch (e: Exception){
            Result.failure(Exception("No se pudo acceder a la base de datos"))
        }
    }
    override suspend fun registrar(nombre: String, apellido: String, email: String, contrasena: String
    ): Result<Unit> {
        val emailLimpio = email.trim()
        val siexisteUsuario = userDao.usuariosPorEmail(emailLimpio)
        if (siexisteUsuario != null) {
            return Result.failure(
                Exception("El correo ya está registrado")
            )
        }
        return try {
            val nuevoUsuario = UserEntity(nombre = nombre.trim(), apellido = apellido.trim(), email = emailLimpio,
                contrasena = contrasena)
            userDao.insertarUsuario(nuevoUsuario)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("No se pudo registrar el usuario"))
        }
    }
}