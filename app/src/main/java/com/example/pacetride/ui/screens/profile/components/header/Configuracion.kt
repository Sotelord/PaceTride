package com.example.pacetride.ui.screens.notifications.components.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier // ¡IMPORTACIÓN CORREGIDA AQUÍ!
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun Configuracion(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.ic_configuracion),
        contentDescription = "Configuracion",
        modifier = modifier
            .size(24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF121212)
fun ConfiguracionPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        Corazon()
    }
}
