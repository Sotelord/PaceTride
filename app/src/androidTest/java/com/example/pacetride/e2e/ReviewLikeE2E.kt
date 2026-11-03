package com.example.pacetride.e2e

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.example.pacetride.MainActivity
import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.example.pacetride.data.datasource.impl.firestore.CarreraFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.ResenaFirestoreDataSourceImpl
import com.example.pacetride.data.datasource.impl.firestore.UsuarioFirestoreDataSourceImpl
import com.example.pacetride.data.dtos.CarreraDto
import com.example.pacetride.data.repository.AuthRepository
import com.example.pacetride.data.repository.CarreraRepository
import com.example.pacetride.data.repository.ResenaRepository
import com.example.pacetride.data.repository.UsuarioRepository
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
class ReviewLikeE2E {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var authRepository: AuthRepository
    private lateinit var usuarioRepository: UsuarioRepository
    private lateinit var carrerRepository: CarreraRepository
    private lateinit var resenaRepository: ResenaRepository
    private val carreraId = "e2e-carrera"
    private lateinit var reviewerId: String

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
        Firebase.auth.currentUser?.let { u ->
            runCatching { db.collection("usuarios").document(u.uid).delete().await() }
            runCatching { u.delete().await() }
        }
        Firebase.auth.signOut()
        runCatching {
            Firebase.auth.signInWithEmailAndPassword("reviewer@test.com", "12345678").await()
        }
        runCatching {
            val resenas =
                db.collection("resenas").whereEqualTo("carreraId", carreraId).get().await()
            for (doc in resenas.documents) {
                doc.reference.collection("likes").get().await().documents
                    .forEach { it.reference.delete().await() }
                doc.reference.delete().await()
            }
        }
        runCatching { db.collection("carreras").document(carreraId).delete().await() }
        Firebase.auth.currentUser?.let { u ->
            runCatching { db.collection("usuarios").document(u.uid).delete().await() }
            runCatching { u.delete().await() }
        }
        Firebase.auth.signOut()
    }

    @After
    fun tearDown() = runBlocking { limpiarSembrado() }

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
        carrerRepository = CarreraRepository(carreraRemoteDataSource, authRemoteDataSource)
        resenaRepository = ResenaRepository(
            resenaRemoteDataSource,
            usuarioRemoteDataSource,
            carreraRemoteDataSource,
            authRemoteDataSource
        )

        runBlocking {
            limpiarSembrado()

            val registro = authRepository.signUp("reviewer@test.com", "12345678")
            check(registro.isSuccess) { "No se pudo crear reviewer@test.com: ${registro.exceptionOrNull()?.message}" }
            reviewerId = authRepository.currentUser!!.uid
            usuarioRepository.registerUsuario(reviewerId, "Ana", "ana", "Bio", "Bogotá")
            Firebase.firestore.collection("carreras").document(carreraId).set(carrera).await()

            resenaRepository.createResena(
                reviewerId,
                carreraId,
                "Muy buena carrera",
                "4",
                listOf("Organización")
            )
            authRepository.signOut()
        }
    }

    @Test
    fun registerUsuario_invalidAfterValidFields_updateLike() {
        //1. Coloca la contraseña en el registro 1234 (Registro contraseña corta)
        composeRule.onNodeWithTag("create_account_button").performClick()
        composeRule.onNodeWithTag("txtNombre").performTextInput("David")
        composeRule.onNodeWithTag("txtEmail").performTextInput("prueba@test.com")
        composeRule.onNodeWithTag("txtUsuario").performTextInput("david")
        composeRule.onNodeWithTag("txtPassword").performTextInput("1234")
        composeRule.onNodeWithTag("txtConfirmPassword").performTextInput("1234")
        composeRule.onNodeWithTag("chkTerms").performScrollTo().performClick()

        composeRule.onNodeWithTag("btnRegister").performScrollTo().performClick()
        //Verificacion mensaje de error
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("errorMessage").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("errorMessage").performScrollTo()
            .assertTextEquals("La contraseña debe tener al menos 7 caracteres")

        //2. Cambia la contraseña a una válida para poderse registrar
        composeRule.onNodeWithTag("txtPassword").performTextClearance()
        composeRule.onNodeWithTag("txtPassword").performTextInput("12345678")
        composeRule.onNodeWithTag("txtConfirmPassword").performTextClearance()
        composeRule.onNodeWithTag("txtConfirmPassword").performTextInput("12345678")

        composeRule.onNodeWithTag("btnRegister").performScrollTo().performClick()

        //Esperamos pacientemente hasta navegar a homeScreen el tiempo es gigante porque mi tostadora no da para más
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("homeScreen").fetchSemanticsNodes().isNotEmpty()
        }

        composeRule.onNodeWithTag("homeScreen").assertIsDisplayed()

        //3. Buscar en las proximas carreras la carrera
        composeRule.onNodeWithTag("proximasCarreras").performScrollTo()
        composeRule.onNodeWithTag("proximasCarreras")
            .performScrollToNode(hasTestTag("raceCard_$carreraId"))
        composeRule.onNodeWithTag("raceCard_$carreraId").performClick()

        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("raceDetailScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("raceDetailScreen").assertIsDisplayed()

        //4. Verificamos que la info del detalle sea corrercta
        composeRule.onNodeWithTag("raceTitulo").assertTextContains(carrera.nombre, substring = true)
        composeRule.onNodeWithTag("raceTitulo")
            .assertTextContains(carrera.ubicacion, substring = true)
        composeRule.onNodeWithTag("raceDescripcion").performScrollTo()
            .assertTextEquals(carrera.descripcion)

        //5. Damos like a la primera reseña
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("btnLike").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onAllNodesWithTag("btnLike")[0].performScrollTo().performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodes(
                hasTestTag("btnLike") and hasText(
                    "1 personas",
                    substring = true
                )
            )
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onAllNodesWithTag("btnLike")[0].assertTextContains(
            "1 personas",
            substring = true
        )

        //6. Volvemos atras
        composeRule.onNodeWithTag("atras").performScrollTo().performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("homeScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("homeScreen").assertIsDisplayed()

        //7. Volvemos a la carrera
        composeRule.onNodeWithTag("proximasCarreras").performScrollTo()
        composeRule.onNodeWithTag("proximasCarreras")
            .performScrollToNode(hasTestTag("raceCard_$carreraId"))
        composeRule.onNodeWithTag("raceCard_$carreraId").performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("raceDetailScreen").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("raceDetailScreen").assertIsDisplayed()

        //8. Verificamos que el like se mantenga y luego lo quitamos y verificamos que disminuya el contador
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodesWithTag("btnLike").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodes(
                hasTestTag("btnLike") and hasText(
                    "1 personas",
                    substring = true
                )
            )
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onAllNodesWithTag("btnLike")[0].assertTextContains(
            "1 personas",
            substring = true
        )
        composeRule.onAllNodesWithTag("btnLike")[0].performScrollTo().performClick()
        composeRule.waitUntil(timeoutMillis = 50000) {
            composeRule.onAllNodes(
                hasTestTag("btnLike") and hasText(
                    "0 personas",
                    substring = true
                )
            )
                .fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onAllNodesWithTag("btnLike")[0].assertTextContains(
            "0 personas",
            substring = true
        )
    }
}