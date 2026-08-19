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

@Composable
fun BotonInscripcion(precio: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { Log.d("RaceDetailScreen", "Inscricion clicked") },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.electric_lime)
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            stringResource(R.string.inscribeme, precio),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
@Preview
fun BotonInscripcionPreview(modifier: Modifier = Modifier){
    BotonInscripcion("$145.000 COP")
}