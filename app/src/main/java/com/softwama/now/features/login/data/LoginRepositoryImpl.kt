package com.softwama.now.features.login.data

import com.softwama.now.features.login.domain.model.LoginRequest
import com.softwama.now.features.login.domain.model.LoginResponse
import com.softwama.now.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl : LoginRepository {

    override fun login(request: LoginRequest): Flow<Result<LoginResponse>> = flow {
        try {
            // Simulamos un delay de red
            delay(1000)

            // Validación simple
            if (request.mail.isEmpty() || request.password.isEmpty()) {
                emit(Result.failure(Exception("Usuario y contraseña son requeridos")))
                return@flow
            }

            if (request.mail == "correo@mail.com" && request.password == "123") {
                val response = LoginResponse(
                    token = "mock_token_${System.currentTimeMillis()}",
                    userId = "user_123"
                )
                emit(Result.success(response))
            } else {
                emit(Result.failure(Exception("Credenciales inválidas")))
            }
        } catch (e: Exception) {
            emit(Result.failure(Exception(e.message ?: "Error de conexión")))
        }
    }
}