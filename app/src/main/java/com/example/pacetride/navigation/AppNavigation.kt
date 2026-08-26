package com.example.pacetride.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.R
import com.example.pacetride.ui.screens.escribirResena.EscribirResenaScreen
import com.example.pacetride.ui.screens.explorer.ExploreScreen
import com.example.pacetride.ui.screens.home.HomeScreen
import com.example.pacetride.ui.screens.login.LoginScreen
import com.example.pacetride.ui.screens.notifications.NotificationsScreen
import com.example.pacetride.ui.screens.profile.ProfileScreen
import com.example.pacetride.ui.screens.publicProfile.PublicProfileScreen
import com.example.pacetride.ui.screens.raceDetail.RaceDetailScreen
import com.example.pacetride.ui.screens.registrar.RegisterScreen

sealed class Screen(val route: String){
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object RaceDetail : Screen ("raceDetail"){
        fun createRoute(raceId : Int) = "raceDetail/$raceId"
    }
    object  Notifications : Screen("notificaciones"){
        fun createRoute(usuarioId : Int) = "notificaciones/$usuarioId"
    }
    object Explorer : Screen("explorer") {
        const val filtroDistancia = "filtroDistancia"
    }
    object  MisCarreras : Screen("misCarreras")
    object  Profile : Screen("profile")
    object  PublicProfile : Screen("publicProfile"){
        fun createRoute(usuarioId: Int) = "publicProfile/$usuarioId"
    }
    object Inscribeme : Screen("inscribeme")
    object Comentarios : Screen("comentarios")
    object ConfigUsuarioPublico : Screen ("configUsuarioPublico")
    object EditProfile : Screen ("editProfile")
    object Configuracion : Screen ("Configuracion")
    object RecuperarContrasena : Screen ("recuperarContrasena")
    object EscribirResena : Screen ("escribirResena")
}


@Composable
fun AppNavigation(
    navControler: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navControler,
        startDestination = Screen.Login.route,
        modifier = modifier
    ){

        //Inicio de sesión

        composable(route = Screen.Login.route){
            LoginScreen(
                loginButtonPressed = {
                    navControler.navigate(Screen.Home.route){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                },
                createAcountPressed = {
                    navControler.navigate(Screen.Register.route)
                },
                recuperarContrasenaPressed = {
                    navControler.navigate((Screen.RecuperarContrasena.route))
                }
            )
        }

        composable(route = Screen.Register.route){
            RegisterScreen (
                loginPressed = {
                    navControler.popBackStack()
                },
                registerPressed = {
                    navControler.navigate(Screen.Home.route){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }
            )
        }

        //Nav Bar

        composable(route = Screen.Home.route){
            HomeScreen(
                verCarreraButtonPressed = { raceId ->
                    navControler.navigate(Screen.RaceDetail.createRoute(raceId))
                },
                raceCardPressed = { raceId ->
                    navControler.navigate(Screen.RaceDetail.createRoute(raceId))
                },
                notificacionButtonPressed = { usuarioId ->
                    navControler.navigate(Screen.Notifications.createRoute(usuarioId))
                },
                distanciaShortcutPressed = { distanciaKm ->
                    navControler.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set(Screen.Explorer.filtroDistancia, distanciaKm)
                    navControler.navigate(Screen.Explorer.route)
                }
            )
        }

        composable(route = Screen.Explorer.route){ backStackEntry ->
            val filtroDistancia = remember(backStackEntry) {
                navControler.previousBackStackEntry
                    ?.savedStateHandle
                    ?.remove<Int>(Screen.Explorer.filtroDistancia)
            }

            ExploreScreen(
                verCarreraButtonPressed = { raceId ->
                    navControler.navigate(Screen.RaceDetail.createRoute(raceId))
                },
                filtroDistanciaInicial = filtroDistancia
            )
        }

        composable(route = Screen.MisCarreras.route){
            Text("Falta esta pantalla")
        }

        composable(route = Screen.Profile.route){
            ProfileScreen(
                editProfilePressed = {
                    navControler.navigate(Screen.EditProfile.route)
                },
                configurationPressed = {
                    navControler.navigate(Screen.Configuracion.route)
                }
            )
        }


        //Otra navegación
        composable (route = Screen.RecuperarContrasena.route){
            Text("Falta esta pantalla")
        }

        composable (route = Screen.Configuracion.route){
            Text("Falta esta pantalla")
        }

        composable (route = Screen.EditProfile.route){
            Text("Falta esta pantalla")
        }

        composable(
            route = "${Screen.RaceDetail.route}/{raceId}",
            arguments = listOf(navArgument("raceId") {type = NavType.IntType})
        ){ it ->
            //Obtener los parametros
            val raceId = it.arguments?.getInt("raceId") ?: 0

            //Buscar la carrera
            RaceDetailScreen(
                raceId = raceId,
                atrasPressed = {
                    navControler.popBackStack()
                               },
                inscribemePressed = {
                    navControler.navigate(Screen.Inscribeme.route)
                },
                escribirResenaPressed = {
                    navControler.navigate(Screen.EscribirResena.route)
                }
            )
        }

        composable (route = Screen.Inscribeme.route){
            Text("Falta esta pantalla")
        }

        composable (route = Screen.EscribirResena.route){
            EscribirResenaScreen()
        }

        composable(
            route = "${Screen.Notifications.route}/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") {type = NavType.IntType})
        ){ it ->
            val usuarioId = it.arguments?.getInt("usuarioId") ?: 0
            NotificationsScreen(
                usuarioId = usuarioId,
                atrasPressed = {
                    navControler.popBackStack()
                               },
                viewProfile = { usuarioId ->
                    navControler.navigate(Screen.PublicProfile.createRoute(usuarioId))
                              },
                verCarrera = { raceId ->
                    navControler.navigate(Screen.RaceDetail.createRoute(raceId))
                }
            )
        }

        composable (
            route = "${Screen.PublicProfile.route}/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") {type = NavType.IntType})
        ) {it ->
            val usuarioId = it.arguments?.getInt("usuarioId") ?: 0

            PublicProfileScreen(
                atrasPressed = {
                    navControler.popBackStack()
                               },
                usuarioId = usuarioId,
                comentariosPressed = {
                    navControler.navigate(Screen.Comentarios.route)
                                     },
                configPressed = {
                    navControler.navigate(Screen.ConfigUsuarioPublico.route)
                }
            )
        }

        composable (route = Screen.Comentarios.route){
            Text("Falta esta pantalla")
        }

        composable (route = Screen.ConfigUsuarioPublico.route){
            Text("Falta esta pantalla")
        }
    }
}

data class BottomNavItem(
    val idIcon: Int,
    val texto: String,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        idIcon = R.drawable.ic_home,
        texto = "Inicio",
        route = Screen.Home.route
    ),
    BottomNavItem(
        idIcon = R.drawable.ic_explorar,
        texto = "Explorar",
        route = Screen.Explorer.route
    ),
    BottomNavItem(
        idIcon = R.drawable.ic_mis_carreras,
        texto = "Mis carreras",
        route = Screen.MisCarreras.route
    ),
    BottomNavItem(
        idIcon = R.drawable.ic_perfil,
        texto = "Perfil",
        route = Screen.Profile.route
    )
)

@Composable
fun PacetrideBottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.idIcon),
                        contentDescription = item.route,
                        tint = if(isSelected)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = {
                    Text(item.texto)
                },
                selected = false,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PacetrideBottomNavigationBarPreview(){
    PacetrideTheme(darkTheme = true) {
        PacetrideBottomNavigationBar(
            navController = rememberNavController()
        )
    }
}