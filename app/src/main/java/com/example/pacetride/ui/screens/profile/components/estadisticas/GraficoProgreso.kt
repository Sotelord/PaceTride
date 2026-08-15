package com.example.pacetride.ui.screens.profile.components.estadisticas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GraficoProgreso(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(0f, size.height)
            quadraticBezierTo(size.width * 0.25f, size.height * 0.9f, size.width * 0.5f, size.height * 0.7f)
            quadraticBezierTo(size.width * 0.75f, size.height * 0.5f, size.width, size.height * 0.3f)
        }
        drawPath(
            path = path,
            color = Color(0xFFC6FF00),
            style = Stroke(width = 4f)
        )
        drawCircle(
            color = Color.Red,
            radius = 8f,
            center = Offset(size.width * 0.65f, size.height * 0.6f)
        )
    }
}

@Composable
@Preview
fun GraficoProgresoPreview(modifier: Modifier = Modifier){
    GraficoProgreso()
}