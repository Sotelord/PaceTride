package com.example.pacetride.ui.utils.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun NavItem(
    idIcono: Int,
    texto: String,
    seleccionado: Boolean,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val color = if (seleccionado) colorResource(R.color.electric_lime) else Color.Gray

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = texto,
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(texto, color = color, fontSize = 12.sp)
    }
}

@Composable
@Preview
fun NavItemPreview(modifier: Modifier = Modifier){
    NavItem(R.drawable.ic_home, "Inicio", seleccionado = true)
}

@Composable
@Preview
fun NavItemPreview2(modifier: Modifier = Modifier){
    NavItem(R.drawable.ic_explorar, "Explorar", seleccionado = false)
}

@Composable
@Preview
fun NavItemPreview3(modifier: Modifier = Modifier){
    NavItem(R.drawable.ic_mis_carreras, "Mis carreras", seleccionado = false)
}

@Composable
@Preview
fun NavItemPreview4(modifier: Modifier = Modifier){
    NavItem(R.drawable.ic_perfil, "Perfil", seleccionado = false)
}