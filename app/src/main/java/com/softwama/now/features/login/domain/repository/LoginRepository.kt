package com.softwama.now.features.login.domain.repository

import com.softwama.now.features.login.domain.model.LoginRequest
import com.softwama.now.features.login.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(request: LoginRequest): Flow<Result<LoginResponse>>
}