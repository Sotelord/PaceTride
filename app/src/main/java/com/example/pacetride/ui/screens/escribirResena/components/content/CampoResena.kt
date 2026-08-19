package com.example.pacetride.ui.screens.escribirResena.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun CampoResena(
    modifier: Modifier = Modifier,
    texto: String,
    onTextoChange: (String) -> Unit,
    maxCaracteres: Int = 500
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(colorResource(R.color.graphite))
                .padding(14.dp)
        ) {
            if (texto.isEmpty()) {
                Text(
                    stringResource(R.string.placeholder_resena),
                    color = colorResource(R.color.mist),
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }
            BasicTextField(
                value = texto,
                onValueChange = { nuevo ->
                    if (nuevo.length <= maxCaracteres) onTextoChange(nuevo)
                },
                textStyle = TextStyle(color = colorResource(R.color.ice), fontSize = 13.sp, lineHeight = 18.sp),
                cursorBrush = SolidColor(colorResource(R.color.electric_lime)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "${texto.length} / $maxCaracteres",
            color = colorResource(R.color.mist),
            fontSize = 11.sp,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
@Preview
fun CampoResenaPreview() {
    CampoResena(
        texto = "",
        onTextoChange = {}
    )
}

@Composable
@Preview
fun CampoResenaPreview2() {
    CampoResena(
        texto = "Hola",
        onTextoChange = {}
    )
}

@Composable
@Preview
fun CampoResenaPreview3() {
    CampoResena(
        texto = "123456789",
        onTextoChange = {}
    )
}