package com.example.pacetride.ui.screens.home.components.header

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun MensajeMotivacion(modifier: Modifier = Modifier) {
    Text(
        stringResource(R.string.listo_para_tu_pr_xima_carrera),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        modifier = modifier
    )
}

@Composable
@Preview
fun MensajeMotivacionPreview(modifier: Modifier = Modifier){
    MensajeMotivacion()
}