package com.example.pacetride.data.datasource.impl

import com.example.pacetride.data.datasource.CarreraRemoteDataSource
import com.example.pacetride.data.datasource.services.CarreraRetrofitService
import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.ResenaDto
import jakarta.inject.Inject

class CarreraRetrofitDataSourceImpl @Inject constructor(
    val service: CarreraRetrofitService
): CarreraRemoteDataSource {
    override suspend fun getCarreras(): List<CarreraDto> {
        return service.getCarreras()
    }

    override suspend fun getCarreraById(id: String): CarreraDto {
        return service.getCarreraById(id)
    }

    override suspend fun getReviewsCarreraId(id: String): List<ResenaDto> {
        return service.getReviewsCarreraId(id)
    }
}