package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(etiqueta, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(valor, color = MaterialTheme.colorScheme.onBackground, fontSize = 12.sp)
        }
    }
}

@Composable
@Preview
fun InfoItemPreview(){
    PacetrideTheme(darkTheme = true) {
        InfoItem(
            idIcono = R.drawable.ic_calendario,
            etiqueta = stringResource(R.string.fecha),
            valor = "fecha"
        )
    }
}

@Composable
@Preview
fun InfoItemPreview2(){
    PacetrideTheme(darkTheme = true) {
        InfoItem(
            idIcono = R.drawable.ic_reloj,
            etiqueta = stringResource(R.string.hora_de_salida),
            valor = "hora"
        )
    }
}

@Composable
@Preview
fun InfoItemPreview3(){
    PacetrideTheme(darkTheme = true) {
        InfoItem(
            idIcono = R.drawable.ubicacion_blanco,
            etiqueta = stringResource(R.string.lugar),
            valor = "lugar"
        )
    }
}

@Composable
@Preview
fun InfoItemPreview4(){
    PacetrideTheme(darkTheme = true) {
        InfoItem(
            idIcono = R.drawable.ic_distancia,
            etiqueta = stringResource(R.string.distancia),
            valor = "distancia"
        )
    }
}

@Composable
@Preview
fun InfoItemPreview5(){
    PacetrideTheme(darkTheme = true) {
        InfoItem(
            idIcono =  R.drawable.ic_dinero,
            etiqueta =  stringResource(R.string.inscripcion),
            valor = "precio"
        )
    }
}