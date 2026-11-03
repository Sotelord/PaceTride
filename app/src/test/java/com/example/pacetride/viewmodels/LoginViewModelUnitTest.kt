package com.example.pacetride.viewmodels

import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.UsuarioRepository
import com.example.pacetride.ui.screens.login.LoginViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class LoginViewModelUnitTest {
    private lateinit var viewModel: LoginViewModel
    private lateinit var authRepository: AuthRepository
    private lateinit var usuarioRepository: UsuarioRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        authRepository = mockk()
        usuarioRepository = mockk()
        viewModel = LoginViewModel(authRepository, usuarioRepository, StandardTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun login_success_navigateTrue() = runTest {
        //AAA
        //Arrange
        viewModel.updateEmail("test@test.com")
        viewModel.updatePassword("12345678")
        //Act
        coEvery { authRepository.signIn(any(), any()) } returns Result.success(Unit)
        coEvery { authRepository.currentUser?.uid } returns "1"
        coEvery { usuarioRepository.saveFcmToken(any()) } returns Result.success(Unit)
        viewModel.loginButtonPressed()
        advanceUntilIdle()
        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isTrue()
        assertThat(state.mostrarMensajeError).isFalse()
        assertThat(state.errorMessage).isEmpty()
    }
}