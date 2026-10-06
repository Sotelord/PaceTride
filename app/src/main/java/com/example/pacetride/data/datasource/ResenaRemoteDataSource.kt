package com.example.pacetride.data.datasource

import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.ResenaDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import com.example.pacetride.data.dtos.UsuarioDto

interface ResenaRemoteDataSource {
    suspend fun createResena(resena: CreateResenaDto): Unit
    suspend fun updateResena(id: String, resena: UpdateResenaDto): Unit
    suspend fun deleteResena(id: String): Unit
    suspend fun getResenasById(id: String): ResenaDto
}