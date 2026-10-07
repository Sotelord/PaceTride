package com.example.pacetride.ui.screens.raceDetail.components

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.Resena
import com.example.pacetride.data.aPrecioCop
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.data.local.LocalResenaProvider
import com.example.pacetride.ui.screens.raceDetail.components.content.DistanciasDetalleRow
import com.example.pacetride.ui.screens.raceDetail.components.content.ImagenPortadaCarrera
import com.example.pacetride.ui.screens.raceDetail.components.content.IncluyeGrid
import com.example.pacetride.ui.screens.raceDetail.components.content.MapaRuta
import com.example.pacetride.ui.screens.raceDetail.components.content.TarjetaInfoCarrera
import com.example.pacetride.ui.screens.raceDetail.components.content.TituloCarrera
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.AppButton
import com.example.pacetride.ui.utils.TituloSeccionDetalle
import com.example.pacetride.ui.utils.reviews.ResenasList

@Composable
fun RaceDetailScreenContent(
    carrera: Carrera,
    kmSeleccionado: Int,
    onSeleccionarKm: (Int) -> Unit,
    atrasPressed: () -> Unit,
    resenas: List<Resena>,
    escribirResenaPressed: (String) -> Unit,
    onClickUsuario: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        ImagenPortadaCarrera(
            raceImageUrl = carrera.raceImageUrl,
            contentDescription = carrera.nombre,
            atrasPressed = atrasPressed
        )

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

            Spacer(modifier = Modifier.height(24.dp))

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                textoBoton = "Escribir reseña",
                onClick = {
                    escribirResenaPressed(carrera.id)
                    Log.d("RaceDetailScreen", "Escribir reseña pressed")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            TituloSeccionDetalle(
                texto = stringResource(R.string.ultimas_resenas),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            ResenasList(resenas = resenas, onClickUsuario = onClickUsuario)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RaceDetailScreenContentPreview() {
    val carrera = LocalCarreraProvider.listCarrera[0]
    val resenas = LocalResenaProvider.listaResenas
    PacetrideTheme(darkTheme = true) {
        RaceDetailScreenContent(
            carrera = carrera,
            kmSeleccionado = 1,
            onSeleccionarKm = {},
            atrasPressed = {},
            resenas = resenas,
            escribirResenaPressed = {},
            onClickUsuario = {},
        )
    }
}