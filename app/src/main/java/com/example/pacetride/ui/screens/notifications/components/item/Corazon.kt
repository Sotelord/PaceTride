package com.example.pacetride.ui.screens.notifications.components.item

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
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun Corazon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Image(
        painter = painterResource(id = R.drawable.ic_me_gusta),
        contentDescription = "Me gusta",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier
            .size(24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
    )
}

@Composable
@Preview
fun CorazonPreview() {
    PacetrideTheme(darkTheme = true) {
        Corazon()
    }
}
