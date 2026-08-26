package com.example.pacetride.data.local

import com.example.pacetride.R
import com.example.pacetride.data.EstadisticasGlobales
import com.example.pacetride.data.Usuario

object LocalUsuarioProvider {

    private val carreras = LocalCarreraProvider.listCarrera
    private val historial = LocalCarreraRealizadaProvider.historial
    private val resenas = LocalResenaProvider.listaResenas

    val usuarios = listOf(
        Usuario(
            id = 1,
            nombre = "Santiago Rayo",
            usuario = "@santiagorayo",
            ubicacion = "Bogotá, Colombia",
            bio = "Runner • Siempre buscando mi próximo reto 🏃",
            fotoPerfil = R.drawable.foto_perfil,
            proximaCarreras = carreras,
            estadisticasGlobales = EstadisticasGlobales(
                numCarrera = 12,
                distacia = "186,4 km",
                mejorTiempo10k = "48:32",
                mejorTiempo21k = "1:52:40"
            ),
            historial = historial,
            resenas = resenas
        ),

        Usuario(
            id = 2,
            nombre = "Sara Castro",
            usuario = "@saracastro",
            ubicacion = "Chía, Colombia",
            bio = "Corredora • Kilómetros, música y nuevas metas 🎧🏃‍♀️",
            fotoPerfil = R.drawable.foto_perfil,
            proximaCarreras = carreras,
            estadisticasGlobales = EstadisticasGlobales(
                numCarrera = 18,
                distacia = "254,7 km",
                mejorTiempo10k = "52:18",
                mejorTiempo21k = "2:01:35"
            ),
            historial = historial,
            resenas = resenas
        ),

        Usuario(
            id = 3,
            nombre = "David Sotelo",
            usuario = "@davidsotelo",
            ubicacion = "Cajicá, Colombia",
            bio = "Runner • Constancia hoy, kilómetros mañana 🚀🏃",
            fotoPerfil = R.drawable.foto_perfil,
            proximaCarreras = carreras,
            estadisticasGlobales = EstadisticasGlobales(
                numCarrera = 9,
                distacia = "143,2 km",
                mejorTiempo10k = "46:55",
                mejorTiempo21k = "1:48:20"
            ),
            historial = historial
        ),

        Usuario(
            id = 4,
            nombre = "Juan Angarita",
            usuario = "@juanangarita",
            ubicacion = "Bogotá, Colombia",
            bio = "Runner • Disfrutando cada kilómetro y cada nueva ruta 🏃‍♂️🌄",
            fotoPerfil = R.drawable.foto_perfil,
            proximaCarreras = carreras,
            estadisticasGlobales = EstadisticasGlobales(
                numCarrera = 15,
                distacia = "221,8 km",
                mejorTiempo10k = "49:47",
                mejorTiempo21k = "1:56:12"
            ),
            historial = historial
        )
    )
}