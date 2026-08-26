package com.example.pacetride.ui.screens.raceDetail.components.content

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.utils.AppButton

@Composable
fun BotonInscripcion(
    inscribemePressed: () -> Unit,
    precio: String,
    modifier: Modifier = Modifier
) {
    AppButton(
        textoBoton = stringResource(R.string.inscribeme, precio),
        onClick = {
            inscribemePressed()
            Log.d("RaceDetailScreen", "Inscripcion clicked")
                  },
        fontSize = 16.sp,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
@Preview
fun BotonInscripcionPreview(){
    BotonInscripcion({},"$145.000 COP")
}