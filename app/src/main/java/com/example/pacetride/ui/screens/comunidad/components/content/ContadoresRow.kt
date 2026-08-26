package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun ContadoresRow(
    nLikes: String,
    nComentarios: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ContadorInteraccion(idIcono = R.drawable.ic_me_gusta_lleno, cantidad = nLikes, interaccion =  stringResource(R.string.me_gusta))
        ContadorInteraccion(idIcono = R.drawable.ic_comentario, cantidad =  nComentarios,
            interaccion =  stringResource(R.string.comentarios), aplicarTinte = true
        )
    }
}

@Composable
@Preview
fun ContadoresRowPreview(){
    ContadoresRow("128", "24")
}

@Composable
@Preview
fun ContadoresRowPreview2(){
    ContadoresRow("67", "8")
}

@Composable
@Preview
fun ContadoresRowPreview3(){
    ContadoresRow("34", "5")
}