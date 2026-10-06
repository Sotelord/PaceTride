package com.example.pacetride.data.repository

import android.util.Log
import retrofit2.HttpException
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.Resena
import com.example.pacetride.data.datasource.impl.CarreraRetrofitDataSourceImpl
import com.example.pacetride.data.dtos.toCarrera
import com.example.pacetride.data.dtos.toResena
import com.google.gson.JsonParseException
import kotlinx.coroutines.CancellationException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class CarreraRepository @Inject constructor(
    private val carreraRemoteDataSource: CarreraRetrofitDataSourceImpl
) {
    suspend fun getCarreras(): Result<List<Carrera>> {
        return try {
            val carrerasDto = carreraRemoteDataSource.getCarreras()
            val carreras = carrerasDto.map { it.toCarrera() }
            Result.success(carreras)
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> Result.failure(Exception("No encontramos carreras"))
                in 500..599 -> Result.failure(Exception("El servidor tuvo un problema. Intenta más tarde"))
                else -> Result.failure(Exception("No pudimos cargar las carreras (${e.code()})"))
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
            Log.e("CARRERA", "Error inesperado", e)
            Result.failure(e)
        }
    }

    suspend fun getCarreraById(id: String): Result<Carrera> {
        return try {
            val carreraDto = carreraRemoteDataSource.getCarreraById(id)
            val carrera = carreraDto.toCarrera()
            Result.success(carrera)
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> Result.failure(Exception("Carrera no encontrada"))
                in 500..599 -> Result.failure(Exception("El servidor tuvo un problema. Intenta más tarde"))
                else -> Result.failure(Exception("No pudimos cargar la carrera (${e.code()})"))
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
            Log.e("CARRERA", "Error inesperado", e)
            Result.failure(Exception("Ocurrió un error inesperado"))
        }
    }

    suspend fun getReviewsCarreraId(id: String): Result<List<Resena>> {
        return try {
            val resenasDto = carreraRemoteDataSource.getReviewsCarreraId(id)
            val resenas = resenasDto.map { it.toResena() }
            Result.success(resenas)
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> Result.failure(Exception("No encontramos resenas"))
                in 500..599 -> Result.failure(Exception("El servidor tuvo un problema. Intenta más tarde"))
                else -> Result.failure(Exception("No pudimos cargar las resenas (${e.code()})"))
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
            Log.e("CARRERA", "Error inesperado", e)
            Result.failure(e)
        }
    }
}