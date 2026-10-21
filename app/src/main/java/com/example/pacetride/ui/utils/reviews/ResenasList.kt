package com.example.pacetride.ui.utils.reviews


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.Resena
import com.example.pacetride.data.local.LocalResenaProvider
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ResenasList(
    modifier: Modifier = Modifier,
    resenas: List<Resena>,
    sesion: String,
    isOwn: Boolean = false,
    mensajeVacio: String = "Aún no hay reseñas para esta carrera, anímate a escribir la primera.",
    sendOrDeleteResenaLike: (String, String) -> Unit,
    onClickEdit: (String, String) -> Unit = {_, _ ->},
    onClickDelete: (String) -> Unit = {},
    onClickUsuario: (String) -> Unit = {},
    onClickCarrera: (String) -> Unit = {}
) {
    if (resenas.isNotEmpty()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp), // Espacio entre cada tarjeta
            modifier = modifier.fillMaxWidth()
        ) {
            resenas.forEach { itemResena ->
                ResenaCard(
                    sesion = sesion,
                    resena = itemResena,
                    isOwn = isOwn,
                    sendOrDeleteResenaLike = sendOrDeleteResenaLike,
                    onClickEdit = onClickEdit,
                    onClickDelete = onClickDelete,
                    onClickUsuario = onClickUsuario,
                    onClickCarrera = onClickCarrera
                )
            }
        }
    } else {
        Text(
            text = mensajeVacio,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
@Preview
fun ResenasListPreview() {
    PacetrideTheme(darkTheme = true) {
        ResenasList(
            sesion = "1",
            resenas = LocalResenaProvider.listaResenas,
            sendOrDeleteResenaLike = { _, _ -> },
        )
    }
}

@Composable
@Preview
fun ResenasListPreview2() {
    PacetrideTheme(darkTheme = true) {
        val resenasList = listOf<Resena>()
        ResenasList(
            sesion = "1",
            resenas = resenasList,
            sendOrDeleteResenaLike = { _, _ -> },
        )
    }
}

@Composable
@Preview
fun ResenasListPreview3() {
    PacetrideTheme(darkTheme = true) {
        ResenasList(
            sesion = "",
            resenas = LocalResenaProvider.listaResenas,
            isOwn = true,
            sendOrDeleteResenaLike = { _, _ -> },
        )
    }
}