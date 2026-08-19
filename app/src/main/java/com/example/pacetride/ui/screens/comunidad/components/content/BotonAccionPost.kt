package com.example.pacetride.ui.screens.comunidad.components.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun BotonAccionPost(
    idIcono: Int,
    texto: String,
    seleccionado: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconoFinal = if (seleccionado && idIcono == R.drawable.ic_me_gusta) {
        R.drawable.ic_me_gusta_lleno
    } else {
        idIcono
    }

    val filtroColor = if (seleccionado) {
        ColorFilter.tint(colorResource(R.color.electric_lime))
    } else {
        null
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(R.color.midnight))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(iconoFinal),
            contentDescription = null,
            colorFilter = filtroColor,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = texto,
            color = colorResource(R.color.electric_lime),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun BotonAccionPostPreview() {
    var meGustaSeleccionado by remember { mutableStateOf(false) }

    BotonAccionPost(
        idIcono = R.drawable.ic_me_gusta,
        texto = stringResource(R.string.me_gusta),
        seleccionado = meGustaSeleccionado,
        onClick = { meGustaSeleccionado = !meGustaSeleccionado }
    )
}

@Composable
@Preview
fun BotonAccionPostPreview2() {
    var meGustaSeleccionado by remember { mutableStateOf(true) }

    BotonAccionPost(
        idIcono = R.drawable.ic_me_gusta,
        texto = stringResource(R.string.me_gusta),
        seleccionado = meGustaSeleccionado,
        onClick = { meGustaSeleccionado = !meGustaSeleccionado }
    )
}

@Composable
@Preview
fun BotonAccionPostPreview3() {
    BotonAccionPost(
        idIcono = R.drawable.ic_comentario,
        texto = stringResource(R.string.comentar),
        seleccionado = false,
        onClick = {}
    )
}
