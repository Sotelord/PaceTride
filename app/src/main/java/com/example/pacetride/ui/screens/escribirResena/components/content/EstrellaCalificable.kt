package com.example.pacetride.ui.screens.escribirResena.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun EstrellaCalificable(
    llena: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.ic_estrella),
        contentDescription = null,
        colorFilter = ColorFilter.tint(
            if (llena) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
            .size(40.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    )
}

@Composable
@Preview
fun EstrellaCalificablePreview(){
    EstrellaCalificable(
        true,
        onClick = {}
        )
}

@Composable
@Preview
fun EstrellaCalificablePreview2(){
    EstrellaCalificable(
        false,
        onClick = {}
    )
}