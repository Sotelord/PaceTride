package com.example.pacetride.data.repository

import android.util.Log
import com.example.pacetride.data.Resena
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.retrofit.UsuarioRetrofitDataSourceImpl
import com.example.pacetride.data.dtos.EstadisticasGlobalesDto
import com.example.pacetride.data.dtos.RegisterUserDto
import com.example.pacetride.data.dtos.UpdateUserDto
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonParseException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val usuarioRemoteDataSource: UsuarioFirestoreDataSourceImpl,
    private val authRemoteDataSource: AuthRemoteDataSource
    //private val usuarioRemoteDataSource: UsuarioRetrofitDataSourceImpl
) {
    suspend fun getUsuarioById(id: String): Result<Usuario> {
        val currentUserId = authRemoteDataSource.currentUser?.uid ?: ""
        return try {
            val usuarioDto = usuarioRemoteDataSource.getUsuarioById(id, currentUserId)
            if (usuarioDto == null) return Result.failure(Exception("Usuario no encontrado"))
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
        } catch (e: FirebaseFirestoreException) {
            val mensaje = when (e.code) {
                FirebaseFirestoreException.Code.PERMISSION_DENIED ->
                    "No tienes permisos para guardar tus datos"

                FirebaseFirestoreException.Code.UNAVAILABLE ->
                    "Sin conexión con el servidor. Revisa tu internet"

                FirebaseFirestoreException.Code.DEADLINE_EXCEEDED ->
                    "La conexión tardó demasiado. Intenta de nuevo"

                FirebaseFirestoreException.Code.ALREADY_EXISTS ->
                    "Este usuario ya existe"

                FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED ->
                    "Se superó el límite de uso del servicio. Intenta más tarde"

                else -> "No pudimos guardar tu información (${e.code})"
            }
            Log.e("USUARIOS", "Firestore: ${e.code}", e)
            Result.failure(Exception(mensaje))
        } catch (e: Exception) {
            Log.e("USUARIOS", "Error inesperado", e)
            Result.failure(Exception("Ocurrió un error inesperado"))
        }
    }

    suspend fun getReviewsUsuarioId(id: String): Result<List<Resena>> {
        val currentUserId = authRemoteDataSource.currentUser?.uid ?: ""
        return try {
            val resenasDto = usuarioRemoteDataSource.getReviewsUsuarioId(id, currentUserId)
            val resenas = resenasDto.map { it.toResena() }
            Log.d("Lenght", "Repo ${resenas.size}")
            Result.success(resenas)
        } catch (e: CancellationException) {
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

    suspend fun registerUsuario(
        userId: String,
        nombre: String,
        userName: String,
        bio: String?,
        ubicacion: String?
    ): Result<Unit> {
        return try {
            val fcmToken = try {
                FirebaseMessaging.getInstance().token.await()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.w("REGISTER", "No se pudo obtener el token FCM, se registra sin él", e)
                ""
            }
            val registerUserDto = RegisterUserDto(
                nombre = nombre,
                userName = userName,
                bio = bio,
                ubicacion = ubicacion,
                estadisticasGlobales = EstadisticasGlobalesDto(
                    distancia = "0km",
                    numCarrera = 0,
                    mejorTiempo10k = "0:00",
                    mejorTiempo21k = "0:00"
                ),
                followersCount = 0,
                followingCount = 0,
                fcmToken = fcmToken
            )
            usuarioRemoteDataSource.registerUsuario(registerUserDto, userId)
            Result.success(Unit)
        } catch (e: FirebaseFirestoreException) {
            val mensaje = when (e.code) {
                FirebaseFirestoreException.Code.PERMISSION_DENIED ->
                    "No tienes permisos para guardar tus datos"

                FirebaseFirestoreException.Code.UNAVAILABLE ->
                    "Sin conexión con el servidor. Revisa tu internet"

                FirebaseFirestoreException.Code.DEADLINE_EXCEEDED ->
                    "La conexión tardó demasiado. Intenta de nuevo"

                FirebaseFirestoreException.Code.ALREADY_EXISTS ->
                    "Este usuario ya existe"

                FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED ->
                    "Se superó el límite de uso del servicio. Intenta más tarde"

                else -> "No pudimos guardar tu información (${e.code})"
            }
            Log.e("USUARIOS", "Firestore: ${e.code}", e)
            Result.failure(Exception(mensaje))
        } catch (e: Exception) {
            Log.e("REGISTER", "Error inesperado no se pudo registrar al usuario", e)
            Result.failure(e)
        }
    }

    suspend fun updateUsuario(id: String, bio: String?, ubicacion: String?): Result<Unit> {
        return try {
            val updateUserDto = UpdateUserDto(
                bio = bio,
                ubicacion = ubicacion
            )
            usuarioRemoteDataSource.updateUsuario(id, updateUserDto)
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

    suspend fun updateFotoPerfil(id: String, fotoPerfil: String): Result<Unit> {
        return try {
            usuarioRemoteDataSource.updateFotoPerfil(id, fotoPerfil)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: FirebaseFirestoreException) {
            val mensaje = when (e.code) {
                FirebaseFirestoreException.Code.NOT_FOUND -> "No encontramos tu perfil"
                FirebaseFirestoreException.Code.PERMISSION_DENIED -> "No tienes permisos para actualizar tu foto"
                FirebaseFirestoreException.Code.UNAVAILABLE -> "Sin conexión con el servidor"
                else -> "No pudimos guardar tu foto (${e.code})"
            }
            Result.failure(Exception(mensaje))
        } catch (e: Exception) {
            Log.e("USUARIOS", "Error inesperado", e)
            Result.failure(e)
        }
    }

    suspend fun followOrUnfollowUsuario(currentUserId: String, targetUserId: String): Result<Unit> {
        return try {
            usuarioRemoteDataSource.followOrUnfollowUsuario(currentUserId, targetUserId)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("USUARIOS", "Error inesperado", e)
            Result.failure(e)
        }
    }

    suspend fun saveFcmToken(userId: String): Result<Unit> {
        return try {
            val token = FirebaseMessaging.getInstance().token.await()
            usuarioRemoteDataSource.updateFcmToken(userId, token)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("FCM", "No se pudo guardar el token", e)
            Result.failure(e)
        }
    }
}