package com.example.pacetride.data.repository

import android.util.Log
import com.example.pacetride.data.Resena
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.datasource.UsuarioRemoteDataSource
import com.example.pacetride.data.datasource.impl.UsuarioRetrofitDataSourceImpl
import com.example.pacetride.data.dtos.toResena
import com.example.pacetride.data.dtos.toUsuario
import com.google.gson.JsonParseException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val usuarioRemoteDataSource: UsuarioRetrofitDataSourceImpl
) {
    suspend fun getUsuarioById(id: String): Result<Usuario> {
        return try {
            val usuarioDto = usuarioRemoteDataSource.getUsuarioById(id)
            val usuario = usuarioDto.toUsuario()
            Result.success(usuario)
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> Result.failure(Exception("Usuario no encontrado"))
                in 500..599 -> Result.failure(Exception("El servidor tuvo un problema. Intenta más tarde"))
                else -> Result.failure(Exception("No pudimos cargar el usuario (${e.code()})"))
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
            Log.e("USUARIOS", "Error inesperado", e)
            Result.failure(Exception("Ocurrió un error inesperado"))
        }
    }

    suspend fun getReviewsUsuarioId(id: String): Result<List<Resena>> {
        return try {
            val resenasDto = usuarioRemoteDataSource.getReviewsUsuarioId(id)
            val resenas = resenasDto.map { it.toResena() }
            Log.d("Lenght", "Repo ${resenas.size}")
            Result.success(resenas)
        }catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Log.e("Lenght", "HTTP ${e.code()}", e)
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
            Log.e("Lenght", "Parseo de reseñas", e)
            Result.failure(Exception("Recibimos una respuesta inesperada del servidor"))
        } catch (e: IOException) {
            Result.failure(Exception("Error de red. Revisa tu conexión"))
        } catch (e: Exception) {
            Log.e("USUARIOS", "Error inesperado", e)
            Result.failure(e)
        }
    }
}