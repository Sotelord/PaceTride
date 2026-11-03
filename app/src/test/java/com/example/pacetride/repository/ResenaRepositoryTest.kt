package com.example.pacetride.repository

import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.CarreraFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.ResenaFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.dtos.CreateResenaCarreraDto
import com.example.pacetride.data.dtos.CreateResenaDto
import com.example.pacetride.data.dtos.CreateResenaUsuarioDto
import com.example.pacetride.data.dtos.EstadisticasGlobalesDto
import com.example.pacetride.data.dtos.ResenaCarreraDto
import com.example.pacetride.data.dtos.ResenaFirestoreDto
import com.example.pacetride.data.dtos.ResenaUsuarioDto
import com.example.pacetride.data.dtos.UpdateResenaDto
import com.example.pacetride.data.dtos.UsuarioFirestoreDto
import com.example.pacetride.data.repository.ResenaRepository
import com.google.common.truth.Truth
import com.google.firebase.Timestamp
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ResenaRepositoryTest {
    private val mockResenaDataSource = mockk<ResenaFirestoreDataSourceImpl>()
    private val mockUsuarioDataSource = mockk<UsuarioFirestoreDataSourceImpl>()
    private val mockCarreraDataSource = mockk<CarreraFirestoreDataSourceImpl>()
    private val mockAuthDataSource = mockk<AuthRemoteDataSource>()

    private val repository = ResenaRepository(
        resenaRemoteDataSource = mockResenaDataSource,
        usuarioRemoteDataSource = mockUsuarioDataSource,
        carreraRemoteDataSource = mockCarreraDataSource,
        authRemoteDataSource = mockAuthDataSource
    )

    @Test
    fun `Al llamar getResenaById, si el id es valido, retorna un Result con la resena`() = runTest {
        //AAA
        val dtoUsuario = ResenaUsuarioDto(
            nombre = "Santiago Rayo",
            fotoPerfil = "https://media.istockphoto.com/id/545805760/photo/man-runner-jogger-running-isolated.jpg?s=612x612&w=0&k=20&c=h_yH1K2Ou_b6fjL8At0TY2wV5rhasGFNu4sdFVZW54A="
        )
        val dtoCarrera = ResenaCarreraDto(
            nombre = "Corre por Bogotá 5K"
        )
        val dtoResena = ResenaFirestoreDto(
            usuarioId = "1",
            carreraId = "2",
            id = "3",
            resena = "Buena organización en general, pero la entrega de kits fue un poco lenta. El recorrido es plano y rápido, perfecto para buscar una mejor marca personal.",
            calificacion = 4f,
            categoriasDestacadas = listOf("Organización", "Seguridad"),
            likes = 1,
            createdAt = null,
            updatedAt = null,
            carrera = dtoCarrera,
            usuario = dtoUsuario,
            liked = false
        )
        //Arrange
        coEvery { mockAuthDataSource.currentUser?.uid } returns "1"
        coEvery { mockResenaDataSource.getResenasById("3", "1") } returns dtoResena
        //Act
        val result = repository.getResenaById("3")
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()?.id).isEqualTo("3")
        Truth.assertThat(result.getOrNull()?.resena)
            .isEqualTo("Buena organización en general, pero la entrega de kits fue un poco lenta. El recorrido es plano y rápido, perfecto para buscar una mejor marca personal.")
    }

    @Test
    fun `Al llamar getResenaById, si el id es invalido, retorna un Result con failure`() = runTest {
        //AAA
        //Arrange
        coEvery { mockAuthDataSource.currentUser?.uid } returns "1"
        coEvery { mockResenaDataSource.getResenasById("3", "1") } returns null
        //Act
        val result = repository.getResenaById("3")
        //Assert
        Truth.assertThat(result.isFailure).isTrue()
        Truth.assertThat(result.exceptionOrNull()?.message).isEqualTo("Resena no encontrada")
    }

    @Test
    fun `Al llamar getResenaById, la fecha se formatea correctamente`() = runTest {
        //AAA
        //AAA
        val dtoUsuario = ResenaUsuarioDto(
            nombre = "Santiago Rayo",
            fotoPerfil = "https://media.istockphoto.com/id/545805760/photo/man-runner-jogger-running-isolated.jpg?s=612x612&w=0&k=20&c=h_yH1K2Ou_b6fjL8At0TY2wV5rhasGFNu4sdFVZW54A="
        )
        val dtoCarrera = ResenaCarreraDto(
            nombre = "Corre por Bogotá 5K"
        )
        val dtoResena = ResenaFirestoreDto(
            usuarioId = "1",
            carreraId = "2",
            id = "3",
            resena = "Buena organización en general, pero la entrega de kits fue un poco lenta. El recorrido es plano y rápido, perfecto para buscar una mejor marca personal.",
            calificacion = 4f,
            categoriasDestacadas = listOf("Organización", "Seguridad"),
            likes = 1,
            createdAt = Timestamp.now(),
            updatedAt = Timestamp.now(),
            carrera = dtoCarrera,
            usuario = dtoUsuario,
            liked = false
        )
        //Arrange
        coEvery { mockAuthDataSource.currentUser?.uid } returns "1"
        coEvery { mockResenaDataSource.getResenasById("3", "1") } returns dtoResena
        //Act
        val result = repository.getResenaById("3")
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()?.id).isEqualTo("3")
        Truth.assertThat(result.getOrNull()?.fechaPublicacion).isEqualTo("Hace un momento")
    }

    @Test
    fun `Al llamar deleteResena, si el id es valido, retorna un Result con success`() = runTest {
        //AAA
        //Arrange
        coEvery { mockResenaDataSource.deleteResena("3") } returns Unit
        //Act
        val result = repository.deleteResena("3")
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Al llamar updateResena, con los datos validos, retorna un Result con success`() = runTest {
        //AAA
        val dtoUpdateResena = UpdateResenaDto(
            resena = "Buena organización en general, pero la entrega de kits fue un poco lenta. El recorrido es plano y rápido, perfecto para buscar una mejor marca personal.",
            calificacion = 4f,
            categoriasDestacadas = listOf("Organización", "Seguridad")
        )
        //Arrange
        coEvery { mockResenaDataSource.updateResena("3", dtoUpdateResena) } returns Unit
        //Act
        val result = repository.updateResena(
            "3",
            dtoUpdateResena.resena,
            dtoUpdateResena.calificacion.toString(),
            dtoUpdateResena.categoriasDestacadas
        )
        //Assert
        Truth.assertThat(result.isSuccess).isTrue()
        Truth.assertThat(result.getOrNull()).isEqualTo(Unit)
    }
}