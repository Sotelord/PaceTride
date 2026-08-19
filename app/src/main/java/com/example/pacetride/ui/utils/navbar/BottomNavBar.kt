package com.example.pacetride.ui.utils.navbar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

enum class Seccion {
    INICIO, EXPLORAR, MIS_CARRERAS, PERFIL
}
@Composable
fun BottomNavBar(
    seccionActual: Seccion,
    onSeccionClick: (Seccion) -> Unit ={},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.graphite))
            .padding(vertical = 12.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NavItem(
            idIcono = R.drawable.ic_home,
            texto = "Inicio",
            seleccionado = seccionActual == Seccion.INICIO,
            onClick = {
                Log.d("NavBar", "Inicio clicked")
                onSeccionClick(Seccion.INICIO)
            }
        )
        NavItem(
            idIcono = R.drawable.ic_explorar,
            texto = "Explorar",
            seleccionado = seccionActual == Seccion.EXPLORAR,
            onClick = {
                Log.d("NavBar", "Explorar clicked")
                onSeccionClick(Seccion.EXPLORAR)
            }
        )
        NavItem(
            idIcono = R.drawable.ic_mis_carreras,
            texto = "Mis carreras",
            seleccionado = seccionActual == Seccion.MIS_CARRERAS,
            onClick = {
                Log.d("NavBar", "Mis carreras clicked")
                onSeccionClick(Seccion.MIS_CARRERAS)
            }
        )
        NavItem(
            idIcono = R.drawable.ic_perfil,
            texto = "Perfil",
            seleccionado = seccionActual == Seccion.PERFIL,
            onClick = {
                Log.d("NavBar", "Perfil clicked")
                onSeccionClick(Seccion.PERFIL)
            }
        )
    }
}

@Composable
@Preview
fun BottomNavBarPreview(modifier: Modifier = Modifier){
    BottomNavBar(Seccion.INICIO)
}

@Composable
@Preview
fun BottomNavBarPreview2(modifier: Modifier = Modifier){
    BottomNavBar(Seccion.EXPLORAR)
}

@Composable
@Preview
fun BottomNavBarPreview3(modifier: Modifier = Modifier){
    BottomNavBar(Seccion.MIS_CARRERAS)
}

@Composable
@Preview
fun BottomNavBarPreview4(modifier: Modifier = Modifier){
    BottomNavBar(Seccion.PERFIL)
}