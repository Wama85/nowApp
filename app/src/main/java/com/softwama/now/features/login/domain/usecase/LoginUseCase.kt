package com.softwama.now.features.login.domain.usecase

import com.softwama.now.features.login.domain.model.LoginRequest
import com.softwama.now.features.login.domain.model.LoginResponse
import com.softwama.now.features.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(username: String, password: String): Flow<Result<LoginResponse>> {
        val request = LoginRequest(username, password)
        return loginRepository.login(request)
    }
}