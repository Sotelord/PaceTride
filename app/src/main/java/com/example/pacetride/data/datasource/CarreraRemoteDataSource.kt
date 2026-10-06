package com.example.pacetride.data.datasource

import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.ResenaDto

interface CarreraRemoteDataSource {
    suspend fun getCarreras(): List<CarreraDto>
    suspend fun getCarreraById(id: String): CarreraDto
    suspend fun getReviewsCarreraId(id: String): List<ResenaDto>
}