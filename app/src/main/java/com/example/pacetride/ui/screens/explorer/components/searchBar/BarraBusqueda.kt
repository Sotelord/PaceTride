package com.example.pacetride.ui.screens.explorer.components.searchBar

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun BarraBusqueda(
    modifier: Modifier = Modifier
) {
    var texto by remember { mutableStateOf("") }
    val teclado = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(colorResource(R.color.graphite))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))

        Box(modifier = Modifier.weight(1f)) {
            if (texto.isEmpty()) {
                Text(
                    stringResource(R.string.buscar_carreras_ciudades_o_eventos),
                    color = Color.Gray,
                    fontSize = 15.sp
                )
            }
            BasicTextField(
                value = texto,
                onValueChange = { texto = it },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 15.sp
                ),
                cursorBrush = SolidColor(colorResource(R.color.electric_lime)),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Log.d("ExploreScreen", "Buscando: $texto")
                        teclado?.hide()
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (texto.isNotEmpty()) {
            Spacer(modifier = Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = stringResource(R.string.cerrar),
                modifier = Modifier
                    .size(18.dp)
                    .clickable {
                        Log.d("ExploreScreen", "Búsqueda cancelada")
                        texto = ""
                        teclado?.hide()
                    }
            )
        }
    }
}

@Composable
@Preview
fun BarraBusquedaPreview(modifier: Modifier = Modifier){
    BarraBusqueda()
}