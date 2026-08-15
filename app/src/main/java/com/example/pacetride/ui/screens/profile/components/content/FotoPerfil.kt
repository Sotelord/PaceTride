package com.example.pacetride.ui.screens.profile.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun FotoPerfil(idImagen: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(idImagen),
        contentDescription = stringResource(R.string.foto_de_perfil),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
    )
}

@Composable
@Preview
fun FotoPerfilPreview(modifier: Modifier = Modifier){
    FotoPerfil(R.drawable.foto_perfil)
}