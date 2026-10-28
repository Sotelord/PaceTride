package com.example.pacetride.data.datasource

import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.ResenaDtoGeneric
import kotlinx.coroutines.flow.Flow

interface CarreraRemoteDataSource {
    suspend fun getCarreras(): List<CarreraDto>
    suspend fun getCarreraById(id: String): CarreraDto?
    suspend fun getReviewsCarreraId(id: String, currentUserId: String = ""): List<ResenaDtoGeneric>
    suspend fun listenAllCarreras(): Flow<List<CarreraDto>>
}