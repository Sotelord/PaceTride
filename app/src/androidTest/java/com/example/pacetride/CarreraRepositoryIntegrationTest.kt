package com.example.pacetride

import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.CarreraFirestoreDataSourceImpl
import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.repository.CarreraRepository
import com.google.common.truth.Truth
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class CarreraRepositoryIntegrationTest {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private lateinit var carreraRepository: CarreraRepository

    private fun generateCarrera(i: Int) = CarreraDto(
        id = "carrera_$i",
        raceImageUrl = "https://picsum.photos/700/400?random=$i",
        nombre = "Carrera $i",
        fecha = "2026-11-0${(i % 9) + 1}",
        ubicacion = "Ubicacion $i",
        distanciasDisponiblesKm = listOf(5, 10),
        precioBase = 40000 + i * 1000,
        distanciaReferenciaKm = 10,
        ultimosCupos = false,
        descripcion = "Descripcion $i",
        createdAt = "2026-10-03T19:31:37.551Z",
        updatedAt = "2026-10-03T19:31:37.551Z"
    )

    @Before
    fun setUp() = runTest {
        try {
            db.useEmulator("10.0.2.2", 8080)
        } catch (e: Exception) {
        }

        carreraRepository = CarreraRepository(
            CarreraFirestoreDataSourceImpl(db),
            AuthRemoteDataSource(auth)
        )

        val batch = db.batch()
        repeat(10) { i ->
            val carrera = generateCarrera(i)
            batch.set(db.collection("carreras").document(carrera.id), carrera)
        }
        batch.commit().await()
    }

    @Test
    fun getCarreraById_validId_correctCarrera() = runTest {
        //Arrange
        val id = "carrera_7"
        //Act
        val result = carreraRepository.getCarreraById(id)
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        val carrera = result.getOrNull()
        Truth.assertThat(carrera?.id).isEqualTo(id)
        Truth.assertThat(carrera?.nombre).isEqualTo("Carrera 7")
        Truth.assertThat(carrera?.precioBase).isEqualTo(47000)
        Truth.assertThat(carrera?.distanciasDisponiblesKm).containsExactly(5, 10).inOrder()
    }

    @Test
    fun getCarreraById_invalidId_returnFailure() = runTest {
        //Arrange
        val id = "carrera_999"
        //Act
        val result = carreraRepository.getCarreraById(id)
        //Assert
        Truth.assertThat(result.isFailure).isTrue()
        Truth.assertThat(result.exceptionOrNull()?.message).isEqualTo("Carrera no encontrada")
    }

    @After
    fun tearDown() = runTest {
        val carreras = db.collection("carreras").get().await()
        for (carreraDoc in carreras) {
            carreraDoc.reference.delete().await()
        }
    }
}