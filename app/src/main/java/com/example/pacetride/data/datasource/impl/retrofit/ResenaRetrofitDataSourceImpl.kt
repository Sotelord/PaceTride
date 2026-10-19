package com.example.pacetride.data.datasource.impl.retrofit

import com.example.pacetride.data.datasource.ResenaRemoteDataSource
import com.example.pacetride.data.datasource.services.ResenaRetrofitService
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.ResenaRetrofitDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import jakarta.inject.Inject

class ResenaRetrofitDataSourceImpl @Inject constructor(
   val service: ResenaRetrofitService
): ResenaRemoteDataSource {
    override suspend fun createResena(resena: CreateResenaDto) {
        resena.usuarioId = "3"
        return service.createResena(resena)
    }

    override suspend fun deleteResena(id: String) {
        return service.deleteResena(id)
    }

    override suspend fun updateResena(
        id: String,
        resena: UpdateResenaDto
    ) {
        return service.updateResena(id, resena)
    }

    override suspend fun getResenasById(id: String): ResenaRetrofitDto {
        return service.getResenaById(id)
    }
}