package com.example.pacetride.data.datasource.impl.firestore

import com.example.pacetride.data.datasource.ResenaRemoteDataSource
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.ResenaDtoGeneric
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ResenaFirestoreDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore
) : ResenaRemoteDataSource {
    override suspend fun createResena(resena: CreateResenaDto) {
        db.collection("resenas").add(resena).await()
    }

    override suspend fun updateResena(
        id: String,
        resena: UpdateResenaDto
    ) {
        val cambios = mapOf(
            "resena" to resena.resena,
            "calificacion" to resena.calificacion,
            "categoriasDestacadas" to resena.categoriasDestacadas,
            "updatedAt" to FieldValue.serverTimestamp()
        )
        db.collection("resenas").document(id)
            .set(cambios, SetOptions.merge())
            .await()
    }

    override suspend fun deleteResena(id: String) {
        db.collection("resenas").document(id).delete().await()
    }

    override suspend fun getResenasById(
        id: String,
        currentUserId: String
    ): ResenaDtoGeneric? {
        val resenaRef = db.collection("resenas").document(id)
        val resenaSnapshot = resenaRef.get().await()
        val resena = resenaSnapshot.toObject(ResenaFirestoreDto::class.java)?.copy(id = resenaSnapshot.id)
            ?: return null
        if(currentUserId.isNotEmpty()) {
            val likeSnapshot = resenaRef.collection("likes").document(currentUserId).get().await()
            val hasLiked = likeSnapshot.exists()

            if(hasLiked){
                resena.liked = true
            }
        }

        return resena
    }


    override suspend fun sendOrDeleteResenaLike(resenaId: String, userId: String) {
        val resenaRef = db.collection("resenas").document(resenaId)
        val likesRef = resenaRef.collection("likes").document(userId)

        db.runTransaction { transaction ->
            val likeDoc = transaction.get(likesRef)

            if(likeDoc.exists()) {
                transaction.delete(likesRef)
                transaction.update(resenaRef, "likes", FieldValue.increment(-1))
            } else {
                transaction.set(likesRef, mapOf("timestamp" to FieldValue.serverTimestamp()))
                transaction.update(resenaRef, "likes", FieldValue.increment(1))
            }
        }.await()
    }

    override suspend fun getAllResenas(currentUserId: String): List<ResenaDtoGeneric> {
        val snapshot = db.collection("resenas").get().await()
        val resenas = snapshot.documents.map { doc ->
            val resena = doc.toObject(ResenaFirestoreDto::class.java)?.copy(id = doc.id)
                ?: throw Exception("No se pudo obtener la reseña")

            if (currentUserId.isNotEmpty()) {
                val likeSnapshot = doc.reference.collection("likes").document(currentUserId).get().await()
                resena.liked = likeSnapshot.exists()
            }
            resena
        }
        return resenas.sortedByDescending { it.createdAt?.seconds ?: 0L }
    }

    override suspend fun getResenasSeguidos(currentUserId: String): List<ResenaDtoGeneric> {
        if (currentUserId.isEmpty()) return emptyList()

        val seguidosIds = db.collection("usuarios").document(currentUserId)
            .collection("following").get().await()
            .documents.map { it.id }
        if (seguidosIds.isEmpty()) return emptyList()

        val resenas = mutableListOf<ResenaFirestoreDto>()
        for (grupo in seguidosIds.chunked(30)) {
            val snapshot = db.collection("resenas").whereIn("usuarioId", grupo).get().await()
            for (doc in snapshot.documents) {
                val resena = doc.toObject(ResenaFirestoreDto::class.java)?.copy(id = doc.id) ?: continue
                val likeSnapshot = doc.reference.collection("likes").document(currentUserId).get().await()
                resena.liked = likeSnapshot.exists()
                resenas.add(resena)
            }
        }
        return resenas.sortedByDescending { it.createdAt?.seconds ?: 0L }
    }

    override suspend fun listenAllResenas(): Flow<List<ResenaDtoGeneric>> = callbackFlow {
        val listener = db.collection("resenas").addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if(snapshot != null) {
                val resenas = snapshot.documents.map { doc ->
                    val resena = doc.toObject(ResenaFirestoreDto::class.java)
                    resena?.copy(id = doc.id) ?: throw Exception("No se pudo obtener la reseña")
                }

                trySend(resenas).isSuccess
            }
        }
        awaitClose {
            listener.remove()
        }
    }
}