package com.example.pacetride.ui.screens.profile.components.estadisticas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun StatBox(
    idIcono: Int,
    valor: String,
    etiqueta: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(78.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.graphite))
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(valor, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(etiqueta, color = Color.LightGray, fontSize = 11.sp)
    }
}

@Composable
@Preview
fun StatBoxPreview(modifier: Modifier = Modifier){
    StatBox(
        R.drawable.ic_medalla,
        "12",
        stringResource(R.string.carreras)
    )
}

@Composable
@Preview
fun StatBoxPreview2(modifier: Modifier = Modifier){
    StatBox(
        R.drawable.ic_distancia,
        "186,4 km",
        stringResource(R.string.distancia)
    )
}

@Composable
@Preview
fun StatBoxPreview3(modifier: Modifier = Modifier){
    StatBox(
        R.drawable.ic_cronometro,
        "48:32",
        stringResource(R.string.mejor_10k)
    )
}

@Composable
@Preview
fun StatBoxPreview4(modifier: Modifier = Modifier){
    StatBox(
        R.drawable.ic_cronometro,
        "1:52:40",
        stringResource(R.string.mejor_21k)
    )
}