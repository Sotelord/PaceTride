package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun EstadisticasPostGrid(
    distancia: String,
    tiempo: String,
    ritmo: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(colorResource(R.color.midnight))
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EstadisticaPost(R.drawable.corriendo, distancia)
            EstadisticaPost(R.drawable.ic_reloj, tiempo)
        }
        Spacer(modifier = Modifier.height(10.dp))
        EstadisticaPost(R.drawable.ic_ritmo, ritmo)
    }
}

@Composable
@Preview
fun EstadisticaPostGridPreview(modifier: Modifier = Modifier){
    EstadisticasPostGrid("21,1 km", "1:52:40", "Ritmo: 5:20 min/km")
}