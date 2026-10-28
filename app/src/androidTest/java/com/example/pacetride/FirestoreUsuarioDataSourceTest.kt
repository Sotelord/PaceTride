package com.example.pacetride

import android.util.Log
import com.example.pacetride.data.datasource.impl.firestore.ResenaFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.dtos.CreateResenaCarreraDto
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.CreateResenaUsuarioDto
import com.example.pacetride.data.dtos.EstadisticasGlobalesDto
import com.example.pacetride.data.dtos.RegisterUserDto
import com.example.pacetride.data.dtos.UpdateUserDto
import com.example.pacetride.data.dtos.UsuarioFirestoreDto
import com.google.common.truth.Truth
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class FirestoreUsuarioDataSourceTest {

    private val db = Firebase.firestore
    private lateinit var dataSource: UsuarioFirestoreDataSourceImpl

    private lateinit var resenaDataSource: ResenaFirestoreDataSourceImpl

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

    @Before
    fun setUp() = runTest {
        try {
            db.useEmulator("10.0.2.2", 8080)
        } catch (e: Exception){

        }
        dataSource = UsuarioFirestoreDataSourceImpl(db)
        resenaDataSource = ResenaFirestoreDataSourceImpl(db)

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

    @Test
    fun getUsuarioById_validId_correctUsuario() = runTest {
        //AAA
        //Arrange
        val id = "user_7"
        val expectedName = "Nombre 7"
        //Act
        val result = dataSource.getUsuarioById(id, "")
        //Assert
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.nombre).isEqualTo(expectedName)
        Truth.assertThat(result?.id).isEqualTo(id)
    }

    @Test
    fun getUsuarioById_InvalidId_null() = runTest {
        //AAA
        //Arrange
        val id = "user_777"
        //Act
        val result = dataSource.getUsuarioById(id, "")
        //Assert
        Truth.assertThat(result).isNull()
    }

    @Test
    fun registerUser_insertDocument_DocumentExists() = runTest {
        //AAA
        //Arrange
        val user = RegisterUserDto(
            nombre = "Nombre",
            userName = "username",
            bio = "Bio",
            ubicacion = "Ubicacion",
            estadisticasGlobales = EstadisticasGlobalesDto(
                distancia = "0km",
                numCarrera = 0,
                mejorTiempo10k = "0:00",
                mejorTiempo21k = "0:00"
            ),
            followersCount = 0,
            followingCount = 0,
            fcmToken = "token"
        )
        //Act
        dataSource.registerUsuario(user, "999")
        //Assert
        val result = dataSource.getUsuarioById("999", "")
        //Assert
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result?.nombre).isEqualTo("Nombre")
        Truth.assertThat(result?.id).isEqualTo("999")
    }

    @Test
    fun followOrUnfollowUsuario_followUser_UserFollowed() = runTest {
        //AAA
        //Arrange
        val currentUser = generateUsuario(1)
        val targetUser = generateUsuario(2)
        //Act
        dataSource.followOrUnfollowUsuario(currentUser.id, targetUser.id)
        //Assert
        val targetUsuarioResult = dataSource.getUsuarioById(targetUser.id, currentUser.id)
        Truth.assertThat(targetUsuarioResult?.followed).isTrue()
    }

    @Test
    fun followOrUnfollowUsuario_followUser_followCountIncremented() = runTest {
        //AAA
        //Arrange
        val currentUser = generateUsuario(1)
        val targetUser = generateUsuario(2)
        val oldData = dataSource.getUsuarioById(targetUser.id)
        //Act
        dataSource.followOrUnfollowUsuario(currentUser.id, targetUser.id)
        //Assert
        val targetUsuarioResult = dataSource.getUsuarioById(targetUser.id, currentUser.id)
        Truth.assertThat(targetUsuarioResult?.followersCount).isGreaterThan(oldData?.followersCount)
    }

    @Test
    fun followOrUnfollowUsuario_unfollow_followedFalse() = runTest {
        //AAA
        //Arrange
        val currentUser = generateUsuario(1)
        val targetUser = generateUsuario(2)
        //Act
        dataSource.followOrUnfollowUsuario(currentUser.id, targetUser.id)
        dataSource.followOrUnfollowUsuario(currentUser.id, targetUser.id)
        //Assert
        val targetUsuarioResult = dataSource.getUsuarioById(targetUser.id, currentUser.id)
        Truth.assertThat(targetUsuarioResult?.followed).isFalse()
    }

    @Test
    fun getReviewsUsuarioId_validId_correctReviews() = runTest {
        //AAA
        //Arrange
        val usuario = generateUsuario(1)
        val createResenaDto = CreateResenaDto(
            usuarioId = usuario.id,
            carreraId = "carrera_1",
            resena = "Resena",
            calificacion = 5.0f,
            categoriasDestacadas = listOf("Categoria 1", "Categoria 2"),
            usuario = CreateResenaUsuarioDto(
                fotoPerfil = usuario.fotoPerfil,
                nombre = usuario.nombre,
                usuario = usuario.userName
            ),
            carrera = CreateResenaCarreraDto(
                nombre = "Nombre"
            )
        )
        //Act
        resenaDataSource.createResena(createResenaDto)
        //Assert
        val resenas = dataSource.getReviewsUsuarioId(usuario.id).map { it.toResena() }
        Truth.assertThat(resenas).isNotEmpty()
        Truth.assertThat(resenas.first().resena).isEqualTo(createResenaDto.resena)
    }

    @Test
    fun updateUsuario_updateBioAndUbicacion_changesFieldsAndKeepOtherFields() = runTest {
        //AAA
        //Arrange
        val id = "user_7"
        val cambios = UpdateUserDto(
            bio = "Bio modificado",
            ubicacion = "Ubicacion modificada"
        )
        //Act
        dataSource.updateUsuario(id, cambios)
        //Assert
        val result = dataSource.getUsuarioById(id, "")
        Truth.assertThat(result?.bio).isEqualTo(cambios.bio)
        Truth.assertThat(result?.ubicacion).isEqualTo(cambios.ubicacion)
        Truth.assertThat(result?.nombre).isEqualTo("Nombre 7")
    }

    @After
    fun tearDown() = runTest {
        //Delete usuarios
        val usuarios = db.collection("usuarios").get().await()
        for(usuarioDoc in usuarios){
            val followers = usuarioDoc.reference.collection("followers").get().await()
            for (follower in followers){
                follower.reference.delete().await()
            }
            val following = usuarioDoc.reference.collection("following").get().await()
            for (follow in following){
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