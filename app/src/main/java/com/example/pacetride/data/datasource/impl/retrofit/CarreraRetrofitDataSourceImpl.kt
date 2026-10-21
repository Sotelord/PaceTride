package com.example.pacetride.data.datasource.impl.retrofit

import com.example.pacetride.data.datasource.CarreraRemoteDataSource
import com.example.pacetride.data.datasource.services.CarreraRetrofitService
import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.ResenaDtoGeneric
import com.example.pacetride.data.dtos.ResenaRetrofitDto
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class CarreraRetrofitDataSourceImpl @Inject constructor(
    val service: CarreraRetrofitService
): CarreraRemoteDataSource {
    override suspend fun getCarreras(): List<CarreraDto> {
        return service.getCarreras()
    }

    override suspend fun getCarreraById(id: String): CarreraDto {
        return service.getCarreraById(id)
    }

    override suspend fun getReviewsCarreraId(
        id: String,
        currentUserId: String
    ): List<ResenaDtoGeneric> {
        return service.getReviewsCarreraId(id)
    }

    override suspend fun listenAllCarreras(): Flow<List<CarreraDto>> {
        TODO("Not yet implemented")
    }
}