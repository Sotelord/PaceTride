package com.example.pacetride.ui.screens.raceDetail.components.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun IncluyeGrid(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            IncludeItem(R.drawable.ic_camiseta, stringResource(R.string.camiseta_oficial))
            IncludeItem(R.drawable.ic_hidratacion, stringResource(R.string.hidratacion))
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            IncludeItem(R.drawable.ic_medalla, stringResource(R.string.medalla))
            IncludeItem(R.drawable.ic_numero, stringResource(R.string.n_mero_de_competencia))
        }
    }
}

@Composable
@Preview
fun IncluyeGridPreview(modifier: Modifier = Modifier){
    IncluyeGrid()
}