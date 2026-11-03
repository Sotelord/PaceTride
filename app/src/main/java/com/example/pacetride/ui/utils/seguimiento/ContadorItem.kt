package com.example.pacetride.ui.utils.seguimiento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ContadorItem(
    cantidad: Int,
    etiqueta: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = cantidad.toString(),
            modifier = Modifier.testTag("perfil$etiqueta"),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = etiqueta,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
@Preview
fun ContadorItemPreview() {
    PacetrideTheme(darkTheme = true) {
        ContadorItem(cantidad = 10, etiqueta = "Seguidores", onClick = {})
    }
}

@Composable
@Preview
fun ContadorItemPreview2() {
    PacetrideTheme(darkTheme = true) {
        ContadorItem(cantidad = 10, etiqueta = "Seguidos", onClick = {})
    }
}