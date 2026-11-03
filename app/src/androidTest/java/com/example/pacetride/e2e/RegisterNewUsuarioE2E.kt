package com.example.pacetride.e2e

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import com.example.pacetride.MainActivity
import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.UsuarioRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class RegisterNewUsuarioE2E {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var authRepository: AuthRepository
    private lateinit var usuarioRepository: UsuarioRepository

    @Before
    fun setUp(){
        hiltRule.inject()
        try {
            Firebase.auth.useEmulator("10.0.2.2", 9099)
            Firebase.firestore.useEmulator("10.0.2.2", 8080)
        } catch (e: Exception) {

        }

        val authRemoteDataSource = AuthRemoteDataSource(Firebase.auth)
        val usuarioRemoteDataSource = UsuarioFirestoreDataSourceImpl(Firebase.firestore)
        authRepository = AuthRepository(authRemoteDataSource)
        usuarioRepository = UsuarioRepository(usuarioRemoteDataSource, authRemoteDataSource)

        runBlocking {
            authRepository.signUp("test@test.com", "12345678")
            authRepository.signOut()
        }
    }

    @Test
    fun navigate_fromStart_toLogin() {
        composeRule.onNodeWithTag("login_button").performClick()

        composeRule.onNodeWithTag("login_screen").assertIsDisplayed()
    }

    @Test
    fun registerUsuario_shortPassword_showMessage(){
        composeRule.onNodeWithTag("create_account_button").performClick()
        composeRule.onNodeWithTag("txtNombre").performTextInput("David")
        composeRule.onNodeWithTag("txtEmail").performTextInput("david@test.com")
        composeRule.onNodeWithTag("txtUsuario").performTextInput("david")
        composeRule.onNodeWithTag("txtPassword").performTextInput("1234")
        composeRule.onNodeWithTag("txtConfirmPassword").performTextInput("1234")
        composeRule.onNodeWithTag("chkTerms").performScrollTo().performClick()

        composeRule.onNodeWithTag("btnRegister").performScrollTo().performClick()

        composeRule.onNodeWithTag("errorMessage").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun registerUsuario_usedEmail_showMessage(){
        composeRule.onNodeWithTag("create_account_button").performClick()
        composeRule.onNodeWithTag("txtNombre").performTextInput("David")
        composeRule.onNodeWithTag("txtEmail").performTextInput("test@test.com")
        composeRule.onNodeWithTag("txtUsuario").performTextInput("david")
        composeRule.onNodeWithTag("txtPassword").performTextInput("12345678")
        composeRule.onNodeWithTag("txtConfirmPassword").performTextInput("12345678")
        composeRule.onNodeWithTag("chkTerms").performScrollTo().performClick()

        composeRule.onNodeWithTag("btnRegister").performScrollTo().performClick()

        composeRule.waitUntil(timeoutMillis = 5000) {
            composeRule.onAllNodesWithTag("errorMessage").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag("errorMessage").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun registerUsuario_allValidInputs_navigateHome(){
        composeRule.onNodeWithTag("create_account_button").performClick()
        composeRule.onNodeWithTag("txtNombre").performTextInput("David")
        composeRule.onNodeWithTag("txtEmail").performTextInput("prueba@test.com")
        composeRule.onNodeWithTag("txtUsuario").performTextInput("david")
        composeRule.onNodeWithTag("txtPassword").performTextInput("12345678")
        composeRule.onNodeWithTag("txtConfirmPassword").performTextInput("12345678")
        composeRule.onNodeWithTag("chkTerms").performScrollTo().performClick()

        composeRule.onNodeWithTag("btnRegister").performScrollTo().performClick()

        composeRule.waitUntil(timeoutMillis = 300000) {
            composeRule.onAllNodesWithTag("homeScreen").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag("homeScreen").assertIsDisplayed()
    }

    @After
    fun tearDown() = runBlocking {
        // 1. Usuario creado durante el test (registro nuevo), si lo hay
        Firebase.auth.currentUser?.let { usuario ->
            try {
                Firebase.firestore.collection("usuarios").document(usuario.uid).delete().await()
            } catch (e: Exception) { }
            try { usuario.delete().await() } catch (e: Exception) { }
        }
        Firebase.auth.signOut()

        // 2. Usuario precargado en el setUp
        try {
            Firebase.auth.signInWithEmailAndPassword("test@test.com", "12345678").await()
            Firebase.auth.currentUser?.delete()?.await()
        } catch (e: Exception) { }
        Firebase.auth.signOut()
    }
}