package com.example.pacetride.ui.screens.escribirResena.components.content

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ChipsFlowDestacar(
    opciones: List<String>,
    seleccionadas: Set<String>,
    onToggle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filas = opciones.chunked(3)
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        filas.forEach { fila ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                fila.forEach { opcion ->
                    ChipDestacado(
                        texto = opcion,
                        seleccionado = opcion in seleccionadas,
                        onClick = {
                            Log.d("EscribirResnaScreen", "Chip/Item clicked")
                            onToggle(opcion)
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ChipsFlowDestacarPreview(){
    PacetrideTheme(darkTheme = true) {
        val opcionesDestacar = listOf("Ruta", "Organización", "Ambiente", "Hidratación", "Seguridad", "Kit", "Precio")
        var seleccionadas by remember { mutableStateOf(setOf("Ruta", "Organización", "Ambiente")) }

        Box(modifier = Modifier.padding(16.dp)) {
            ChipsFlowDestacar(
                opciones = opcionesDestacar,
                seleccionadas = seleccionadas,
                onToggle = { opcion ->
                    seleccionadas = if (opcion in seleccionadas) {
                        seleccionadas - opcion
                    } else {
                        seleccionadas + opcion
                    }
                }
            )
        }
    }
}