package com.example.pacetride.data.repository

import android.util.Log
import com.example.pacetride.data.Resena
import com.example.pacetride.data.datasource.impl.ResenaRetrofitDataSourceImpl
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import com.example.pacetride.data.dtos.toResena
import com.google.gson.JsonParseException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ResenaRepository @Inject constructor(
    private val resenaRemoteDataSource: ResenaRetrofitDataSourceImpl
) {
    suspend fun createResena(
        usuarioId: String,
        carreraId: String,
        resena: String,
        calificacion: String,
        categoriasDestacadas: List<String>?
    ): Result<Unit> {
        return try {
            val createResenaDto = CreateResenaDto(
                usuarioId = usuarioId.toInt(),
                carreraId = carreraId.toInt(),
                resena = resena,
                calificacion = calificacion.toFloat(),
                categoriasDestacadas = categoriasDestacadas
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
        return try {
            val resenaDto = resenaRemoteDataSource.getResenasById(id)
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
}