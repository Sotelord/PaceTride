package com.example.pacetride.ui

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- HEADER "Mi perfil" ----------

@Composable
fun HeaderPerfil(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            stringResource(R.string.mi_perfil),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Image(
            painter = painterResource(R.drawable.ic_configuracion),
            contentDescription = stringResource(R.string.configuracion),
            modifier = Modifier.size(24.dp)
        )
    }
}

// ---------- FOTO + DATOS DEL USUARIO ----------

@Composable
fun FotoPerfil(idImagen: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(idImagen),
        contentDescription = stringResource(R.string.foto_de_perfil),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(100.dp)
            .clip(CircleShape)
    )
}

@Composable
fun UbicacionUsuario(texto: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ubicacion_verde),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(texto, color = Color.LightGray, fontSize = 14.sp)
    }
}

@Composable
fun DatosUsuario(
    nombre: String,
    usuario: String,
    ubicacion: String,
    bio: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(nombre, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(usuario, color = Color.LightGray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        UbicacionUsuario(ubicacion)
        Spacer(modifier = Modifier.height(8.dp))
        Text(bio, color = Color.LightGray, fontSize = 12.sp)
    }
}

// ---------- ESTADÍSTICA (usa un ícono propio, no Icons.Filled) ----------

@Composable
fun StatBox(
    idIcono: Int,
    valor: String,
    etiqueta: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(78.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.graphite))
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(valor, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(etiqueta, color = Color.LightGray, fontSize = 11.sp)
    }
}

@Composable
fun EstadisticasRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatBox(R.drawable.ic_medalla, "12", stringResource(R.string.carreras))
        StatBox(R.drawable.ic_distancia, "186,4 km", stringResource(R.string.distancia))
        StatBox(R.drawable.ic_cronometro, "48:32", stringResource(R.string.mejor_10k))
        StatBox(R.drawable.ic_cronometro, "1:52:40", stringResource(R.string.mejor_21k))
    }
}

// ---------- TARJETA DE PRÓXIMA CARRERA CON GRÁFICO ----------

@Composable
fun GraficoProgreso(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(0f, size.height)
            quadraticBezierTo(size.width * 0.25f, size.height * 0.9f, size.width * 0.5f, size.height * 0.7f)
            quadraticBezierTo(size.width * 0.75f, size.height * 0.5f, size.width, size.height * 0.3f)
        }
        drawPath(
            path = path,
            color = Color(0xFFC6FF00),
            style = Stroke(width = 4f)
        )
        drawCircle(
            color = Color.Red,
            radius = 8f,
            center = Offset(size.width * 0.65f, size.height * 0.6f)
        )
    }
}

@Composable
fun NextRaceCardWithGraph(
    titulo: String,
    fecha: String,
    distancia: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.graphite))
    ) {
        GraficoProgreso(modifier = Modifier.fillMaxSize())

        Column(modifier = Modifier.padding(16.dp)) {
            Text(titulo, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("$fecha • $distancia", color = Color.LightGray, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    stringResource(R.string.inscrito),
                    color = colorResource(R.color.electric_lime),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(R.drawable.ic_check),
                    contentDescription = null,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}

// ---------- TARJETA DE HISTORIAL ----------

@Composable
fun HistoryRaceCard(
    titulo: String,
    resultado: String,
    fecha: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.graphite))
            .padding(12.dp)
    ) {
        Text(
            titulo,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(resultado, color = Color.LightGray, fontSize = 12.sp)
        Text(fecha, color = Color.LightGray, fontSize = 10.sp)
    }
}

data class CarreraHistorial(
    val titulo: String,
    val resultado: String,
    val fecha: String
)

@Composable
fun HistorialRow(carreras: List<CarreraHistorial>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(carreras) { carrera ->
            HistoryRaceCard(carrera.titulo, carrera.resultado, carrera.fecha)
        }
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val historial = listOf(
        CarreraHistorial("Carrera 10K Bogotá", "10K • 49:12", "15 de junio de 2026"),
        CarreraHistorial("Carrera Universitaria Javeriana", "5K • 23:48", "20 de mayo de 2026"),
        CarreraHistorial("Bogotá Night Run", "10K • 50:31", "18 de abril de 2026")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HeaderPerfil()
            Spacer(modifier = Modifier.height(24.dp))

            FotoPerfil(R.drawable.foto_perfil)
            Spacer(modifier = Modifier.height(12.dp))

            DatosUsuario(
                nombre = "Santiago Rayo",
                usuario = "@santiagorayo",
                ubicacion = "Bogotá, Colombia",
                bio = "Runner • Siempre buscando mi próximo reto 🏃"
            )
            Spacer(modifier = Modifier.height(24.dp))

            AppButton(
                textoBoton = stringResource(R.string.editar_perfil),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            SeccionTitulo(
                stringResource(R.string.mis_estadisticas),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp)
            )
            EstadisticasRow()
            Spacer(modifier = Modifier.height(32.dp))

            SeccionTitulo(
                stringResource(R.string.proximas_carreras),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp)
            )
            NextRaceCardWithGraph(
                titulo = "Media Maratón Bogotá 2026",
                fecha = "27 de septiembre",
                distancia = "21K"
            )
            Spacer(modifier = Modifier.height(32.dp))

            SeccionTitulo(
                stringResource(R.string.historial_de_carreras),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp)
            )
            HistorialRow(historial)
            Spacer(modifier = Modifier.height(20.dp))
        }
        BottomNavBar()
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun ProfileScreenPreview() {
    ProfileScreen()
}