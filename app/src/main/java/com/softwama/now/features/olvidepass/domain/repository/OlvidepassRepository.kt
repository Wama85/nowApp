package com.softwama.now.features.olvidepass.domain.repository

import com.softwama.now.features.olvidepass.domain.model.OlvidepassModel

import kotlinx.coroutines.flow.Flow

interface OlvidepassRepository {
    fun getOlvidepass(email: String): Flow<Result<OlvidepassModel>>
}