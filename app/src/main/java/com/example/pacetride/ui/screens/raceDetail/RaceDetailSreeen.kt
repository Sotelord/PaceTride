package com.example.pacetride.ui.screens.raceDetail

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.R
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.aPrecioCop
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.ui.screens.raceDetail.components.content.BotonInscripcion
import com.example.pacetride.ui.screens.raceDetail.components.content.DistanciasDetalleRow
import com.example.pacetride.ui.screens.raceDetail.components.content.ImagenPortadaCarrera
import com.example.pacetride.ui.screens.raceDetail.components.content.IncluyeGrid
import com.example.pacetride.ui.screens.raceDetail.components.content.MapaRuta
import com.example.pacetride.ui.screens.raceDetail.components.content.TarjetaInfoCarrera
import com.example.pacetride.ui.screens.raceDetail.components.content.TituloCarrera
import com.example.pacetride.ui.utils.AppButton
import com.example.pacetride.ui.utils.TituloSeccionDetalle

// ---------- CONTENIDO ----------

@Composable
fun RaceDetailScreenContent(
    carrera: Carrera,
    kmSeleccionado: Int,
    onSeleccionarKm: (Int) -> Unit,
    atrasPressed: () -> Unit,
    inscribemePressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        ImagenPortadaCarrera(carrera.idImagen ?: R.drawable.running, atrasPressed)

        Column(modifier = Modifier.padding(16.dp)) {
            TituloCarrera(
                titulo = carrera.nombre,
                fecha = carrera.fecha,
                ubicacion = carrera.ubicacion
            )
            Spacer(modifier = Modifier.height(24.dp))

            DistanciasDetalleRow(
                distancias = carrera.distanciasDisponiblesKm.map { "${it}K" },
                seleccionada = "${kmSeleccionado}K",
                onSeleccionar = { label -> onSeleccionarKm(label.removeSuffix("K").toInt()) }
            )
            Spacer(modifier = Modifier.height(24.dp))

            TarjetaInfoCarrera(
                fecha = carrera.fecha,
                hora = "6:00 a. m.",
                lugar = carrera.ubicacion,
                distancia = "$kmSeleccionado kilómetros",
                precio = carrera.calcularPrecio(kmSeleccionado).aPrecioCop()
            )

            if (carrera.ultimosCupos == true) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(R.string.ltimos_cupos_disponibles),
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            TituloSeccionDetalle(stringResource(R.string.sobre_la_carrera))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                carrera.descripcion,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(24.dp))

            TituloSeccionDetalle(stringResource(R.string.ruta))
            Spacer(modifier = Modifier.height(8.dp))
            MapaRuta(R.drawable.mapa_ruta)
            Spacer(modifier = Modifier.height(24.dp))

            TituloSeccionDetalle(stringResource(R.string.tu_inscripcion_incluye))
            Spacer(modifier = Modifier.height(16.dp))
            IncluyeGrid()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun RaceDetailScreen(
    raceId: Int,
    atrasPressed: () -> Unit,
    inscribemePressed: () -> Unit,
    escribirResenaPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val carrera = LocalCarreraProvider.listCarrera.find {it.id == raceId}

    if (carrera != null){
        var kmSeleccionado by remember { mutableIntStateOf(carrera.distanciaPrincipalKm) }
        val precioActual = carrera.calcularPrecio(kmSeleccionado)

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            RaceDetailScreenContent(
                carrera = carrera,
                kmSeleccionado = kmSeleccionado,
                onSeleccionarKm = { kmSeleccionado = it },
                atrasPressed = atrasPressed,
                inscribemePressed = inscribemePressed,
                modifier = Modifier.weight(1f)
            )

            AppButton(
                textoBoton = stringResource(R.string.escribir_resena),
                onClick = {
                    Log.d("ExplorerScreen", "Escribir reseña clicked")
                    escribirResenaPressed()
                },
                modifier = Modifier.fillMaxWidth()
            )

            // ---------- "bottomBar" manual ----------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                BotonInscripcion(inscribemePressed, precio = precioActual.aPrecioCop())
            }
        }
    } else {
        Text("Carrera no encontrada")
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun RaceDetailScreenPreview() {
    val carrera = LocalCarreraProvider.listCarrera[2]
    PacetrideTheme(darkTheme = true) {
        RaceDetailScreen(
            inscribemePressed = {},
            raceId = 2,
            atrasPressed = {},
            escribirResenaPressed = {}
        )
    }
}