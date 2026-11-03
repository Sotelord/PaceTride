package com.example.pacetride.repository

import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.dtos.EstadisticasGlobalesDto
import com.example.pacetride.data.dtos.UsuarioFirestoreDto
import com.example.pacetride.data.repository.UsuarioRepository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UsuarioRepositoryTest {

    private val mockUsuarioDataSource = mockk<UsuarioFirestoreDataSourceImpl>()
    private val mockAuthDataSource = mockk<AuthRemoteDataSource>()

    private val repository = UsuarioRepository(
        usuarioRemoteDataSource = mockUsuarioDataSource,
        authRemoteDataSource = mockAuthDataSource
    )

    @Test
    fun `Al llamar getUsuarioById, si el id es valido, retorna un Result con el usuario`() =
        runTest {
            //AAA
            val dto = UsuarioFirestoreDto(
                id = "123",
                nombre = "David",
                userName = "Sotelord",
                email = "sotelord@example.com",
                ubicacion = "Cajicá",
                bio = "Sientete libre",
                fotoPerfil = "https://picsum.photos/200/300?random=1",
                estadisticasGlobales = EstadisticasGlobalesDto(
                    distancia = "10km",
                    numCarrera = 10,
                    mejorTiempo10k = "10:00",
                    mejorTiempo21k = "10:00"
                ),
                followingCount = 10,
                followersCount = 2,
                followed = false
            )
            //Arrange
            coEvery { mockAuthDataSource.currentUser?.uid } returns "1"
            coEvery { mockUsuarioDataSource.getUsuarioById("123", "1") } returns dto
            //Act
            val result = repository.getUsuarioById("123")
            //Assert
            Truth.assertThat(result.isSuccess).isTrue()
            Truth.assertThat(result.getOrNull()?.id).isEqualTo("123")
            Truth.assertThat(result.getOrNull()?.nombre).isEqualTo("David")
        }

    @Test
    fun `Al llamar getUsuarioById, si el id es invalido, retorna un Result con failure`() =
        runTest {
            //AAA
            //Arrange
            coEvery { mockAuthDataSource.currentUser?.uid } returns "1"
            coEvery { mockUsuarioDataSource.getUsuarioById("123", "1") } returns null
            //Act
            val result = repository.getUsuarioById("123")
            //Assert
            Truth.assertThat(result.isFailure).isTrue()
            Truth.assertThat(result.exceptionOrNull()?.message).isEqualTo("Usuario no encontrado")
        }

    @Test
    fun `Al llamar getUsuarioById, si el datasource devuelve campos vacios de ubicacion y bio se mapean correctamente cuando son string vacios`() = runTest {
        //AAA
        val dto = UsuarioFirestoreDto(
            id= "123",
            nombre = "",
            userName = "",
            email = "",
            ubicacion = "",
            bio = "",
            fotoPerfil = null,
            estadisticasGlobales = EstadisticasGlobalesDto(
                distancia = "0km",
                numCarrera = 0,
                mejorTiempo10k = "0:00",
                mejorTiempo21k = "0:00"
            ),
            followingCount = 0,
            followersCount = 0,
            followed = false
        )
        //Arrange
        coEvery { mockAuthDataSource.currentUser?.uid } returns "1"
        coEvery { mockUsuarioDataSource.getUsuarioById("123", "1") } returns dto
        //Act
        val result = repository.getUsuarioById("123")
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()?.id).isEqualTo("123")
        Truth.assertThat(result.getOrNull()?.bio).isEqualTo("No hay biografía")
        Truth.assertThat(result.getOrNull()?.ubicacion).isEqualTo("No hay ubicación")
    }

    @Test
    fun `Al llamar getUsuarioById, si el datasource devuelve campos vacios de ubicacion y bio se mapean correctamente cuando son null`() = runTest {
        //AAA
        val dto = UsuarioFirestoreDto(
            id= "123",
            nombre = "",
            userName = "",
            email = "",
            ubicacion = null,
            bio = null,
            fotoPerfil = null,
            estadisticasGlobales = EstadisticasGlobalesDto(
                distancia = "0km",
                numCarrera = 0,
                mejorTiempo10k = "0:00",
                mejorTiempo21k = "0:00"
            ),
            followingCount = 0,
            followersCount = 0,
            followed = false
        )
        //Arrange
        coEvery { mockAuthDataSource.currentUser?.uid } returns "1"
        coEvery { mockUsuarioDataSource.getUsuarioById("123", "1") } returns dto
        //Act
        val result = repository.getUsuarioById("123")
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()?.id).isEqualTo("123")
        Truth.assertThat(result.getOrNull()?.bio).isEqualTo("No hay biografía")
        Truth.assertThat(result.getOrNull()?.ubicacion).isEqualTo("No hay ubicación")
    }
}