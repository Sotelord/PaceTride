package com.example.pacetride.data.datasource.impl.firestore

import com.example.pacetride.data.datasource.CarreraRemoteDataSource
import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.google.firebase.firestore.FirebaseFirestore
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

    override suspend fun getReviewsCarreraId(id: String): List<ResenaFirestoreDto> {
        return db.collection("resenas").whereEqualTo("carreraId", id).get().await().toObjects(
            ResenaFirestoreDto::class.java)
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
}