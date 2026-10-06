package com.example.pacetride.data.datasource.impl

import com.example.pacetride.data.datasource.UsuarioRemoteDataSource
import com.example.pacetride.data.datasource.services.UsuarioRetrofitService
import com.example.pacetride.data.dtos.ResenaDto
import com.example.pacetride.data.dtos.UsuarioDto
import jakarta.inject.Inject

class UsuarioRetrofitDataSourceImpl @Inject constructor(
    val service: UsuarioRetrofitService
) : UsuarioRemoteDataSource {
    override suspend fun getUsuarioById(id: String): UsuarioDto {
        return service.getUsuarioById(id)
    }

    override suspend fun getReviewsUsuarioId(id: String): List<ResenaDto> {
        return service.getReviewsUsuarioId(id)
    }
}