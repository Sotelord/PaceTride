package com.example.pacetride.viewmodels

import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.UsuarioRepository
import com.example.pacetride.ui.screens.registrar.RegisterViewModel
import com.google.common.truth.Truth.assertThat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class RegisterViewModelIntegrationTest {
    private lateinit var viewModel: RegisterViewModel
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        try {
            Firebase.auth.useEmulator("10.0.2.2", 9099)
            Firebase.firestore.useEmulator("10.0.2.2", 8080)
        } catch (e: Exception) {
        }

        authRepository = AuthRepository(AuthRemoteDataSource(Firebase.auth))
        val usuarioRepository = UsuarioRepository(
            UsuarioFirestoreDataSourceImpl(Firebase.firestore),
            AuthRemoteDataSource(Firebase.auth)
        )

        val scheduler = TestCoroutineScheduler()
        val dispatcher = StandardTestDispatcher(scheduler)

        Dispatchers.setMain(dispatcher)

        viewModel = RegisterViewModel(authRepository, usuarioRepository, dispatcher)
    }

    //Manera incorrecta porque implica cambiar nuestro código al momento de hacer test y pues no es la idea aunque es una alternativa
    //Mirar en injection el CorrutineModule y e injectar el dispatcher en el viewmodel respectivo
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
        //Act
        viewModel.registerUsuarioOnline()
        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isTrue()
        assertThat(state.mostrarMensajeError).isFalse()
        assertThat(state.errorMessage).isEmpty()
    }

    //Esta sería la forma correcta, ya que no implica cambiar nuestro código al momento de hacer test
    @Test
    fun register_success_createsUsuarioAndUpdateUI2() = runTest {
        //AAA
        //Arrange
        viewModel.updateUsuario("testUsuario")
        viewModel.updateName("Test")
        viewModel.updateEmail("test@test.com")
        viewModel.updatePassword("12345678")
        viewModel.updateConfirmPassword("12345678")
        viewModel.aceptarTerminos()
        //Act
        viewModel.registerButtonPressed()

        //Await hasta que navigate sea verdadero
        val navigate = viewModel.uiState.map { it.navigate }.first { it }

        assertThat(navigate).isTrue()

        //Assert
        val state = viewModel.uiState.value
        assertThat(state.navigate).isTrue()
        assertThat(state.mostrarMensajeError).isFalse()
        assertThat(state.errorMessage).isEmpty()
    }

    @Test
    fun register_alreadyUsedEmail_showErrorMessage() = runTest {
        //AAA
        authRepository.signUp("test@test.com", "12345678")
        //Arrange
        viewModel.updateUsuario("testUsuario")
        viewModel.updateName("Test")
        viewModel.updateEmail("test@test.com")
        viewModel.updatePassword("12345678")
        viewModel.updateConfirmPassword("12345678")
        viewModel.aceptarTerminos()
        //Act
        viewModel.registerButtonPressed()

        // Esperar a que aparezca el error
        val state = viewModel.uiState.first { it.errorMessage.isNotEmpty() }

        //Assert
        assertThat(state.navigate).isFalse()
        assertThat(state.errorMessage).isNotEmpty()
        assertThat(state.errorMessage).isEqualTo("Ya existe una cuenta con ese correo")
    }

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
        //Act
        viewModel.registerButtonPressed()

        // Esperar a que aparezca el error
        val state = viewModel.uiState.first { it.errorMessage.isNotEmpty() }

        //Assert
        assertThat(state.navigate).isFalse()
        assertThat(state.errorMessage).isNotEmpty()
        assertThat(state.errorMessage).isEqualTo("Ingresa un correo válido")
    }

    @After
    fun tearDown() = runTest {
        val usuario = Firebase.auth.currentUser
        if (usuario != null) {
            try {
                Firebase.firestore.collection("usuarios").document(usuario.uid).delete().await()
            } catch (e: Exception) {
                // el documento puede no existir (por ejemplo en el test de correo duplicado)
            }
            usuario.delete().await()
        }
        Firebase.auth.signOut()
    }
}