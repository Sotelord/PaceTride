package com.example.pacetride.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pacetride.R

@Composable
fun RaceAsyncImage(
    modifier: Modifier = Modifier,
    raceImage: String?,
    contentDescription: String = "",
    background: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    var esExito by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .then(
                if (!esExito) Modifier.background(background)
                else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(raceImage)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.ic_race),
            placeholder = painterResource(id = R.drawable.ic_loading),
            onSuccess = { esExito = true },
            onError = { esExito = false },
            onLoading = { esExito = false },
            contentDescription = contentDescription,
            contentScale = if (esExito) ContentScale.Crop else ContentScale.Fit,
            colorFilter = if (!esExito) ColorFilter.tint(MaterialTheme.colorScheme.onBackground) else null,
            modifier = Modifier.matchParentSize()
        )
    }
}

@Composable
@Preview
fun RaceAsyncImagePreview(){
    RaceAsyncImage(
        raceImage = "",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.3f)
    )
}