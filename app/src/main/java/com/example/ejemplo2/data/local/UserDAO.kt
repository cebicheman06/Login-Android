package com.example.ejemplo2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertarUsuario(usuario: UserEntity)
    @Query(
        """
        SELECT * FROM usuarios
        WHERE email = :email
        LIMIT 1
        """
    )
    suspend fun usuariosPorEmail(email: String): UserEntity?
    @Query(
        """
        SELECT * FROM usuarios
        WHERE email = :email
        AND contrasena = :contrasena
        LIMIT 1
        """
    )
    suspend fun login(
        email: String,
        contrasena: String
    ): UserEntity?
}