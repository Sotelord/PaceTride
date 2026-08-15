package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- ITEM DE "TU INSCRIPCIÓN INCLUYE" ----------

@Composable
fun IncludeItem(idIcono: Int, texto: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(texto, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
@Preview
fun IncludeItemPreview(modifier: Modifier = Modifier){
    IncludeItem(R.drawable.ic_camiseta, stringResource(R.string.camiseta_oficial))
}

@Composable
@Preview
fun IncludeItemPreview2(modifier: Modifier = Modifier){
    IncludeItem(R.drawable.ic_hidratacion, stringResource(R.string.hidratacion))
}

@Composable
@Preview
fun IncludeItemPreview3(modifier: Modifier = Modifier){
    IncludeItem(R.drawable.ic_medalla, stringResource(R.string.medalla))
}

@Composable
@Preview
fun IncludeItemPreview4(modifier: Modifier = Modifier){
    IncludeItem(R.drawable.ic_numero, stringResource(R.string.n_mero_de_competencia))
}