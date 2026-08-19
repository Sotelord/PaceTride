package com.example.pacetride.ui.screens.escribirResena.components.content

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.utils.AppButton

@Composable
fun BotonPublicarResena(
    onClick: ()-> Unit = {},
    modifier: Modifier = Modifier
) {
    AppButton(
        textoBoton = stringResource(R.string.publicar_rese_a),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
@Preview
fun BotonPublicarResenaPreview(modifier: Modifier = Modifier){
    BotonPublicarResena()
}