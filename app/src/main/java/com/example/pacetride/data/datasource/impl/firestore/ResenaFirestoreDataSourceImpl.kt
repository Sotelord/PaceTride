package com.example.pacetride.data.datasource.impl.firestore

import com.example.pacetride.data.datasource.ResenaRemoteDataSource
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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

    override suspend fun getResenasById(id: String): ResenaFirestoreDto {
        val docRef = db.collection("resenas").document(id)
        val respuesta = docRef.get().await()
        return respuesta.toObject(ResenaFirestoreDto::class.java)
            ?: throw Exception("No se pudo obtener la reseña")
    }
}