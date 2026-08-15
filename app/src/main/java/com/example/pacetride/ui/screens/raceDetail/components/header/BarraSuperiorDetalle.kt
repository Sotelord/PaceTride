package com.example.pacetride.ui.screens.raceDetail.components.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun BarraSuperiorDetalle(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.ic_atras),
            contentDescription = stringResource(R.string.atras),
            modifier = Modifier.size(24.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_me_gusta),
            contentDescription = stringResource(R.string.me_gusta),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
@Preview
fun BarraSuperiorDetallePreview(modifier: Modifier = Modifier){
    BarraSuperiorDetalle()
}