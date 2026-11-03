package com.example.pacetride.e2e

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import com.example.pacetride.MainActivity
import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.UsuarioRepository
import com.example.pacetride.navigation.Screen
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
class ModifyUsuarioE2E {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createEmptyComposeRule()

    private lateinit var scenario: ActivityScenario<MainActivity>

    private lateinit var authRepository: AuthRepository
    private lateinit var usuarioRepository: UsuarioRepository

    @Before
    fun setUp() {
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
            authRepository.signUp("prueba@prueba.com", "12345678")
            val usuarioId = authRepository.currentUser?.uid ?: return@runBlocking

            usuarioRepository.registerUsuario(
                nombre = "David",
                userName = "david",
                bio = "Bio",
                ubicacion = "Ubicacion",
                userId = usuarioId
            )
        }

        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun updateUsuario_allValidInputs_infoUsuarioUpdated() = runTest {
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("homeScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("homeScreen").assertIsDisplayed()

        composeRule.onNodeWithTag("nav_${Screen.Profile.route}").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("profileScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("profileScreen").assertIsDisplayed()

        composeRule.onNodeWithTag("editProfileButton").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("editProfileScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("editProfileScreen").assertIsDisplayed()

        composeRule.onNodeWithTag("txtUbicacion").assertTextContains("Ubicacion", substring = true)

        composeRule.onNodeWithTag("txtUbicacion").performTextClearance()

        composeRule.onNodeWithTag("txtUbicacion").performTextInput("modificado")
        composeRule.onNodeWithTag("btnGuardar").performClick()

        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("profileScreen").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag("nav_${Screen.Home.route}").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("homeScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("homeScreen").assertIsDisplayed()

        composeRule.onNodeWithTag("nav_${Screen.Profile.route}").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("profileScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("profileScreen").assertIsDisplayed()

        composeRule.onNodeWithTag("editProfileButton").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("editProfileScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("editProfileScreen").assertIsDisplayed()

        composeRule.onNodeWithTag("txtUbicacion").assertTextContains("modificado", substring = true)
    }

    @After
    fun tearDown() = runTest {
        scenario.close()
        Firebase.auth.currentUser?.let { usuario ->
            try {
                Firebase.firestore.collection("usuarios").document(usuario.uid).delete().await()
            } catch (e: Exception) {
            }
            try {
                usuario.delete().await()
            } catch (e: Exception) {
            }
        }
        Firebase.auth.signOut()
    }
}