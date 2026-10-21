package com.example.pacetride.data.datasource

import com.example.pacetride.data.dtos.RegisterUserDto
import com.example.pacetride.data.dtos.ResenaDtoGeneric
import com.example.pacetride.data.dtos.UpdateUserDto
import com.example.pacetride.data.dtos.UsuarioDtoGeneric

interface UsuarioRemoteDataSource {
    suspend fun getUsuarioById(id: String, currentUserId: String = ""): UsuarioDtoGeneric
    suspend fun getReviewsUsuarioId(id: String, currentUserId: String = ""): List<ResenaDtoGeneric>
    suspend fun registerUsuario(registerUserDto: RegisterUserDto, userId: String): Unit
    suspend fun updateUsuario(id: String, updateUserDto: UpdateUserDto): Unit

    suspend fun updateFotoPerfil(id: String, fotoPerfil: String): Unit
    suspend fun followOrUnfollowUsuario(currentUserId: String, targetUserId: String): Unit
    suspend fun updateFcmToken(userId: String, token: String): Unit
}