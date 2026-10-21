package com.example.pacetride.data.datasource.impl.firestore

import com.example.pacetride.data.datasource.CarreraRemoteDataSource
import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.ResenaDtoGeneric
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CarreraFirestoreDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore
): CarreraRemoteDataSource {

    override suspend fun getCarreras(): List<CarreraDto> {
        val snapshot = db.collection("carreras").get().await()
        return snapshot.documents.map { doc ->
            val carrera = doc.toObject(CarreraDto::class.java)
            carrera?.copy(id = doc.id) ?: throw Exception("No se pudo obtener la carrera")
        }
    }

    override suspend fun getCarreraById(id: String): CarreraDto {
        val docRef = db.collection("carreras").document(id)
        val respuesta = docRef.get().await()
        return respuesta.toObject(CarreraDto::class.java)?.copy(id = respuesta.id)
            ?: throw Exception("No se pudo obtener la carrera")
    }

    override suspend fun getReviewsCarreraId(
        id: String,
        currentUserId: String
    ): List<ResenaDtoGeneric> {
        val snapshot = db.collection("resenas").whereEqualTo("carreraId", id).get().await()
        return snapshot.documents.map { doc ->
            val resena = doc.toObject(ResenaFirestoreDto::class.java)?.copy(id = doc.id)
                ?: throw Exception("No se pudo obtener la reseña")

            if(currentUserId.isNotEmpty()) {
                val likeSnapshot = doc.reference.collection("likes").document(currentUserId).get().await()
                resena.liked = likeSnapshot.exists()
            }
            resena
        }
    }

    override suspend fun listenAllCarreras(): Flow<List<CarreraDto>> = callbackFlow {
        val listener = db.collection("carreras").addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if(snapshot != null) {
                val carreras = snapshot.documents.map { doc ->
                    val carrera = doc.toObject(CarreraDto::class.java)
                    carrera?.copy(id = doc.id) ?: throw Exception("No se pudo obtener la carrera")
                }

                trySend(carreras).isSuccess
            }
        }
        awaitClose {
            listener.remove()
        }
    }
}
    /*En caso de borrar una carrera borrar sus resenas:
    * override suspend fun deleteCarrera(id: String) {
    *     db.collection("carreras").document(id).delete().await()
    *     val resenasSnapshot = db.collection("resenas").whereEqualTo("carreraId", id).get().await()
    *     for (doc in resenasSnapshot.documents) {
    *          deleteCarrera(doc.id)
    *     }
    * }
    */