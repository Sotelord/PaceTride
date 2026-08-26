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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun ContadorInteraccion(
    modifier: Modifier = Modifier,
    idIcono: Int,
    cantidad: String,
    interaccion: String,
    aplicarTinte: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            colorFilter = if (aplicarTinte && !isSystemInDarkTheme()) {
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
fun ContadorInteraccionPreview() {
    ContadorInteraccion(
        idIcono = R.drawable.ic_me_gusta_lleno,
        cantidad =  "128",
        interaccion = "Me gusta"
    ) // sin tinte, corazón rojo
}

@Composable
@Preview
fun ContadorInteraccionPreview1_1() {
    ContadorInteraccion(
        idIcono = R.drawable.ic_comentario,
        cantidad = "24",
        interaccion = "comentarios"
    )
}

@Composable
@Preview
fun ContadorInteraccionPreview2() {
    ContadorInteraccion(
        idIcono =  R.drawable.ic_me_gusta_lleno,
        cantidad = "67",
        interaccion = "Me gusta"
    )
}

@Composable
@Preview
fun ContadorInteraccionPreview2_1() {
    ContadorInteraccion(
        idIcono =  R.drawable.ic_comentario,
        cantidad = "8",
        interaccion = "comentarios"
    )
}

@Composable
@Preview
fun ContadorInteraccionPreview3() {
    ContadorInteraccion(
        idIcono =  R.drawable.ic_me_gusta_lleno,
        cantidad = "34",
        interaccion = "Me gusta"
    )
}

@Composable
@Preview
fun ContadorInteraccionPreview3_1() {
    ContadorInteraccion(
        idIcono =  R.drawable.ic_comentario,
        cantidad = "5",
        interaccion = "comentarios",
        aplicarTinte = true
    )
}