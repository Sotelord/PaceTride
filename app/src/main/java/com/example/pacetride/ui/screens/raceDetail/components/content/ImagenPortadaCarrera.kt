package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.screens.raceDetail.components.header.BadgeMarca
import com.example.pacetride.ui.screens.raceDetail.components.header.BarraSuperiorDetalle
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.RaceAsyncImage

@Composable
fun ImagenPortadaCarrera(
    raceImageUrl: String?,
    contentDescription: String,
    atrasPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        RaceAsyncImage(
            modifier = Modifier.fillMaxSize(),
            raceImage = raceImageUrl,
            contentDescription = contentDescription
        )
        BarraSuperiorDetalle(
            atrasPressed = atrasPressed,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
        )
        BadgeMarca(modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(top = 0.dp))
    }
}
@Composable
@Preview
fun ImagenPortadaCarreraPreview(){
    PacetrideTheme(darkTheme = true) {
        ImagenPortadaCarrera(
            raceImageUrl = "",
            contentDescription = "",
            atrasPressed = {}
        )
    }
}