package com.example.pacetride.data.datasource.impl.retrofit

import com.example.pacetride.data.datasource.UsuarioRemoteDataSource
import com.example.pacetride.data.datasource.services.UsuarioRetrofitService
import com.example.pacetride.data.dtos.RegisterUserDto
import com.example.pacetride.data.dtos.ResenaDtoGeneric
import com.example.pacetride.data.dtos.UsuarioRetrofitDto
import com.example.pacetride.data.dtos.ResenaRetrofitDto
import com.example.pacetride.data.dtos.UpdateUserDto
import com.example.pacetride.data.dtos.UsuarioDtoGeneric
import jakarta.inject.Inject

class UsuarioRetrofitDataSourceImpl @Inject constructor(
    val service: UsuarioRetrofitService
) : UsuarioRemoteDataSource {
    override suspend fun getUsuarioById(
        id: String,
        currentUserId: String
    ): UsuarioDtoGeneric {
        return service.getUsuarioById("3")
    }

    override suspend fun getReviewsUsuarioId(
        id: String,
        currentUserId: String
    ): List<ResenaDtoGeneric> {
        return service.getReviewsUsuarioId(id)
    }

    override suspend fun registerUsuario(
        registerUserDto: RegisterUserDto,
        userId: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUsuario(
        id: String,
        updateUserDto: UpdateUserDto
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateFotoPerfil(id: String, fotoPerfil: String) {
        TODO("Not yet implemented")
    }

    override suspend fun followOrUnfollowUsuario(
        currentUserId: String,
        targetUserId: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateFcmToken(userId: String, token: String) {
        TODO("Not yet implemented")
    }
}