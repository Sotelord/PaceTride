package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.screens.raceDetail.components.header.BadgeMarca
import com.example.pacetride.ui.screens.raceDetail.components.header.BarraSuperiorDetalle

@Composable
fun ImagenPortadaCarrera(idImagen: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        Image(
            painter = painterResource(idImagen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        BarraSuperiorDetalle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
        )
        BadgeMarca(modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(top = 8.dp))
    }
}
@Composable
@Preview
fun ImagenPortadaCarreraPreview(modifier: Modifier = Modifier){
    ImagenPortadaCarrera(R.drawable.running)
}