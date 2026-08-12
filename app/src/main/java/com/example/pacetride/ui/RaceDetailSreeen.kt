package com.example.pacetride.ui

import android.R.attr.fontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- IMAGEN DE PORTADA + TOP BAR (atrás / favorito) + LOGO ----------

@Composable
fun BarraSuperiorDetalle(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.ic_atras),
            contentDescription = stringResource(R.string.atras),
            modifier = Modifier.size(24.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_me_gusta),
            contentDescription = stringResource(R.string.me_gusta),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun BadgeMarca(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 12.dp))
            .background(Color.Black.copy(alpha = 0.7f))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LogoApp(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            stringResource(R.string.pacetride_upper),
            color = colorResource(R.color.electric_lime),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ImagenPortadaCarrera(idImagen: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        Image(
            painter = painterResource(idImagen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        BarraSuperiorDetalle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp)
        )
        BadgeMarca(modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(top = 8.dp))
    }
}

// ---------- TÍTULO, FECHA, UBICACIÓN ----------

@Composable
fun TituloCarrera(
    titulo: String,
    fecha: String,
    ubicacion: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(titulo, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(fecha, color = Color.LightGray, fontSize = 14.sp)
        Text(ubicacion, color = Color.LightGray, fontSize = 14.sp)
    }
}

// ---------- CHIPS DE DISTANCIA (5K / 10K / 21K) ----------

@Composable
fun ChipDistanciaDetalle(
    texto: String,
    seleccionado: Boolean,
    modifier: Modifier = Modifier
) {
    val fondo = if (seleccionado) colorResource(R.color.electric_lime) else colorResource(R.color.graphite)
    val color = if (seleccionado) Color.Black else Color.LightGray
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(fondo)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(texto, color = color, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun DistanciasDetalleRow(
    distancias: List<String>,
    seleccionada: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        distancias.forEach { dist ->
            ChipDistanciaDetalle(
                texto = dist,
                seleccionado = dist == seleccionada,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

// ---------- ITEM DE INFO (ícono + etiqueta + valor) ----------

@Composable
fun InfoItem(
    idIcono: Int,
    etiqueta: String,
    valor: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(etiqueta, color = Color.LightGray, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(valor, color = Color.White, fontSize = 12.sp)
        }
    }
}

@Composable
fun TarjetaInfoCarrera(
    fecha: String,
    hora: String,
    lugar: String,
    distancia: String,
    precio: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.graphite))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            InfoItem(R.drawable.ic_calendario,
                stringResource(R.string.fecha), fecha, Modifier.weight(1f))
            InfoItem(R.drawable.ic_reloj,
                stringResource(R.string.hora_de_salida), hora, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            InfoItem(R.drawable.ubicacion_blanco,
                stringResource(R.string.lugar), lugar, Modifier.weight(1f))
            InfoItem(R.drawable.ic_distancia, stringResource(R.string.distancia), distancia, Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InfoItem(R.drawable.ic_dinero,
            stringResource(R.string.inscripcion), precio, Modifier.fillMaxWidth())
    }
}

// ---------- SECCIÓN CON TÍTULO SIMPLE (18sp, sin padding extra) ----------

@Composable
fun TituloSeccionDetalle(texto: String, modifier: Modifier = Modifier) {
    Text(texto, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold, modifier = modifier)
}

// ---------- MAPA DE RUTA (placeholder de imagen) ----------

@Composable
fun MapaRuta(idImagen: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(idImagen),
        contentDescription = stringResource(R.string.ruta),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

// ---------- ITEM DE "TU INSCRIPCIÓN INCLUYE" ----------

@Composable
fun IncludeItem(idIcono: Int, texto: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(texto, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun IncluyeGrid(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            IncludeItem(R.drawable.ic_camiseta, stringResource(R.string.camiseta_oficial))
            IncludeItem(R.drawable.ic_hidratacion, stringResource(R.string.hidratacion))
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            IncludeItem(R.drawable.ic_medalla, stringResource(R.string.medalla))
            IncludeItem(R.drawable.ic_numero, stringResource(R.string.n_mero_de_competencia))
        }
    }
}

// ---------- BOTÓN INFERIOR CON PRECIO ----------

@Composable
fun BotonInscripcion(precio: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.electric_lime)
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            stringResource(R.string.inscribeme, precio),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun RaceDetailScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(16.dp)
            ) {
                BotonInscripcion(precio = "$145.000 COP")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            ImagenPortadaCarrera(R.drawable.running)

            Column(modifier = Modifier.padding(16.dp)) {
                TituloCarrera(
                    titulo = "Media Maratón Bogotá 2026",
                    fecha = "27 de septiembre de 2026",
                    ubicacion = "Bogotá, Colombia"
                )
                Spacer(modifier = Modifier.height(24.dp))

                DistanciasDetalleRow(
                    distancias = listOf("5K", "10K", "21K"),
                    seleccionada = "21K"
                )
                Spacer(modifier = Modifier.height(24.dp))

                TarjetaInfoCarrera(
                    fecha = "27 de septiembre de 2026",
                    hora = "6:00 a. m.",
                    lugar = "Bogotá, Colombia",
                    distancia = "21 kilómetros",
                    precio = "$145.000 COP"
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(R.string.ltimos_cupos_disponibles),
                    color = colorResource(R.color.pulse_orange),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))

                TituloSeccionDetalle(stringResource(R.string.sobre_la_carrera))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(R.string.descripcion_carrera),
                    color = Color.LightGray,
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
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun RaceDetailScreenPreview() {
    RaceDetailScreen()
}