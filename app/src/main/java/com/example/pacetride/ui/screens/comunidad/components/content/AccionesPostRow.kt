package com.example.pacetride.ui.screens.comunidad.components.content

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R

@Composable
fun AccionesPostRow(
    meGustaSeleccionado: Boolean,
    onMeGustaClick: () -> Unit,
    onComentarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BotonAccionPost(
            idIcono = R.drawable.ic_me_gusta,
            texto = stringResource(R.string.me_gusta),
            seleccionado = meGustaSeleccionado,
            onClick = {
                Log.d("ComunidadScreen", "Me gusta clicked")
                onMeGustaClick()
            },
            modifier = Modifier.weight(1f)
        )
        BotonAccionPost(
            idIcono = R.drawable.ic_comentario,
            texto = stringResource(R.string.comentar),
            seleccionado = false,
            onClick = {
                Log.d("ComunidadScreen", "Comentario clicked")
                onComentarClick()
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF121212)
fun AccionesPostRowPreview() { // Corregido: Quitamos el parámetro modifier de aquí
    // Creamos un estado local en el Preview para probar el comportamiento de los botones reunidos
    var meGustaActivo by remember { mutableStateOf(false) }

    AccionesPostRow(
        meGustaSeleccionado = meGustaActivo,
        onMeGustaClick = { meGustaActivo = !meGustaActivo },
        onComentarClick = { /* Acción de comentar */ }
    )
}
