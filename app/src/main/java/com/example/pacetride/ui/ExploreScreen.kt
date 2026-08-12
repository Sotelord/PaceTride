package com.example.pacetride.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- BARRA DE BÚSQUEDA + BOTÓN FILTROS ----------

@Composable
fun BarraBusqueda(modifier: Modifier = Modifier) {
    var texto by remember { mutableStateOf("") }
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(colorResource(R.color.graphite))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box {
            if (texto.isEmpty()) {
                Text(
                    stringResource(R.string.buascar_carreras_ciudades_o_eventos),
                    color = Color.Gray,
                    fontSize = 15.sp
                )
            }
            BasicTextField(
                value = texto,
                onValueChange = { texto = it },
                textStyle = androidx.compose.ui.text.TextStyle(
                    color = Color.White,
                    fontSize = 15.sp
                ),
                cursorBrush = androidx.compose.ui.graphics.SolidColor(colorResource(R.color.electric_lime)),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun BotonFiltros(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .border(1.dp, colorResource(R.color.electric_lime), RoundedCornerShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_filter),
            contentDescription = stringResource(R.string.filtros),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            stringResource(R.string.filtros),
            color = colorResource(R.color.electric_lime),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BusquedaYFiltros(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BarraBusqueda(modifier = Modifier.weight(1f))
        BotonFiltros()
    }
}

// ---------- CHIPS DE FILTRO (Todas / 5K / 10K / ...) ----------

@Composable
fun FiltroChip(
    texto: String,
    seleccionado: Boolean,
    modifier: Modifier = Modifier
) {
    val colorFondo = if (seleccionado) colorResource(R.color.electric_lime) else colorResource(R.color.graphite)
    val colorTexto = if (seleccionado) Color.Black else Color.White

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(colorFondo)
            .padding(horizontal = 18.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(texto, color = colorTexto, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        if (seleccionado) {
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Text("✓", color = colorResource(R.color.electric_lime), fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun FiltrosRow(modifier: Modifier = Modifier) {
    val filtros = listOf("Todas", "5K", "10K", "21K", "42K")
    var seleccionado by remember { mutableStateOf("Todas") }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(filtros) { filtro ->
            FiltroChip(
                texto = filtro,
                seleccionado = filtro == seleccionado,
                modifier = Modifier.clip(RoundedCornerShape(50))
            )
        }
    }
}

// ---------- TARJETA DE CARRERA (grilla explorar) ----------

@Composable
fun ExplorarRaceCard(
    idImagen: Int,
    titulo: String,
    ubicacion: String,
    fecha: String,
    distancia: String,
    precio: String? = null,
    ultimosCupos: Boolean = false,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.graphite))
    ) {
        Image(
            painter = painterResource(idImagen),
            contentDescription = titulo,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.3f)
        )
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                titulo,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                "$ubicacion • $fecha",
                color = Color.LightGray,
                fontSize = 12.sp
            )

            if (ultimosCupos) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    stringResource(R.string.ultimos_cupos),
                    color = colorResource(R.color.pulse_orange),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            } else if (precio != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "$distancia • $precio",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                AppButton(
                    textoBoton = stringResource(R.string.ver_detalles),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

// ---------- GRILLA DE CARRERAS ----------

data class CarreraExplorar(
    val idImagen: Int,
    val titulo: String,
    val ubicacion: String,
    val fecha: String,
    val distancia: String,
    val precio: String? = null,
    val ultimosCupos: Boolean = false
)

@Composable
fun GrillaCarreras(
    carreras: List<CarreraExplorar>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(carreras) { carrera ->
            ExplorarRaceCard(
                idImagen = carrera.idImagen,
                titulo = carrera.titulo,
                ubicacion = carrera.ubicacion,
                fecha = carrera.fecha,
                distancia = carrera.distancia,
                precio = carrera.precio,
                ultimosCupos = carrera.ultimosCupos
            )
        }
    }
}

// ---------- ENCABEZADO "Explorar" ----------

@Composable
fun HeaderExplorar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LogoApp(modifier = Modifier.height(32.dp))
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            stringResource(R.string.explorar),
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun ExploreScreen(modifier: Modifier = Modifier) {
    val carreras = listOf(
        CarreraExplorar(R.drawable.running, "Carrera Atlética Bogotá 10K", "Bogotá", "15 de agosto", "10K", "$90.000 COP"),
        CarreraExplorar(R.drawable.running, "Corre por Bogotá 5K", "Bogotá", "30 de agosto", "5K", "$65.000 COP"),
        CarreraExplorar(R.drawable.running, "Media Maratón Bogotá 2026", "Bogotá", "27 de septiembre", "21K", "$145.000 COP"),
        CarreraExplorar(R.drawable.running, "Correra 0 Bogotá", "Bogotá", "27 de agosto", "", ultimosCupos = true)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HeaderExplorar()
            Spacer(modifier = Modifier.height(16.dp))
            BusquedaYFiltros()
            Spacer(modifier = Modifier.height(14.dp))
            FiltrosRow()
            SeccionTitulo(
                stringResource(R.string.carreras_cerca_de_ti),
                modifier = Modifier.padding(horizontal = 0.dp)
            )
            GrillaCarreras(carreras = carreras, modifier = Modifier.weight(1f))
        }
        BottomNavBar()
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ExploreScreenPreview() {
    ExploreScreen()
}
