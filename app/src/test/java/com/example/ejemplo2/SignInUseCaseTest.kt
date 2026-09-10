package com.example.ejemplo2

import com.example.ejemplo2.domain.AuthRepository
import com.example.ejemplo2.domain.SignInUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SignInUseCaseTest {

    private val mockRepository = object : AuthRepository {
        override suspend fun signIn(email: String, password: String): Result<Unit> {
            return if (email == "success@test.com") {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Auth failed"))
            }
        }
    }

    private val signInUseCase = SignInUseCase(mockRepository)

    @Test
    fun `invoke with empty email returns failure`() = runBlocking {
        val result = signInUseCase("", "password")
        assertTrue(result.isFailure)
        assertEquals("Email and password cannot be empty", result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke with valid credentials returns success`() = runBlocking {
        val result = signInUseCase("success@test.com", "any_password")
        assertTrue(result.isSuccess)
    }
}
