package com.example.pacetride

import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.CarreraFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.ResenaFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.dtos.ResenaCarreraDto
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.example.pacetride.data.dtos.ResenaUsuarioDto
import com.example.pacetride.data.repository.ResenaRepository
import com.google.common.truth.Truth
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.Duration
import java.time.Instant
import java.util.Date

class ResenaRepositoryIntegrationTest {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private lateinit var resenaRepository: ResenaRepository

    private fun generateResena(
        i: Int,
        creadaEn: Instant = Instant.now()
    ) = ResenaFirestoreDto(
        usuarioId = "user_$i",
        carreraId = "carrera_$i",
        id = "resena_$i",
        resena = "Resena $i",
        calificacion = 4f,
        categoriasDestacadas = listOf("Ruta", "Organización"),
        likes = i,
        createdAt = Timestamp(Date.from(creadaEn)),
        updatedAt = Timestamp(Date.from(creadaEn)),
        carrera = ResenaCarreraDto("Carrera $i"),
        usuario = ResenaUsuarioDto(
            nombre = "Nombre $i",
            fotoPerfil = "https://picsum.photos/200/300?random=$i",
            usuario = "username_$i"
        )
    )

    private suspend fun guardarResena(resena: ResenaFirestoreDto) {
        db.collection("resenas").document(resena.id).set(resena).await()
    }

    @Before
    fun setUp() = runTest {
        try {
            db.useEmulator("10.0.2.2", 8080)
        } catch (e: Exception) {
        }

        resenaRepository = ResenaRepository(
            ResenaFirestoreDataSourceImpl(db),
            UsuarioFirestoreDataSourceImpl(db),
            CarreraFirestoreDataSourceImpl(db),
            AuthRemoteDataSource(auth)
        )

        val batch = db.batch()
        repeat(10) { i ->
            val resena = generateResena(i)
            batch.set(db.collection("resenas").document(resena.id), resena)
        }
        batch.commit().await()
    }

    @Test
    fun getResenaById_validId_correctResena() = runTest {
        //AAA
        //Arrange
        val id = "resena_7"
        val expectedResena = generateResena(7)
        //Act
        val result = resenaRepository.getResenaById(id)
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        val resena = result.getOrNull()
        Truth.assertThat(resena?.id).isEqualTo(id)
        Truth.assertThat(resena?.resena).isEqualTo("Resena 7")
        Truth.assertThat(resena?.calificacion).isEqualTo("4.0") // Float -> String
        Truth.assertThat(resena?.carrera).isEqualTo("Carrera 7")
        Truth.assertThat(resena?.usuario).isEqualTo("Nombre 7")
        Truth.assertThat(resena?.userName).isEqualTo("username_7")
        Truth.assertThat(resena?.likes).isEqualTo(7)
    }

    @Test
    fun getResenaById_invalidId_returnFailure() = runTest {
        //AAA
        //Arrange
        val id = "resena_999"
        //Act
        val result = resenaRepository.getResenaById(id)
        //Assert
        Truth.assertThat(result.isFailure).isTrue()
        Truth.assertThat(result.exceptionOrNull()?.message).isEqualTo("Resena no encontrada")
    }

    @Test
    fun getResenaById_createdThreeDaysAgo_formattedAsHaceDias() = runTest {
        //Arrange
        guardarResena(generateResena(20, Instant.now().minus(Duration.ofDays(3))))
        //Act
        val result = resenaRepository.getResenaById("resena_20")
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()?.fechaPublicacion).isEqualTo("Hace 3 días")
    }

    @After
    fun tearDown() = runTest {
        val resenas = db.collection("resenas").get().await()
        for (resenaDoc in resenas) {
            val likes = resenaDoc.reference.collection("likes").get().await()
            for (like in likes) {
                like.reference.delete().await()
            }
            resenaDoc.reference.delete().await()
        }
    }
}