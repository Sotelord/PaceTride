package com.example.pacetride.data.datasource.services

import com.example.pacetride.data.dtos.ResenaRetrofitDto
import com.example.pacetride.data.dtos.UsuarioRetrofitDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioRetrofitService {
    @GET("usuarios/{id}")
    suspend fun getUsuarioById(@Path("id") id: String): UsuarioRetrofitDto

    @GET("usuarios/{id}/resenas")
    suspend fun getReviewsUsuarioId(@Path("id") id: String): List<ResenaRetrofitDto>
}