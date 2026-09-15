package com.example.pacetride.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import androidx.core.net.toUri

class AuthRemoteDataSource @Inject constructor (
    private val auth: FirebaseAuth
) {

    val currentUser: FirebaseUser?get() = auth.currentUser

    suspend fun signIn(email: String, password: String): Unit {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun signUp(email: String, password: String): Unit {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    fun signOut() {
        auth.signOut()
    }

    suspend fun updateProfileImage(photoUrl: String): Unit{
        val uri = photoUrl.toUri()
        currentUser?.updateProfile(
            UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build()
        )?.await()
    }
}