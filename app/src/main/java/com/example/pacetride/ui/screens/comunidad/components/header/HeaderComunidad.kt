package com.example.pacetride.ui.screens.comunidad.components.header

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.utils.CampanaNotificaciones
import com.example.pacetride.ui.utils.LogoApp
import kotlinx.coroutines.delay

@Composable
fun HeaderComunidad(modifier: Modifier = Modifier) {
    var enModoBusqueda by remember { mutableStateOf(false) }
    var textoBusqueda by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val teclado = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (enModoBusqueda) {
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                placeholder = { Text(stringResource(R.string.buscar)) },
                singleLine = true,
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = colorResource(R.color.ice),
                    unfocusedTextColor = colorResource(R.color.ice),
                    focusedContainerColor = colorResource(R.color.graphite),
                    unfocusedContainerColor = colorResource(R.color.graphite),
                    focusedBorderColor = colorResource(R.color.electric_lime),
                    unfocusedBorderColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Log.d("ComunidadScreen", "Buscando: $textoBusqueda")
                        teclado?.hide()
                    }
                )
            )

            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = stringResource(R.string.cerrar),
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(colorResource(R.color.graphite))
                    .padding(10.dp)
                    .clickable {
                        Log.d("Comunidad Screen", "Cerrar clicked")
                        enModoBusqueda = false
                        textoBusqueda = ""
                    }
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LogoApp(modifier = Modifier.height(40.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    stringResource(R.string.comunidad),
                    color = colorResource(R.color.ice),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.buscar),
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(colorResource(R.color.graphite))
                        .padding(10.dp)
                        .clickable {
                            Log.d("Comunidad Screen", "Buscar clicked")
                            enModoBusqueda = true
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
                CampanaNotificaciones(onClick = { Log.d("Comunidad Screen", "Campana notificaciones clicked") })
            }
        }
    }

    LaunchedEffect(enModoBusqueda) {
        if (enModoBusqueda) {
            delay(100)
            focusRequester.requestFocus()
        }
    }
}


@Composable
@Preview
fun HeaderComunidadPreview() {
    HeaderComunidad(
        modifier = Modifier.padding(16.dp)
    )
}