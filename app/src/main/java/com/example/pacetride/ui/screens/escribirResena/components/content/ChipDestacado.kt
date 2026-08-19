package com.example.pacetride.ui.screens.escribirResena.components.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun ChipDestacado(
    texto: String,
    seleccionado: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fondo = if (seleccionado) colorResource(R.color.electric_lime).copy(alpha = 0.15f) else colorResource(R.color.graphite)
    val borde = if (seleccionado) colorResource(R.color.electric_lime) else Color.Transparent
    val colorTexto = if (seleccionado) colorResource(R.color.electric_lime) else colorResource(R.color.ice)

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(fondo)
            .then(
                if (seleccionado) Modifier else Modifier
            )
            .border(BorderStroke(1.dp, borde), RoundedCornerShape(50))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (seleccionado) {
            Image(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(R.color.electric_lime)),
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(texto, color = colorTexto, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
@Preview
fun ChipDestacadoPreview(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Estado No Seleccionado
        ChipDestacado(
            texto = "Ruta",
            seleccionado = false,
            onClick = {}
        )

        // Estado Seleccionado
        ChipDestacado(
            texto = "Ruta",
            seleccionado = true,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChipDestacadoPreview2(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Estado No Seleccionado
        ChipDestacado(
            texto = "Organización",
            seleccionado = false,
            onClick = {}
        )

        // Estado Seleccionado
        ChipDestacado(
            texto = "Organización",
            seleccionado = true,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChipDestacadoPreview3(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Estado No Seleccionado
        ChipDestacado(
            texto = "Ambiente",
            seleccionado = false,
            onClick = {}
        )

        // Estado Seleccionado
        ChipDestacado(
            texto = "Ambiente",
            seleccionado = true,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChipDestacadoPreview4(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Estado No Seleccionado
        ChipDestacado(
            texto = "Hidratación",
            seleccionado = false,
            onClick = {}
        )

        // Estado Seleccionado
        ChipDestacado(
            texto = "Hidratación",
            seleccionado = true,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChipDestacadoPreview5(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Estado No Seleccionado
        ChipDestacado(
            texto = "Seguridad",
            seleccionado = false,
            onClick = {}
        )

        // Estado Seleccionado
        ChipDestacado(
            texto = "Seguridad",
            seleccionado = true,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChipDestacadoPreview6(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Estado No Seleccionado
        ChipDestacado(
            texto = "Kit",
            seleccionado = false,
            onClick = {}
        )

        // Estado Seleccionado
        ChipDestacado(
            texto = "Kit",
            seleccionado = true,
            onClick = {}
        )
    }
}

@Composable
@Preview
fun ChipDestacadoPreview7(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Estado No Seleccionado
        ChipDestacado(
            texto = "Precio",
            seleccionado = false,
            onClick = {}
        )

        // Estado Seleccionado
        ChipDestacado(
            texto = "Precio",
            seleccionado = true,
            onClick = {}
        )
    }
}

