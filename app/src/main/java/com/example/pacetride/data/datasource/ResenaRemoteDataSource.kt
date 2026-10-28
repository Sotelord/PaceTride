package com.example.pacetride.data.datasource

import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.ResenaDtoGeneric
import com.example.pacetride.data.dtos.UpdateResenaDto
import kotlinx.coroutines.flow.Flow

interface ResenaRemoteDataSource {
    suspend fun createResena(resena: CreateResenaDto): Unit
    suspend fun updateResena(id: String, resena: UpdateResenaDto): Unit
    suspend fun deleteResena(id: String): Unit
    suspend fun getResenasById(id: String, currentUserId: String = ""): ResenaDtoGeneric?
    suspend fun sendOrDeleteResenaLike(resenaId: String, userId: String): Unit
    suspend fun getAllResenas(currentUserId: String = ""): List<ResenaDtoGeneric>
    suspend fun getResenasSeguidos(currentUserId: String = ""): List<ResenaDtoGeneric>

    suspend fun listenAllResenas(): Flow<List<ResenaDtoGeneric>>
}