package com.example.pacetride.data.datasource.impl

import com.example.pacetride.data.datasource.ResenaRemoteDataSource
import com.example.pacetride.data.datasource.services.ResenaRetrofitService
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.ResenaDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import jakarta.inject.Inject

class ResenaRetrofitDataSourceImpl @Inject constructor(
   val service: ResenaRetrofitService
): ResenaRemoteDataSource {
    override suspend fun createResena(resena: CreateResenaDto) {
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

    override suspend fun getResenasById(id: String): ResenaDto {
        return service.getResenaById(id)
    }
}