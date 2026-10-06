package com.example.pacetride.data.datasource

import com.example.pacetride.data.dtos.ResenaDto
import com.example.pacetride.data.dtos.UsuarioDto

interface UsuarioRemoteDataSource {
    suspend fun getUsuarioById(id: String): UsuarioDto
    suspend fun getReviewsUsuarioId(id: String): List<ResenaDto>
}