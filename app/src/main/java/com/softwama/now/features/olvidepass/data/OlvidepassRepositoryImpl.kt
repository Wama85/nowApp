package com.softwama.now.features.olvidepass.data

import com.softwama.now.features.olvidepass.domain.model.OlvidepassModel
import com.softwama.now.features.olvidepass.domain.repository.OlvidepassRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OlvidepassRepositoryImpl : OlvidepassRepository {
    override fun getOlvidepass(email: String): Flow<Result<OlvidepassModel>> = flow {
        if (email.isBlank()) {
            emit(Result.failure(Exception("Correo vacío")))
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emit(Result.failure(Exception("Correo inválido")))
        } else {
            emit(Result.success(OlvidepassModel(email, "Enlace de recuperación enviado")))
        }
    }
}