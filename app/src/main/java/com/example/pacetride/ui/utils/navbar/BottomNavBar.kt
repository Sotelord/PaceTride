package com.example.pacetride.ui.utils.navbar

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

@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.graphite))
            .padding(vertical = 12.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NavItem(R.drawable.ic_home, "Inicio", seleccionado = true)
        NavItem(R.drawable.ic_explorar, "Explorar", seleccionado = false)
        NavItem(R.drawable.ic_mis_carreras, "Mis carreras", seleccionado = false)
        NavItem(R.drawable.ic_perfil, "Perfil", seleccionado = false)
    }
}

@Composable
@Preview
fun BottomNavBarPreview(modifier: Modifier = Modifier){
    BottomNavBar()
}