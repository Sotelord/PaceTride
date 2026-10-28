package com.example.pacetride

import android.util.Log
import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.ResenaFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.dtos.EstadisticasGlobalesDto
import com.example.pacetride.data.dtos.ResenaCarreraDto
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.example.pacetride.data.dtos.ResenaUsuarioDto
import com.example.pacetride.data.dtos.UsuarioFirestoreDto
import com.example.pacetride.data.repository.UsuarioRepository
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
import java.time.Instant
import java.util.Date

class UsuarioRepositoryIntegrationTest {
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private fun generateUsuario(i : Int): UsuarioFirestoreDto = UsuarioFirestoreDto(
        id = "user_$i",
        nombre = "Nombre $i",
        userName = "username_$i",
        email = "user$i@example.com",
        ubicacion = "Ubicacion $i",
        bio = "Bio $i",
        fotoPerfil = "https://picsum.photos/200/300?random=$i",
        estadisticasGlobales = EstadisticasGlobalesDto(
            distancia = "0km",
            numCarrera = 0,
            mejorTiempo10k = "0:00",
            mejorTiempo21k = "0:00"
        ),
        followingCount = i * 10,
        followersCount = i * 5,
        followed = false
    )

    private suspend fun guardarUsuario(usuario: UsuarioFirestoreDto) {
        db.collection("usuarios").document(usuario.id).set(usuario).await()
    }

    private fun generateResena(
        autor: UsuarioFirestoreDto,
        id: String,
        creadaEn: Instant,
        calificacion: Float = 4f
    ) = ResenaFirestoreDto(
        usuarioId = autor.id,
        carreraId = "carrera_1",
        id = id,
        resena = "Resena $id",
        calificacion = calificacion,
        categoriasDestacadas = listOf("Ruta"),
        likes = 3,
        createdAt = Timestamp(Date.from(creadaEn)),
        updatedAt = Timestamp(Date.from(creadaEn)),
        carrera = ResenaCarreraDto("Carrera 10K"),
        usuario = ResenaUsuarioDto(autor.nombre, autor.fotoPerfil, autor.userName)
    )

    private suspend fun guardarResena(resena: ResenaFirestoreDto) {
        db.collection("resenas").document(resena.id).set(resena).await()
    }

    private lateinit var usuarioRepository: UsuarioRepository

    @Before
    fun setUp() = runTest {
        try {
            db.useEmulator("10.0.2.2", 8080)
        } catch (e: Exception) {

        }

        usuarioRepository =
            UsuarioRepository(UsuarioFirestoreDataSourceImpl(db), AuthRemoteDataSource(auth))

        //Replicables
        //Autonomas
        val batch = db.batch()
        repeat(10) { i ->
            val usuario = generateUsuario(i)
            batch.set(db.collection("usuarios").document(usuario.id), usuario)
        }
        Log.d("usuarios", "setUp: Antes de batch")
        batch.commit().await()
        Log.d("usuarios", "setUp: Despues de batch")
    }

    //dto -> mapper ->ui
    @Test
    fun getUsuarioById_validId_correctUsuario() = runTest {
        //AAA
        //Arrange
        val id = "user_7"
        val expectedName = "Nombre 7"
        //Act
        val result = usuarioRepository.getUsuarioById(id)
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()?.nombre).isEqualTo(expectedName)
    }

    @Test
    fun getUsuarioById_invalidId_returnFailure() = runTest {
        //AAA
        //Arrange
        val id = "user_999"
        //Act
        val result = usuarioRepository.getUsuarioById(id)
        //Assert
        Truth.assertThat(result.isFailure).isTrue()
        Truth.assertThat(result.exceptionOrNull()?.message).isEqualTo("Usuario no encontrado")
    }
    //mapeos raros por ejemplo transformar una fecha, un numero a un String con un formato especifico

    @Test
    fun getUsuarioById_bioVaciaAndUbicacionNula_returnsDefaultText() = runTest {
        //AAA
        //Arrange
        guardarUsuario(generateUsuario(20).copy(bio = "", ubicacion = null))
        //Act
        val result = usuarioRepository.getUsuarioById("user_20")
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()?.bio).isEqualTo("No hay biografía")
        Truth.assertThat(result.getOrNull()?.ubicacion).isEqualTo("No hay ubicación")
    }


    @After
    fun tearDown() = runTest {
        //Delete usuarios
        val usuarios = db.collection("usuarios").get().await()
        for (usuarioDoc in usuarios) {
            val followers = usuarioDoc.reference.collection("followers").get().await()
            for (follower in followers) {
                follower.reference.delete().await()
            }
            val following = usuarioDoc.reference.collection("following").get().await()
            for (follow in following) {
                follow.reference.delete().await()
            }
            usuarioDoc.reference.delete().await()
        }

        //Delete resenas
        val resenas = db.collection("resenas").get().await()
        for(resenaDoc in resenas){
            val likes = resenaDoc.reference.collection("likes").get().await()
            for (like in likes){
                like.reference.delete().await()
            }
            resenaDoc.reference.delete().await()
        }
    }
}