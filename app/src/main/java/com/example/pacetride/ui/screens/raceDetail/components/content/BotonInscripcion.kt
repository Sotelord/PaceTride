package com.example.pacetride.ui.screens.raceDetail.components.content

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.utils.AppButton

@Composable
fun BotonInscripcion(precio: String, modifier: Modifier = Modifier) {
    AppButton(
        textoBoton = stringResource(R.string.inscribeme, precio),
        onClick = { Log.d("RaceDetailScreen", "Inscripcion clicked") },
        fontSize = 16.sp,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
@Preview
fun BotonInscripcionPreview(modifier: Modifier = Modifier){
    BotonInscripcion("$145.000 COP")
}