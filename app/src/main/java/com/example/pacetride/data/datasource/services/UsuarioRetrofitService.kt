package com.example.pacetride.data.datasource.services

import com.example.pacetride.data.dtos.ResenaDto
import com.example.pacetride.data.dtos.UsuarioDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioRetrofitService {
    @GET("usuarios/{id}")
    suspend fun getUsuarioById(@Path("id") id: String): UsuarioDto

    @GET("usuarios/{id}/resenas")
    suspend fun getReviewsUsuarioId(@Path("id") id: String): List<ResenaDto>
}