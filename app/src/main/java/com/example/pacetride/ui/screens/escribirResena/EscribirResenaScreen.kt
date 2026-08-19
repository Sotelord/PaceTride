package com.example.pacetride.ui.screens.escribirResena

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.screens.escribirResena.components.content.BotonPublicarResena
import com.example.pacetride.ui.screens.escribirResena.components.content.CampoResena
import com.example.pacetride.ui.screens.escribirResena.components.content.ChipsFlowDestacar
import com.example.pacetride.ui.screens.escribirResena.components.content.SelectorCalificacion
import com.example.pacetride.ui.screens.escribirResena.components.content.TarjetaResumenCarrera
import com.example.pacetride.ui.screens.escribirResena.components.header.BarraSuperiorConTitulo
import com.example.pacetride.ui.utils.TituloSeccionDetalle

// ---------- CONTENIDO ----------

@Composable
fun EscribirResenaScreenContent(
    calificacion: Int,
    onCalificacionChange: (Int) -> Unit,
    textoResena: String,
    onTextoResenaChange: (String) -> Unit,
    mensajeError: String?,
    opcionesDestacar: List<String>,
    seleccionadas: Set<String>,
    onToggleDestacar: (String) -> Unit,
    onPublicarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        BarraSuperiorConTitulo(stringResource(R.string.escribir_resena))
        Spacer(modifier = Modifier.height(24.dp))

        TarjetaResumenCarrera(
            titulo = "Media Maratón Bogotá 2026",
            fecha = "27 de septiembre de 2026",
            ubicacion = "Bogotá, Colombia"
        )
        Spacer(modifier = Modifier.height(28.dp))

        TituloSeccionDetalle(stringResource(R.string.como_calificarias))
        Spacer(modifier = Modifier.height(16.dp))
        SelectorCalificacion(
            calificacion = calificacion,
            onCalificacionChange = onCalificacionChange,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(28.dp))

        TituloSeccionDetalle(stringResource(R.string.cuentanos_tu_experiencia))
        Spacer(modifier = Modifier.height(12.dp))
        CampoResena(
            texto = textoResena,
            onTextoChange = onTextoResenaChange
        )
        Spacer(modifier = Modifier.height(28.dp))

        TituloSeccionDetalle(stringResource(R.string.que_destacarias))
        Spacer(modifier = Modifier.height(12.dp))

        ChipsFlowDestacar(
            opciones = opcionesDestacar,
            seleccionadas = seleccionadas,
            onToggle = onToggleDestacar
        )
        Spacer(modifier = Modifier.height(32.dp))

        if (mensajeError != null) {
            Text(
                text = mensajeError,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        BotonPublicarResena(onClick = onPublicarClick)

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            stringResource(R.string.tu_resena_ayudara),
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun EscribirResenaScreen(modifier: Modifier = Modifier) {
    var calificacion by remember { mutableIntStateOf(4) }
    var textoResena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    var seleccionadas by remember { mutableStateOf(setOf("Ruta", "Organización", "Ambiente")) }
    val opcionesDestacar = remember {
        listOf("Ruta", "Organización", "Ambiente", "Hidratación", "Seguridad", "Kit", "Precio")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        EscribirResenaScreenContent(
            calificacion = calificacion,
            onCalificacionChange = { calificacion = it },
            textoResena = textoResena,
            onTextoResenaChange = { textoResena = it },
            mensajeError = mensajeError,
            opcionesDestacar = opcionesDestacar,
            seleccionadas = seleccionadas,
            onToggleDestacar = { opcion ->
                seleccionadas = if (opcion in seleccionadas) {
                    seleccionadas - opcion
                } else {
                    seleccionadas + opcion
                }
            },
            onPublicarClick = {
                mensajeError = when {
                    textoResena.isBlank() -> "La reseña está vacía, por ende no se puede publicar"
                    textoResena.length > 500 -> "La reseña supera los 500 caracteres permitidos"
                    else -> null
                }

                if (mensajeError == null) {
                    Log.d("EscribirResenaScreen", "Publicar reseña clicked")
                    Log.d("EscribirResenaScreen", "Reseña: $textoResena")
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun EscribirResenaScreenPreview() {
    EscribirResenaScreen()
}