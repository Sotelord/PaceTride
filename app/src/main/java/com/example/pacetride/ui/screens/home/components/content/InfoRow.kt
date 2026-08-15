package com.example.pacetride.ui.screens.home.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- FILA DE INFO (fecha / ubicación / distancia) ----------

@Composable
fun InfoRow(
    idIcono: Int,
    texto: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(texto, color = Color.LightGray, fontSize = 14.sp)
    }
}

@Composable
@Preview
fun InfoRowPreview(modifier: Modifier = Modifier){
    Box {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ){
            InfoRow(R.drawable.ic_calendario, "27 de septiembre de 2026")
            Spacer(modifier = Modifier.size(8.dp)) // Espacio opcional entre filas
            InfoRow(R.drawable.ubicacion_blanco, "Bogotá, Colombia")
            Spacer(modifier = Modifier.size(8.dp))
            InfoRow(R.drawable.corriendo, "21K")
        }
    }
}
