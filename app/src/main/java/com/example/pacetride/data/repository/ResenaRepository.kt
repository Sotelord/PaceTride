package com.example.pacetride.data.repository

import android.util.Log
import com.example.pacetride.data.Resena
import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.CarreraFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.ResenaFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.retrofit.ResenaRetrofitDataSourceImpl
import com.example.pacetride.data.dtos.CreateResenaCarreraDto
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.CreateResenaUsuarioDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import com.google.gson.JsonParseException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ResenaRepository @Inject constructor(
    private val resenaRemoteDataSource: ResenaFirestoreDataSourceImpl,
    private val usuarioRemoteDataSource: UsuarioFirestoreDataSourceImpl,
    private val carreraRemoteDataSource: CarreraFirestoreDataSourceImpl,
    private val authRemoteDataSource: AuthRemoteDataSource
    //private val resenaRemoteDataSource: ResenaRetrofitDataSourceImpl
) {
    suspend fun createResena(
        usuarioId: String,
        carreraId: String,
        resena: String,
        calificacion: String,
        categoriasDestacadas: List<String>?
    ): Result<Unit> {
        return try {
            val user = usuarioRemoteDataSource.getUsuarioById(usuarioId)
            val carrera = carreraRemoteDataSource.getCarreraById(carreraId)
            val fotoPerfil = authRemoteDataSource.currentUser?.photoUrl?.toString().orEmpty()

            val createResenaUsuarioDto = CreateResenaUsuarioDto(
                fotoPerfil = fotoPerfil,
                nombre = user?.nombre,
                usuario = user?.userName
            )

            val createResenaCarreraDto = CreateResenaCarreraDto(
                nombre = carrera?.nombre ?: "",
            )

            val createResenaDto = CreateResenaDto(
                usuarioId = usuarioId,
                carreraId = carreraId,
                resena = resena,
                calificacion = calificacion.toFloat(),
                categoriasDestacadas = categoriasDestacadas,
                usuario = createResenaUsuarioDto,
                carrera = createResenaCarreraDto
            )
            resenaRemoteDataSource.createResena(createResenaDto)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: NumberFormatException) {
            Result.failure(Exception("La calificación o los datos de la reseña no son válidos"))
        } catch (e: HttpException) {
            when (e.code()) {
                400 -> Result.failure(Exception("Revisa los datos de tu reseña"))
                404 -> Result.failure(Exception("No encontramos la carrera o el usuario"))
                409 -> Result.failure(Exception("Ya publicaste una reseña para esta carrera"))
                in 500..599 -> Result.failure(Exception("El servidor tuvo un problema. Intenta más tarde"))
                else -> Result.failure(Exception("No pudimos publicar tu reseña (${e.code()})"))
            }
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("La conexión tardó demasiado. Intenta de nuevo"))
        } catch (e: UnknownHostException) {
            Result.failure(Exception("Sin conexión a internet"))
        } catch (e: ConnectException) {
            Result.failure(Exception("No pudimos conectar con el servidor"))
        } catch (e: JsonParseException) {
            Result.failure(Exception("Recibimos una respuesta inesperada del servidor"))
        } catch (e: IOException) {
            Result.failure(Exception("Error de red. Revisa tu conexión"))
        } catch (e: Exception) {
            Log.e("RESENA", "Error inesperado", e)
            Result.failure(e)
        }
    }

    suspend fun updateResena(
        id: String,
        resena: String,
        calificacion: String,
        categoriasDestacadas: List<String>?
    ): Result<Unit> {
        return try {
            val updateResenaDto = UpdateResenaDto(
                resena = resena,
                calificacion = calificacion.toFloat(),
                categoriasDestacadas = categoriasDestacadas
            )
            resenaRemoteDataSource.updateResena(id, updateResenaDto)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: NumberFormatException) {
            Result.failure(Exception("La calificación o los datos de la reseña no son válidos"))
        } catch (e: HttpException) {
            when (e.code()) {
                400 -> Result.failure(Exception("Revisa los datos de tu reseña"))
                404 -> Result.failure(Exception("No encontramos la carrera o el usuario"))
                409 -> Result.failure(Exception("Ya publicaste una reseña para esta carrera"))
                in 500..599 -> Result.failure(Exception("El servidor tuvo un problema. Intenta más tarde"))
                else -> Result.failure(Exception("No pudimos actualizar tu reseña (${e.code()})"))
            }
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("La conexión tardó demasiado. Intenta de nuevo"))
        } catch (e: UnknownHostException) {
            Result.failure(Exception("Sin conexión a internet"))
        } catch (e: ConnectException) {
            Result.failure(Exception("No pudimos conectar con el servidor"))
        } catch (e: JsonParseException) {
            Result.failure(Exception("Recibimos una respuesta inesperada del servidor"))
        } catch (e: IOException) {
            Result.failure(Exception("Error de red. Revisa tu conexión"))
        } catch (e: Exception) {
            Log.e("RESENA", "Error inesperado", e)
            Result.failure(e)
        }
    }

    suspend fun getResenaById(id: String): Result<Resena> {
        val currentUserId = authRemoteDataSource.currentUser?.uid ?: ""
        return try {
            val resenaDto = resenaRemoteDataSource.getResenasById(id, currentUserId)
            if (resenaDto == null) return Result.failure(Exception("Resena no encontrada"))
            val resena = resenaDto.toResena()
            Result.success(resena)
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> Result.failure(Exception("Resena no encontrada"))
                in 500..599 -> Result.failure(Exception("El servidor tuvo un problema. Intenta más tarde"))
                else -> Result.failure(Exception("No pudimos cargar la resena (${e.code()})"))
            }
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("La conexión tardó demasiado. Intenta de nuevo"))
        } catch (e: UnknownHostException) {
            Result.failure(Exception("Sin conexión a internet"))
        } catch (e: ConnectException) {
            Result.failure(Exception("No pudimos conectar con el servidor"))
        } catch (e: JsonParseException) {
            Result.failure(Exception("Recibimos una respuesta inesperada del servidor"))
        } catch (e: IOException) {
            Result.failure(Exception("Error de red. Revisa tu conexión"))
        } catch (e: Exception) {
            Log.e("RESENA", "Error inesperado", e)
            Result.failure(Exception("Ocurrió un error inesperado"))
        }
    }

    suspend fun deleteResena(id: String): Result<Unit> {
        return try {
            resenaRemoteDataSource.deleteResena(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendOrDeleteResenaLike(resenaId: String, userId: String): Result<Unit> {
        return try {
            resenaRemoteDataSource.sendOrDeleteResenaLike(resenaId, userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllResenas(): Result<List<Resena>> {
        val currentUserId = authRemoteDataSource.currentUser?.uid ?: ""
        return try {
            Result.success(resenaRemoteDataSource.getAllResenas(currentUserId).map { it.toResena() })
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RESENA", "Error al cargar reseñas", e)
            Result.failure(e)
        }
    }

    suspend fun getResenasSeguidos(): Result<List<Resena>> {
        val currentUserId = authRemoteDataSource.currentUser?.uid ?: ""
        return try {
            Result.success(resenaRemoteDataSource.getResenasSeguidos(currentUserId).map { it.toResena() })
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("RESENA", "Error al cargar reseñas de seguidos", e)
            Result.failure(e)
        }
    }

    suspend fun getResenasLive(): Flow<List<Resena>> {
        return resenaRemoteDataSource.listenAllResenas().map { resenasDto ->
            resenasDto.map { it.toResena() }
        }
    }
}