package com.example.pacetride

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pacetride.navigation.AppNavigation
import com.example.pacetride.navigation.NavigationLogic
import com.example.pacetride.navigation.PacetrideBottomNavigationBar

@Composable
fun PacetrideApp(){
    val navController = rememberNavController()

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

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