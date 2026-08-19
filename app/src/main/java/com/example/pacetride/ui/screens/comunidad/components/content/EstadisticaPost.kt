package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- CHIP DE ESTADÍSTICA (distancia / tiempo / ritmo) ----------

@Composable
fun EstadisticaPost(
    idIcono: Int,
    texto: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(R.color.electric_lime)),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(texto, color = colorResource(R.color.ice), fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
@Preview
fun EstadisticaPostPreview(modifier: Modifier = Modifier){
    EstadisticaPost(R.drawable.corriendo, "21,1 km")
}

@Composable
@Preview
fun EstadisticaPostPreview2(modifier: Modifier = Modifier){
    EstadisticaPost(R.drawable.ic_reloj, "1:52:40")
}

@Composable
@Preview
fun EstadisticaPostPreview3(modifier: Modifier = Modifier){
    EstadisticaPost(R.drawable.ic_ritmo, "Ritmo: 5:20 min/km")
}