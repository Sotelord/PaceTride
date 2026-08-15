package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

// ---------- MAPA DE RUTA (placeholder de imagen) ----------

@Composable
fun MapaRuta(idImagen: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(idImagen),
        contentDescription = stringResource(R.string.ruta),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
@Preview
fun MapaRutaPreview(modifier: Modifier = Modifier){
    MapaRuta(R.drawable.mapa_ruta)
}