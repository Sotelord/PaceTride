package com.example.pacetride.data.datasource.services

import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.ResenaDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CarreraRetrofitService {
    @GET("carreras")
    suspend fun getCarreras(): List<CarreraDto>

    @GET("carreras/{id}")
    suspend fun getCarreraById(@Path("id") id: String): CarreraDto

    @GET("carreras/{id}/resenas")
    suspend fun getReviewsCarreraId(@Path("id") id: String): List<ResenaDto>
}