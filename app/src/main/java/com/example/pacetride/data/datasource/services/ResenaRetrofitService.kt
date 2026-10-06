package com.example.pacetride.data.datasource.services

import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.ResenaDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ResenaRetrofitService {
    @POST("resenas")
    suspend fun createResena(@Body resena: CreateResenaDto): Unit

    @DELETE("resenas/{id}")
    suspend fun deleteResena(@Path("id") id: String): Unit

    @PUT("resenas/{id}")
    suspend fun updateResena(@Path("id") id: String, @Body resena: UpdateResenaDto): Unit

    @GET("resenas/{id}")
    suspend fun getResenaById(@Path("id") id: String): ResenaDto
}