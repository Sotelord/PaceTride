package com.example.pacetride

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val usarEmulador = true
        //BuildConfig.DEBUG
        if (usarEmulador) {
            Firebase.firestore.useEmulator("10.0.2.2", 8080)
            Firebase.auth.useEmulator("10.0.2.2", 9099)
        }
    }
}