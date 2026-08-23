package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun ContadorInteraccion(
    idIcono: Int,
    cantidad: String,
    interaccion: String,
    aplicarTinte: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            colorFilter = if (aplicarTinte && isSystemInDarkTheme() != true) {
                ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
            } else {
                null
            },
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            "$cantidad $interaccion",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp
        )
    }
}

@Composable
@Preview
fun ContadorInteraccionPreview(modifier: Modifier = Modifier) {
    ContadorInteraccion(R.drawable.ic_me_gusta_lleno, "128", "Me gusta") // sin tinte, corazón rojo
}

@Composable
@Preview
fun ContadorInteraccionPreview1_1(modifier: Modifier = Modifier) {
    ContadorInteraccion(R.drawable.ic_comentario, "24", "comentarios", aplicarTinte = true) // con tinte
}

@Composable
@Preview
fun ContadorInteraccionPreview2(modifier: Modifier = Modifier) {
    ContadorInteraccion(R.drawable.ic_me_gusta_lleno, "67", "Me gusta")
}

@Composable
@Preview
fun ContadorInteraccionPreview2_1(modifier: Modifier = Modifier) {
    ContadorInteraccion(R.drawable.ic_comentario, "8", "comentarios", aplicarTinte = true)
}

@Composable
@Preview
fun ContadorInteraccionPreview3(modifier: Modifier = Modifier) {
    ContadorInteraccion(R.drawable.ic_me_gusta_lleno, "34", "Me gusta")
}

@Composable
@Preview
fun ContadorInteraccionPreview3_1(modifier: Modifier = Modifier) {
    ContadorInteraccion(R.drawable.ic_comentario, "5", "comentarios", aplicarTinte = true)
}