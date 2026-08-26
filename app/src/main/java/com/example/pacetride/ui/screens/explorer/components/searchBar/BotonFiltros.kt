package com.example.pacetride.ui.screens.explorer.components.searchBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun BotonFiltros(
    activo: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val colorFondo = if (activo) MaterialTheme.colorScheme.primaryContainer else Color.Transparent
    val colorContenido = if (activo) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primaryContainer

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(colorFondo)
            .border(1.dp, MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(14.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_filter),
            contentDescription = stringResource(R.string.filtros),
            colorFilter = ColorFilter.tint(colorContenido),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            stringResource(R.string.filtros),
            color = colorContenido,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun BotonFiltrosPreview() {
    BotonFiltros(activo = false)
}

@Composable
@Preview
fun BotonFiltrosActivoPreview() {
    PacetrideTheme(darkTheme = true) {
        BotonFiltros(activo = true)
    }
}

@Composable
@Preview
fun BotonFiltrosInteractivoPreview() {
    var activo by remember { mutableStateOf(false) }
    BotonFiltros(activo = activo, onClick = { activo = !activo })
}