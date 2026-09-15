package com.example.pacetride.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pacetride.R
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ProfileAsyncImage(
    modifier: Modifier = Modifier,
    imageURL: String?,
    size: Int,
    imgSize: Int,
    background: Color = MaterialTheme.colorScheme.secondaryContainer,
    errorIcon: Int = R.drawable.ic_user_profile
) {
    var esExito by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .size(size.dp)
            .clip(CircleShape)
            .then(
                if (!esExito) Modifier.background(background)
                else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL)
                .crossfade(true)
                .build(),
            error = painterResource(id = errorIcon),
            placeholder = painterResource(id = R.drawable.ic_loading),
            onSuccess = { esExito = true },
            onError = { esExito = false },
            onLoading = { esExito = false },
            contentDescription = stringResource(R.string.foto_de_perfil),
            contentScale = if (esExito) ContentScale.Crop else ContentScale.Fit,
            colorFilter = if (!esExito) ColorFilter.tint(MaterialTheme.colorScheme.onBackground) else null,
            modifier = if (esExito) Modifier.matchParentSize() else Modifier.size(imgSize.dp)
        )
    }
}

@Composable
@Preview
fun ProfileAsyncImagePreview(){
    ProfileAsyncImage(
        imageURL = "https://elcomercio.pe/resizer/v2/H32GC2RMAFCDPKSX25KBSPJQM4.jpg?auth=9fee2012f017b5bc588bd0e5a4f0f91e5b65b32082711815c018e1acd41d14ab&width=2400&height=1620&quality=75&smart=true",
        size = 100,
        imgSize = 48
    )
}

@Composable
@Preview
fun ProfileAsyncImagePreview2(){
    PacetrideTheme(darkTheme = true) {
        ProfileAsyncImage(
            imageURL = null,
            size = 100,
            imgSize = 48
        )
    }
}