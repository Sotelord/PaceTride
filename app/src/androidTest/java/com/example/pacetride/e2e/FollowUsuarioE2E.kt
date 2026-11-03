package com.example.pacetride.e2e

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createEmptyComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.test.core.app.ActivityScenario
import com.example.pacetride.MainActivity
import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.CarreraFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.ResenaFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.ResenaRepository
import com.example.pacetride.data.repository.UsuarioRepository
import com.example.pacetride.navigation.Screen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FollowUsuarioE2E {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createEmptyComposeRule()

    private lateinit var scenario: ActivityScenario<MainActivity>

    private lateinit var authRepository: AuthRepository
    private lateinit var usuarioRepository: UsuarioRepository
    private lateinit var resenaRepository: ResenaRepository
    private val carreraId = "e2e-carrera"
    private val password = "12345678"
    private val autorEmail = "autor@test.com"
    private val seguidorEmail = "seguidor@test.com"
    private val textoResena = "Muy buena carrera"

    private val carrera = CarreraDto(
        id = carreraId,
        raceImageUrl = null,
        nombre = "Carrera E2E 3K",
        fecha = "2026-12-20",
        ubicacion = "Parque E2E",
        distanciasDisponiblesKm = listOf(3),
        precioBase = 40000,
        distanciaReferenciaKm = 3,
        ultimosCupos = false,
        descripcion = "Carrera de prueba para los tests E2E.",
        createdAt = "2026-10-03T19:31:37.551Z",
        updatedAt = "2026-10-03T19:31:37.551Z"
    )

    private suspend fun limpiarSembrado() {
        val db = Firebase.firestore
        Firebase.auth.signOut()

        // Autora: reseñas (con sus likes), carrera y sus datos
        runCatching { Firebase.auth.signInWithEmailAndPassword(autorEmail, password).await() }
        runCatching {
            val resenas =
                db.collection("resenas").whereEqualTo("carreraId", carreraId).get().await()
            for (doc in resenas.documents) {
                doc.reference.collection("likes").get()
                    .await().documents.forEach { it.reference.delete().await() }
                doc.reference.delete().await()
            }
        }
        runCatching { db.collection("carreras").document(carreraId).delete().await() }
        Firebase.auth.currentUser?.let { u ->
            for (sub in listOf("followers", "following")) {
                runCatching {
                    db.collection("usuarios").document(u.uid).collection(sub).get()
                        .await().documents.forEach { it.reference.delete().await() }
                }
            }
            runCatching { db.collection("usuarios").document(u.uid).delete().await() }
            runCatching { u.delete().await() }
        }
        Firebase.auth.signOut()

        // Usuario de la prueba
        runCatching { Firebase.auth.signInWithEmailAndPassword(seguidorEmail, password).await() }
        Firebase.auth.currentUser?.let { u ->
            for (sub in listOf("followers", "following")) {
                runCatching {
                    db.collection("usuarios").document(u.uid).collection(sub).get()
                        .await().documents.forEach { it.reference.delete().await() }
                }
            }
            runCatching { db.collection("usuarios").document(u.uid).delete().await() }
            runCatching { u.delete().await() }
        }
        Firebase.auth.signOut()
    }

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
        val carreraRemoteDataSource = CarreraFirestoreDataSourceImpl(Firebase.firestore)
        val resenaRemoteDataSource = ResenaFirestoreDataSourceImpl(Firebase.firestore)
        authRepository = AuthRepository(authRemoteDataSource)
        usuarioRepository = UsuarioRepository(usuarioRemoteDataSource, authRemoteDataSource)
        resenaRepository = ResenaRepository(
            resenaRemoteDataSource,
            usuarioRemoteDataSource,
            carreraRemoteDataSource,
            authRemoteDataSource
        )

        runBlocking {
            limpiarSembrado()

            // Autora de la reseña (el usuario al que se va a seguir)
            val registroAutor = authRepository.signUp(autorEmail, password)
            check(registroAutor.isSuccess) { "No se pudo crear $autorEmail: ${registroAutor.exceptionOrNull()?.message}" }
            val autorId = authRepository.currentUser!!.uid
            usuarioRepository.registerUsuario(autorId, "Ana", "ana", "Bio", "Bogotá")
            Firebase.firestore.collection("carreras").document(carreraId).set(carrera).await()
            resenaRepository.createResena(
                autorId, carreraId, textoResena, "4", listOf("Organización")
            )
            authRepository.signOut()

            // Usuario de la prueba: ya registrado, no sigue a nadie y queda con la sesión iniciada
            val registroSeguidor = authRepository.signUp(seguidorEmail, password)
            check(registroSeguidor.isSuccess) { "No se pudo crear $seguidorEmail: ${registroSeguidor.exceptionOrNull()?.message}" }
            val seguidorId = authRepository.currentUser!!.uid
            usuarioRepository.registerUsuario(seguidorId, "David", "david", "Corredor", "Cajicá")
        }

        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun followUsuario_fromReview_updatesFollowersAndShowsInSiguiendo() {
        //1. Ingreso con el usuario ya registrado
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("homeScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("homeScreen").assertIsDisplayed()

        //2. Buscar en las proximas carreras la carrera
        composeRule.onNodeWithTag("proximasCarreras").performScrollTo()
        composeRule.onNodeWithTag("proximasCarreras")
            .performScrollToNode(hasTestTag("raceCard_$carreraId"))
        composeRule.onNodeWithTag("raceCard_$carreraId").performClick()

        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("raceDetailScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("raceDetailScreen").assertIsDisplayed()

        //3. Navegar a la pantalla de publicProfile tocando el nombre del autor de la reseña
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("btnUsuarioResena").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onAllNodesWithTag("btnUsuarioResena")[0].performScrollTo().performClick()

        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("publicProfileScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("publicProfileScreen").assertIsDisplayed()

        //4. Verificamos la información del usuario
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("perfilNombre", useUnmergedTree = true)
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("perfilNombre", useUnmergedTree = true)
            .assertTextContains("Ana", substring = true)
        composeRule.onNodeWithTag("perfilUsuario", useUnmergedTree = true)
            .assertTextContains("ana", substring = true)
        composeRule.onNodeWithTag("perfilBio", useUnmergedTree = true)
            .assertTextContains("Bio", substring = true)
        composeRule.onNodeWithTag("perfilSeguidores", useUnmergedTree = true)
            .assertTextContains("0", substring = true)
        composeRule.onNodeWithTag("btnFollow").assertTextContains("Seguir")

        //5. Damos follow y verificamos que aumente el contador
        composeRule.onNodeWithTag("btnFollow").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodes(
                hasTestTag("perfilSeguidores") and hasText("1", substring = true),
                useUnmergedTree = true
            ).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("perfilSeguidores", useUnmergedTree = true)
            .assertTextContains("1", substring = true)
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodes(
                hasTestTag("btnFollow") and hasText("Siguiendo", substring = true)
            ).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("btnFollow").assertTextContains("Siguiendo")

        //6. Volvemos atrás: perfil -> detalle -> home
        composeRule.onNodeWithTag("atras").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("raceDetailScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("raceDetailScreen").assertIsDisplayed()
        composeRule.onNodeWithTag("atras").performScrollTo().performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("homeScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("homeScreen").assertIsDisplayed()

        //7. Navegamos a explorar y abrimos comunidad con el botón flotante
        composeRule.onNodeWithTag("nav_${Screen.Explorer.route}").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("exploreScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("exploreScreen").assertIsDisplayed()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("btnComunidad").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("btnComunidad").assertIsDisplayed()
        composeRule.onNodeWithTag("btnComunidad").performClick()

        //8. Pestaña Siguiendo: debe aparecer la reseña del usuario que acabamos de seguir
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("tabSIGUIENDO").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("tabSIGUIENDO").assertIsDisplayed()
        composeRule.onNodeWithTag("tabSIGUIENDO").performClick()
        composeRule.onNodeWithTag("tabSIGUIENDO").assertIsSelected()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodes(
                hasTestTag("resenaTexto") and hasText(textoResena, substring = true),
                useUnmergedTree = true
            ).fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onAllNodes(
            hasTestTag("resenaTexto") and hasText(textoResena, substring = true),
            useUnmergedTree = true
        )[0].assertIsDisplayed()
    }

    @After
    fun tearDown() = runBlocking {
        if (::scenario.isInitialized) scenario.close()
        limpiarSembrado()
    }
}