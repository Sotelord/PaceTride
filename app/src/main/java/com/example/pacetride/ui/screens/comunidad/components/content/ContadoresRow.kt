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
        ContadorInteraccion(R.drawable.ic_me_gusta_lleno, nLikes, stringResource(R.string.me_gusta))
        ContadorInteraccion(R.drawable.ic_comentario, nComentarios,
            stringResource(R.string.comentarios)
        )
    }
}

@Composable
@Preview
fun ContadoresRowPreview(modifier: Modifier = Modifier){
    ContadoresRow("128", "24")
}

@Composable
@Preview
fun ContadoresRowPreview2(modifier: Modifier = Modifier){
    ContadoresRow("67", "8")
}

@Composable
@Preview
fun ContadoresRowPreview3(modifier: Modifier = Modifier){
    ContadoresRow("34", "5")
}