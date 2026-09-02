package com.example.pacetride.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.R
import com.example.pacetride.ui.screens.comunidad.ComunidadScreen
import com.example.pacetride.ui.screens.comunidad.ComunidadViewModel
import com.example.pacetride.ui.screens.escribirResena.EscribirResenaScreen
import com.example.pacetride.ui.screens.escribirResena.EscribirResenaViewModel
import com.example.pacetride.ui.screens.explorer.ExploreScreen
import com.example.pacetride.ui.screens.explorer.ExploreViewModel
import com.example.pacetride.ui.screens.home.HomeScreen
import com.example.pacetride.ui.screens.home.HomeViewModel
import com.example.pacetride.ui.screens.login.LoginScreen
import com.example.pacetride.ui.screens.login.LoginViewModel
import com.example.pacetride.ui.screens.misCarreras.MisCarrerasScreen
import com.example.pacetride.ui.screens.misCarreras.MisCarrerasViewModel
import com.example.pacetride.ui.screens.notifications.NotificationsScreen
import com.example.pacetride.ui.screens.notifications.NotificationsViewModel
import com.example.pacetride.ui.screens.profile.ProfileScreen
import com.example.pacetride.ui.screens.profile.ProfileViewModel
import com.example.pacetride.ui.screens.publicProfile.PublicProfileScreen
import com.example.pacetride.ui.screens.publicProfile.PublicProfileViewModel
import com.example.pacetride.ui.screens.raceDetail.RaceDatailViewModel
import com.example.pacetride.ui.screens.raceDetail.RaceDetailScreen
import com.example.pacetride.ui.screens.registrar.RegisterScreen
import com.example.pacetride.ui.screens.registrar.RegisterViewModel

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
    object Comunidad: Screen("comunidad")
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
            val loginViewModel: LoginViewModel = viewModel()
            val state by loginViewModel.uiState.collectAsState()

            LaunchedEffect(state.navigate) {
                if (state.navigate) {
                    navControler.navigate(Screen.Home.route){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }
            }

            LoginScreen(
                loginViewModel = loginViewModel,
                createAcountPressed = {
                    navControler.navigate(Screen.Register.route)
                },
                recuperarContrasenaPressed = {
                    navControler.navigate((Screen.RecuperarContrasena.route))
                }
            )
        }

        composable(route = Screen.Register.route){
            val registerViewModel: RegisterViewModel = viewModel()
            val state by registerViewModel.uiState.collectAsState()

            LaunchedEffect(state.navigate) {
                if (state.navigate) {
                    navControler.navigate(Screen.Home.route){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }
            }

            RegisterScreen (
                registerViewModel = registerViewModel,
                loginPressed = {
                    navControler.popBackStack()
                }
            )
        }

        composable(route = Screen.Home.route){
            val homeViewModel: HomeViewModel = viewModel()
            HomeScreen(
                homeViewModel = homeViewModel,
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
            val exploreViewModel: ExploreViewModel = viewModel()
            val filtroDistancia = remember(backStackEntry) {
                navControler.previousBackStackEntry
                    ?.savedStateHandle
                    ?.remove<Int>(Screen.Explorer.filtroDistancia)
            }

            ExploreScreen(
                exploreViewModel = exploreViewModel,
                verCarreraButtonPressed = { raceId ->
                    navControler.navigate(Screen.RaceDetail.createRoute(raceId))
                },
                filtroDistanciaInicial = filtroDistancia,
                comunidadPressed = {
                    navControler.navigate(Screen.Comunidad.route)
                }
            )
        }

        composable(route = Screen.MisCarreras.route){
            val misCarrerasViewModel: MisCarrerasViewModel = viewModel()
            MisCarrerasScreen(
                misCarrerasViewModel = misCarrerasViewModel,
                notificacionButtonPressed = { usuarioId ->
                    navControler.navigate(Screen.Notifications.createRoute(usuarioId))
                },
                verCarreraButtonPressed = { raceId ->
                    navControler.navigate(Screen.RaceDetail.createRoute(raceId))
                },
                explorarPressed = {
                    navControler.navigate(Screen.Explorer.route)
                }
            )
        }

        composable(route = Screen.Profile.route){
            val profileViewModel: ProfileViewModel = viewModel()
            ProfileScreen(
                profileViewModel = profileViewModel,
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

        composable (route = Screen.Comunidad.route){
            val comunidadViewModel: ComunidadViewModel = viewModel()
            ComunidadScreen(
                comunidadViewModel = comunidadViewModel,
                escribirResenaPressed = {
                    navControler.navigate(Screen.EscribirResena.route)
                },
                notificacionButtonPressed = { usuarioId ->
                    navControler.navigate(Screen.Notifications.createRoute(usuarioId))
                }
            )
        }

        composable(
            route = "${Screen.RaceDetail.route}/{raceId}",
            arguments = listOf(navArgument("raceId") {type = NavType.IntType})
        ){
            val raceDatailViewModel: RaceDatailViewModel = viewModel()
            val raceId = it.arguments?.getInt("raceId") ?: 0

            RaceDetailScreen(
                raceDatailViewModel = raceDatailViewModel,
                raceId = raceId,
                atrasPressed = {
                    navControler.popBackStack()
                               },
                inscribemePressed = {
                    navControler.navigate(Screen.Inscribeme.route)
                }
            )
        }

        composable (route = Screen.Inscribeme.route){
            Text("Falta esta pantalla")
        }

        composable (route = Screen.EscribirResena.route){
            val escribirResenaViewModel: EscribirResenaViewModel = viewModel()
            val state by escribirResenaViewModel.uiState.collectAsState()
            LaunchedEffect(state.navigate) {
                if (state.navigate) {
                    navControler.popBackStack()
                }
            }
            EscribirResenaScreen(
                escribirResenaViewModel = escribirResenaViewModel,
                atrasPressed = {
                    navControler.popBackStack()
                }
            )
        }

        composable(
            route = "${Screen.Notifications.route}/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") {type = NavType.IntType})
        ){
            val notificationsViewModel: NotificationsViewModel = viewModel()
            val usuarioId = it.arguments?.getInt("usuarioId") ?: 0
            NotificationsScreen(
                notificationsViewModel = notificationsViewModel,
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
        ) {
            val publicProfileViewModel: PublicProfileViewModel = viewModel()
            val usuarioId = it.arguments?.getInt("usuarioId") ?: 0

            PublicProfileScreen(
                publicProfileViewModel = publicProfileViewModel,
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