package com.example.pacetride.ui.screens.explorer.components.floatingButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun FloatingButtonComunidad(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_comunidad),
            contentDescription = stringResource(R.string.comunidad),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
@Preview
fun FloatingButtonComunidadPreview() {
    PacetrideTheme(darkTheme = true) {
        FloatingButtonComunidad(onClick = {})
    }
}