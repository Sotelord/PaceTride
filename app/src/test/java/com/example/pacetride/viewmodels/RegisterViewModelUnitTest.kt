package com.example.pacetride.viewmodels

import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.UsuarioRepository
import com.example.pacetride.ui.screens.registrar.RegisterViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class RegisterViewModelUnitTest {
    private lateinit var viewModel: RegisterViewModel

    private lateinit var authRepository: AuthRepository
    private lateinit var usuarioRepository: UsuarioRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        authRepository = mockk()
        usuarioRepository = mockk()
        viewModel = RegisterViewModel(authRepository, usuarioRepository, StandardTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun register_success_createsUsuarioAndUpdateUI() = runTest {
        //AAA
        //Arrange
        viewModel.updateUsuario("testUsuario")
        viewModel.updateName("Test")
        viewModel.updateEmail("test@test.com")
        viewModel.updatePassword("12345678")
        viewModel.updateConfirmPassword("12345678")
        viewModel.aceptarTerminos()

        coEvery { authRepository.signUp(any(), any()) } returns Result.success(Unit)
        coEvery { authRepository.currentUser?.uid } returns "1"
        coEvery {
            usuarioRepository.registerUsuario(
                any(),
                any(),
                any(),
                any(),
                any()
            )
        } returns Result.success(Unit)

        //Act
        viewModel.registerUsuarioOnline()
        //Esperar a que todo acabe
        advanceUntilIdle()
        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isTrue()
        assertThat(state.mostrarMensajeError).isFalse()
        assertThat(state.errorMessage).isEmpty()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun register_alreadyUsedEmail_showErrorMessage() = runTest {
        //AAA
        //Arrange
        viewModel.updateUsuario("testUsuario")
        viewModel.updateName("Test")
        viewModel.updateEmail("test@test.com")
        viewModel.updatePassword("12345678")
        viewModel.updateConfirmPassword("12345678")
        viewModel.aceptarTerminos()

        coEvery { authRepository.signUp(any(), any()) } returns Result.failure(Exception("Ya existe una cuenta con ese correo"))
        //Act
        viewModel.registerButtonPressed()
        advanceUntilIdle()

        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isFalse()
        assertThat(state.errorMessage).isEqualTo("Ya existe una cuenta con ese correo")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun register_emailInvalid_showErrorMessage() = runTest {
        //AAA
        //Arrange
        viewModel.updateUsuario("testUsuario")
        viewModel.updateName("Test")
        viewModel.updateEmail("testtest.com")
        viewModel.updatePassword("12345678")
        viewModel.updateConfirmPassword("12345678")
        viewModel.aceptarTerminos()
        coEvery { authRepository.signUp(any(), any()) } returns Result.failure(Exception("Ingresa un correo válido"))
        //Act
        viewModel.registerButtonPressed()
        advanceUntilIdle()
        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isFalse()
        assertThat(state.errorMessage).isEqualTo("Ingresa un correo válido")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun register_shortPassword_showErrorMessage() = runTest {
        //AAA
        //Arrange
        viewModel.updateUsuario("testUsuario")
        viewModel.updateName("Test")
        viewModel.updateEmail("test@test.com")
        viewModel.updatePassword("1234")
        viewModel.updateConfirmPassword("1234")
        viewModel.aceptarTerminos()
        //Act
        coEvery { authRepository.signUp(any(), any()) } returns Result.failure(Exception("La contraseña debe tener al menos 7 caracteres"))
        viewModel.registerButtonPressed()
        advanceUntilIdle()
        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isFalse()
        assertThat(state.errorMessage).isEqualTo("La contraseña debe tener al menos 7 caracteres")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun register_passwordsDontMatch_showErrorMessage() = runTest {
        //AAA
        //Arrange
        viewModel.updateUsuario("testUsuario")
        viewModel.updateName("Test")
        viewModel.updateEmail("test@test.com")
        viewModel.updatePassword("12345678")
        viewModel.updateConfirmPassword("87654321")
        viewModel.aceptarTerminos()
        //Act
        coEvery { authRepository.signUp(any(), any()) } returns Result.failure(Exception("Las contraseñas no coinciden"))
        viewModel.registerButtonPressed()
        advanceUntilIdle()
        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isFalse()
        assertThat(state.errorMessage).isEqualTo("Las contraseñas no coinciden")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun register_termsNotAccepted_showErrorMessage() = runTest {
        //AAA
        //Arrange
        viewModel.updateUsuario("testUsuario")
        viewModel.updateName("Test")
        viewModel.updateEmail("test@test.com")
        viewModel.updatePassword("12345678")
        viewModel.updateConfirmPassword("12345678")
        // no se llama aceptarTerminos()
        //Act
        coEvery { authRepository.signUp(any(), any()) } returns Result.failure(Exception("Debes aceptar los términos y condiciones"))
        viewModel.registerButtonPressed()
        advanceUntilIdle()
        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isFalse()
        assertThat(state.errorMessage).isEqualTo("Debes aceptar los términos y condiciones")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun register_emptyFields_showErrorMessage() = runTest {
        //AAA
        //Arrange
        viewModel.updateEmail("test@test.com")
        //Act
        coEvery { authRepository.signUp(any(), any()) } returns Result.failure(Exception("Todos los campos obligatorios deben ser rellenados"))
        viewModel.registerButtonPressed()
        advanceUntilIdle()
        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isFalse()
        assertThat(state.loading).isFalse()
        assertThat(state.errorMessage).isEqualTo("Todos los campos obligatorios deben ser rellenados")
    }
}