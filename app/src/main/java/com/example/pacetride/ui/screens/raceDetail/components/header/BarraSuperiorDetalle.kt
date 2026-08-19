package com.example.pacetride.ui.screens.raceDetail.components.header

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun BarraSuperiorDetalle(modifier: Modifier = Modifier) {
    var meGustaActivo by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.ic_atras),
            contentDescription = stringResource(R.string.atras),
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { Log.d("RaceDetailScreen", "Atras clicked") }
                )
        )
        Image(
            painter = painterResource(
                id = if (meGustaActivo) R.drawable.ic_me_gusta_lleno else R.drawable.ic_me_gusta
            ),
            contentDescription = stringResource(R.string.me_gusta),
            colorFilter = if (meGustaActivo) ColorFilter.tint(colorResource(R.color.electric_lime)) else null,
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        Log.d("RaceDetailScreen", "Me gusta clicked")
                        meGustaActivo = !meGustaActivo
                    }
                )
        )
    }
}

@Composable
@Preview
fun BarraSuperiorDetallePreview(modifier: Modifier = Modifier){
    BarraSuperiorDetalle()
}