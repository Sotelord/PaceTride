package com.example.pacetride.data.repository

import com.example.pacetride.data.datasource.AuthRemoteDataSource
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor (
    private val authRemoteDataSource: AuthRemoteDataSource
){
    val currentUser: FirebaseUser?get() = authRemoteDataSource.currentUser

    suspend fun signIn (email: String, password: String): Result<Unit> {
        return try {
            authRemoteDataSource.signIn(email, password)
            Result.success(Unit)
        } catch (e: FirebaseAuthInvalidUserException) {
            Result.failure(Exception("No existe una cuenta con ese correo"))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(Exception("Correo o contraseña incorrectos"))
        } catch (e: FirebaseNetworkException) {
            Result.failure(Exception("Sin conexión a internet. Revisa tu red"))
        } catch (e: FirebaseTooManyRequestsException) {
            Result.failure(Exception("Demasiados intentos. Intenta de nuevo más tarde"))
        }
        catch (e: Exception) {
            Result.failure(Exception("Ocurrió un error al iniciar sesión. Intenta de nuevo"))
        }
    }

    suspend fun signUp (email: String, password: String): Result<Unit> {
        return try {
            authRemoteDataSource.signUp(email, password)
            Result.success(Unit)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(Exception("El correo no tiene un formato válido"))
        } catch (e: FirebaseNetworkException) {
            Result.failure(Exception("Sin conexión a internet. Revisa tu red"))
        } catch (e: FirebaseAuthUserCollisionException) {
            Result.failure(Exception("Ya existe una cuenta con ese correo"))
        }catch (e: Exception) {
            Result.failure(Exception("Ocurrió un error al registrarte. Intenta de nuevo"))
        }
    }

    fun signOut(){
        authRemoteDataSource.signOut()
    }
}