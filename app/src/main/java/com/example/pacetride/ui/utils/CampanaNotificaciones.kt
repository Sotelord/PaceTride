package com.example.pacetride.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun CampanaNotificaciones(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(4.dp)
    ) {
        // Ícono de la campana
        Image(
            painter = painterResource(R.drawable.campana),
            contentDescription = stringResource(R.string.campana_notificaciones),
            modifier = Modifier.size(28.dp)
        )

        // Punto de notificación rojo
        Box(
            modifier = Modifier
                .size(9.dp)
                .align(Alignment.TopEnd)
                .offset(x = 2.dp, y = (-2).dp)
                .background(Color.Red, shape = CircleShape)
        )
    }
}

@Composable
@Preview
fun CampanaNotificacionesPreview(modifier: Modifier = Modifier){
    CampanaNotificaciones()
}