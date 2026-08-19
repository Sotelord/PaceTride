package com.example.pacetride.ui.screens.publicprofile.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun EstadisticasRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ItemEstadistica("Carreras", "18", R.drawable.ic_medalla)
        ItemEstadistica("Recorridos", "246 km", R.drawable.ic_distancia)
        ItemEstadistica("Mejor 10K", "47:35", R.drawable.ic_cronometro)
        ItemEstadistica("Mejor 21K", "1:48:52", R.drawable.ic_cronometro)
    }
}

@Composable
fun ItemEstadistica(etiqueta: String, valor: String, iconoRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .height(86.dp)
            .background(Color(0xFF2A2A2A), RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconoRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = valor, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = etiqueta, color = Color.LightGray, fontSize = 12.sp)
    }
}
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun EstadisticasRowPreview() {
    EstadisticasRow()
}