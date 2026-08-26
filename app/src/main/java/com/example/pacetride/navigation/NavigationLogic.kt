package com.example.pacetride.navigation

object NavigationLogic {
    private val siBottomNavBar = listOf(
        Screen.Home.route,
        Screen.Explorer.route,
        Screen.MisCarreras.route,
        Screen.Profile.route
    )

    fun shouldShowBottomNavBar(route: String?) = siBottomNavBar.contains(route)
}