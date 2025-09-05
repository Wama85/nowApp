package com.softwama.now.features.olvidepass.domain.usecase


import com.softwama.now.features.olvidepass.domain.model.OlvidepassModel
import com.softwama.now.features.olvidepass.domain.repository.OlvidepassRepository
import com.softwama.now.navigation.Screen
import kotlinx.coroutines.flow.Flow

class OlvidepassUseCase(private val repository: OlvidepassRepository) {
    fun sendRecovery(email: String): Flow<Result<OlvidepassModel>> {
        return repository.getOlvidepass(email)
    }
}