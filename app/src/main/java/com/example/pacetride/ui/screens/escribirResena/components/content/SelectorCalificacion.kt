package com.example.pacetride.ui.screens.escribirResena.components.content

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectorCalificacion(
    calificacion: Int,
    onCalificacionChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            for (estrella in 1..5) {
                EstrellaCalificable(
                    llena = estrella <= calificacion,
                    onClick = {
                        Log.d("EscribirResenaScreen", "Estrella clicked")
                        onCalificacionChange(estrella)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "${calificacion} / 5",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF121212)
fun SelectorCalificacionInteractivoPreview() {
    var calificacionActual by remember { mutableStateOf(0) }

    SelectorCalificacion(
        calificacion = calificacionActual,
        onCalificacionChange = { nuevaCalificacion ->
            calificacionActual = nuevaCalificacion
        }
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF121212)
fun SelectorCalificacionInteractivoPreview2() {
    var calificacionActual by remember { mutableStateOf(1) }

    SelectorCalificacion(
        calificacion = calificacionActual,
        onCalificacionChange = { nuevaCalificacion ->
            calificacionActual = nuevaCalificacion
        }
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF121212)
fun SelectorCalificacionInteractivoPreview3() {
    var calificacionActual by remember { mutableStateOf(2) }

    SelectorCalificacion(
        calificacion = calificacionActual,
        onCalificacionChange = { nuevaCalificacion ->
            calificacionActual = nuevaCalificacion
        }
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF121212)
fun SelectorCalificacionInteractivoPreview4() {
    var calificacionActual by remember { mutableStateOf(3) }

    SelectorCalificacion(
        calificacion = calificacionActual,
        onCalificacionChange = { nuevaCalificacion ->
            calificacionActual = nuevaCalificacion
        }
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF121212)
fun SelectorCalificacionInteractivoPreview5() {
    var calificacionActual by remember { mutableStateOf(4) }

    SelectorCalificacion(
        calificacion = calificacionActual,
        onCalificacionChange = { nuevaCalificacion ->
            calificacionActual = nuevaCalificacion
        }
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF121212)
fun SelectorCalificacionInteractivoPreview6() {
    var calificacionActual by remember { mutableStateOf(5) }

    SelectorCalificacion(
        calificacion = calificacionActual,
        onCalificacionChange = { nuevaCalificacion ->
            calificacionActual = nuevaCalificacion
        }
    )
}