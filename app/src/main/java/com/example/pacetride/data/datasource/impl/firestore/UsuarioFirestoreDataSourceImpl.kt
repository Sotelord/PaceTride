package com.example.pacetride.data.datasource.impl.firestore

import com.example.pacetride.data.datasource.UsuarioRemoteDataSource
import com.example.pacetride.data.dtos.RegisterUserDto
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.example.pacetride.data.dtos.UpdateUserDto
import com.example.pacetride.data.dtos.UsuarioFirestoreDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioFirestoreDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore
) : UsuarioRemoteDataSource {
    override suspend fun getUsuarioById(id: String): UsuarioFirestoreDto {
        val docRef = db.collection("usuarios").document(id)
        val respuesta = docRef.get().await()
        return respuesta.toObject(UsuarioFirestoreDto::class.java)?.copy(id = respuesta.id)
            ?: throw Exception("No se pudo obtener el usuario")
    }

    override suspend fun getReviewsUsuarioId(id: String): List<ResenaFirestoreDto> {
        val snapshot = db.collection("resenas").whereEqualTo("usuarioId", id).get().await()
        return snapshot.documents.map { doc ->
            val resena = doc.toObject(ResenaFirestoreDto::class.java)
            resena?.copy(id = doc.id) ?: throw Exception("No se pudo obtener la reseña")
        }
    }

    override suspend fun registerUsuario(
        registerUserDto: RegisterUserDto,
        userId: String
    ) {
        val docRef =
            db.collection("usuarios").document(userId) //Se crea un doc. con el id del usuario
        docRef.set(registerUserDto).await() //Insertando el dto del doc en la db
    }

    override suspend fun updateUsuario(
        id: String,
        updateUserDto: UpdateUserDto
    ) {
        val cambios = mapOf(
            "bio" to updateUserDto.bio,
            "ubicacion" to updateUserDto.ubicacion
        )
        db.collection("usuarios").document(id)
            .set(cambios, SetOptions.merge())
            .await()
    }

    override suspend fun updateFotoPerfil(id: String, fotoPerfil: String) {
        db.collection("usuarios").document(id).update("fotoPerfil", fotoPerfil).await()
    }

}