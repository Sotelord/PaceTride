package com.example.pacetride.ui.screens.comunidad.components.header

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.utils.CampanaNotificaciones
import com.example.pacetride.ui.utils.LogoApp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun HeaderComunidad(
    textoBusqueda: String,
    usuario: Usuario,
    onTextoBusquedaChange: (String) -> Unit,
    notificacionButtonPressed: (Int)-> Unit,
    modifier: Modifier = Modifier
) {
    var enModoBusqueda by remember { mutableStateOf(false) }
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
                onValueChange = onTextoBusquedaChange,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                placeholder = { Text(stringResource(R.string.buscar)) },
                singleLine = true,
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
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
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(10.dp)
                    .clickable {
                        Log.d("Comunidad Screen", "Cerrar clicked")
                        enModoBusqueda = false
                        onTextoBusquedaChange("")
                    }
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                LogoApp(modifier = Modifier.height(40.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    stringResource(R.string.comunidad),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.buscar),
                    colorFilter = if (isSystemInDarkTheme()) {
                        null
                    } else {
                        ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                    },
                    modifier = Modifier
                        .size(45.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(10.dp)
                        .clickable {
                            Log.d("Comunidad Screen", "Buscar clicked")
                            enModoBusqueda = true
                        }
                )
                Spacer(modifier = Modifier.width(10.dp))
                CampanaNotificaciones(onClick = {
                    notificacionButtonPressed(usuario.id)
                    Log.d("Comunidad Screen", "Campana notificaciones clicked")
                })
            }
        }
    }

    LaunchedEffect(enModoBusqueda) {
        if (enModoBusqueda) {
            delay(100.milliseconds)
            focusRequester.requestFocus()
        }
    }
}


@Composable
@Preview
fun HeaderComunidadPreview() {
    var texto by remember { mutableStateOf("") }
    val usuario = LocalUsuarioProvider.usuarios[0]
    HeaderComunidad(
        textoBusqueda = texto,
        usuario = usuario,
        onTextoBusquedaChange = { texto = it },
        notificacionButtonPressed = {},
        modifier = Modifier.padding(16.dp)
    )
}