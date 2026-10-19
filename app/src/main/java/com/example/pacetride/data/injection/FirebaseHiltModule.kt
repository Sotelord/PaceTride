package com.example.pacetride.data.injection

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseHiltModule {
    @Provides
    fun auth(): FirebaseAuth = Firebase.auth

    @Provides
    fun storage(): FirebaseStorage = Firebase.storage

    @Singleton
    @Provides
    fun firestore(): FirebaseFirestore = Firebase.firestore
}