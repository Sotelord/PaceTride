package com.example.pacetride

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pacetride.navigation.AppNavigation
import com.example.pacetride.navigation.NavigationLogic
import com.example.pacetride.navigation.PacetrideBottomNavigationBar
import com.google.firebase.messaging.FirebaseMessaging

@Composable
fun PacetrideApp(){
    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val token = task.result
            Log.d("NOTIFICATION", "Token: $token")
        } else {
            Log.e("NOTIFICATION", "Error al obtener el token", task.exception)
        }
    }

    val navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    val context = LocalContext.current

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                Log.d("NOTIFICATION", "Permiso concedido")
            } else {
                Log.d("NOTIFICATION", "Permiso denegado")
            }
        }
    )

    LaunchedEffect(Unit) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ){
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if(NavigationLogic.shouldShowBottomNavBar(currentRoute)){
                PacetrideBottomNavigationBar(
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        AppNavigation(
            navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}