package com.example.pacetride.ui.screens.escribirResena.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.ui.screens.escribirResena.components.content.BotonPublicarResena
import com.example.pacetride.ui.screens.escribirResena.components.content.CampoResena
import com.example.pacetride.ui.screens.escribirResena.components.content.ChipsFlowDestacar
import com.example.pacetride.ui.screens.escribirResena.components.content.SelectorCalificacion
import com.example.pacetride.ui.screens.escribirResena.components.content.TarjetaResumenCarrera
import com.example.pacetride.ui.screens.escribirResena.components.header.BarraSuperiorConTitulo
import com.example.pacetride.ui.utils.TituloSeccionDetalle

@Composable
fun EscribirResenaScreenContent(
    carrera: Carrera,
    calificacion: Int,
    onCalificacionChange: (Int) -> Unit,
    textoResena: String,
    onTextoResenaChange: (String) -> Unit,
    opcionesDestacar: List<String>,
    seleccionadas: Set<String>,
    onToggleDestacar: (String) -> Unit,
    onPublicarClick: () -> Unit,
    atrasPressed: () -> Unit,
    mostrarMensajeError: Boolean,
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        BarraSuperiorConTitulo(
            stringResource(R.string.escribir_resena),
            atrasPressed = atrasPressed
        )
        Spacer(modifier = Modifier.height(24.dp))

        TarjetaResumenCarrera(carrera)
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

        if (mostrarMensajeError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        BotonPublicarResena(onClick = {
            onPublicarClick()
        })

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            stringResource(R.string.tu_resena_ayudara),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun EscribirResenaScreenContentPreview(){
    val carerra = LocalCarreraProvider.listCarrera[2]
    var seleccionadas by remember { mutableStateOf(setOf("Ruta", "Organización", "Ambiente")) }
    EscribirResenaScreenContent(
        atrasPressed = {},
        calificacion = 4,
        carrera = carerra,
        errorMessage = "hola",
        mostrarMensajeError = true,
        onPublicarClick = {},
        onCalificacionChange = {},
        onTextoResenaChange = {},
        onToggleDestacar = {},
        opcionesDestacar = listOf("su", "no"),
        seleccionadas = seleccionadas,
        textoResena = ""
    )
}