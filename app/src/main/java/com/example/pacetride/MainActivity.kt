package com.example.pacetride

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.compose.PacetrideTheme
import com.example.pacetride.ui.screens.comunidad.ComunidadScreen
import com.example.pacetride.ui.screens.escribirResena.EscribirResenaScreen
import com.example.pacetride.ui.screens.explorer.ExploreScreen
import com.example.pacetride.ui.screens.home.HomeScreen
import com.example.pacetride.ui.screens.notifications.NotificationsScreen
import com.example.pacetride.ui.screens.profile.ProfileScreen
import com.example.pacetride.ui.screens.publicprofile.PublicProfileScreen
import com.example.pacetride.ui.screens.raceDetail.RaceDetailScreen

//Donde inicia la app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PacetrideTheme() {
                Scaffold() {
                    EscribirResenaScreen(
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}
