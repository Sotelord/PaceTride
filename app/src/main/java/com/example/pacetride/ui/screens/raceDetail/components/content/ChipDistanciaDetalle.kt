package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun ChipDistanciaDetalle(
    texto: String,
    seleccionado: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fondo = if (seleccionado) colorResource(R.color.electric_lime) else colorResource(R.color.graphite)
    val color = if (seleccionado) Color.Black else Color.LightGray

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(fondo)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(texto, color = color, fontWeight = FontWeight.Bold)
    }
}

@Composable
@Preview(showBackground = true)
fun ChipDistanciaDetallePreview(modifier: Modifier = Modifier){
    ChipDistanciaDetalle("5K", false, onClick = {})
}

@Composable
@Preview(showBackground = true)
fun ChipDistanciaDetallePreview2(modifier: Modifier = Modifier){
    ChipDistanciaDetalle("10K", false, onClick = {})
}

@Composable
@Preview
fun ChipDistanciaDetallePreview3(modifier: Modifier = Modifier){
    ChipDistanciaDetalle("21K", true, onClick = {})
}