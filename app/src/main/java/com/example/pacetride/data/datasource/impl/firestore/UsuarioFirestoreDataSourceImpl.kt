package com.example.pacetride.data.datasource.impl.firestore

import com.example.pacetride.data.datasource.UsuarioRemoteDataSource
import com.example.pacetride.data.dtos.RegisterUserDto
import com.example.pacetride.data.dtos.ResenaDtoGeneric
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.example.pacetride.data.dtos.UpdateUserDto
import com.example.pacetride.data.dtos.UsuarioFirestoreDto
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioFirestoreDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore
) : UsuarioRemoteDataSource {
    override suspend fun getUsuarioById(id: String, currentUserId: String): UsuarioFirestoreDto {
        val docRef = db.collection("usuarios").document(id)
        val respuesta = docRef.get().await()
        val usuario = respuesta.toObject(UsuarioFirestoreDto::class.java)?.copy(id = respuesta.id)
            ?: throw Exception("No se pudo obtener el usuario")
        if(currentUserId.isNotEmpty()) {
            val followerDoc =
                db.collection("usuarios").document(id).collection("followers")
                    .document(currentUserId)
                    .get().await()

            val exist = followerDoc.exists()

            usuario.followed = exist
        }
        return usuario

    }

    override suspend fun getReviewsUsuarioId(
        id: String,
        currentUserId: String
    ): List<ResenaDtoGeneric> {
        val snapshot = db.collection("resenas").whereEqualTo("usuarioId", id).get().await()
        return snapshot.documents.map { doc ->
            val resena = doc.toObject(ResenaFirestoreDto::class.java)?.copy(id = doc.id)
                ?: throw Exception("No se pudo obtener la reseña")

            if (currentUserId.isNotEmpty()) {
                val likeSnapshot =
                    doc.reference.collection("likes").document(currentUserId).get().await()
                resena.liked = likeSnapshot.exists()
            }
            resena
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

    override suspend fun followOrUnfollowUsuario(
        currentUserId: String,
        targetUserId: String
    ) {
        val currentUserRef = db.collection("usuarios").document(currentUserId)
        val targetUserRef = db.collection("usuarios").document(targetUserId)

        val followingRef = currentUserRef.collection("following").document(targetUserId)
        val followersRef = targetUserRef.collection("followers").document(currentUserId)

        db.runTransaction { transaction ->
            val followingDoc = transaction.get(followingRef)
            if (followingDoc.exists()) {
                transaction.delete(followingRef)
                transaction.delete(followersRef)
                transaction.update(currentUserRef, "followingCount", FieldValue.increment(-1))
                transaction.update(targetUserRef, "followersCount", FieldValue.increment(-1))
            } else {
                transaction.set(followingRef, mapOf("timestamp" to FieldValue.serverTimestamp()))
                transaction.set(followersRef, mapOf("timestamp" to FieldValue.serverTimestamp()))
                transaction.update(currentUserRef, "followingCount", FieldValue.increment(1))
                transaction.update(targetUserRef, "followersCount", FieldValue.increment(1))
            }
        }.await()
    }

    override suspend fun updateFcmToken(userId: String, token: String) {
        db.collection("usuarios").document(userId)
            .set(mapOf("fcmToken" to token), SetOptions.merge())
            .await()
    }
}