package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun InfoItem(
    idIcono: Int,
    etiqueta: String,
    valor: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(etiqueta, color = Color.LightGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(valor, color = Color.White, fontSize = 12.sp)
        }
    }
}

@Composable
@Preview
fun InfoItemPreview(modifier: Modifier = Modifier){
    InfoItem(
        R.drawable.ic_calendario,
        stringResource(R.string.fecha),
        "fecha"
    )
}

@Composable
@Preview
fun InfoItemPreview2(modifier: Modifier = Modifier){
    InfoItem(
        R.drawable.ic_reloj,
        stringResource(R.string.hora_de_salida),
        "hora"
    )
}

@Composable
@Preview
fun InfoItemPreview3(modifier: Modifier = Modifier){
    InfoItem(
        R.drawable.ubicacion_blanco,
        stringResource(R.string.lugar),
        "lugar"
    )
}

@Composable
@Preview
fun InfoItemPreview4(modifier: Modifier = Modifier){
    InfoItem(
        R.drawable.ic_distancia, stringResource(R.string.distancia),
        "distancia"
    )
}

@Composable
@Preview
fun InfoItemPreview5(modifier: Modifier = Modifier){
    InfoItem(
        R.drawable.ic_dinero,
        stringResource(R.string.inscripcion),
        "precio"
    )
}