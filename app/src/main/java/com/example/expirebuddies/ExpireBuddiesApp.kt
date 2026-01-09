package com.example.expirebuddies

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ExpireBuddiesApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Inizializza Firebase
        FirebaseApp.initializeApp(this)
    }
}