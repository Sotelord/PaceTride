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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- HEADER ----------

@Composable
fun CampanaNotificaciones(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.campana),
            contentDescription = stringResource(R.string.campana_notificaciones),
            modifier = Modifier.size(28.dp)
        )
        Box(
            modifier = Modifier
                .size(8.dp)
                .align(Alignment.TopEnd)
                .background(Color.Red, shape = RoundedCornerShape(50))
        )
    }
}

@Composable
fun MensajeBienvenida(
    nombre: String,
    modifier: Modifier = Modifier
) {
    Text(
        stringResource(R.string.bienvenida, nombre),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun MensajeMotivacion(modifier: Modifier = Modifier) {
    Text(
        stringResource(R.string.listo_para_tu_pr_xima_carrera),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray,
        modifier = modifier
    )
}

@Composable
fun LogoApp(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "Logo Pacetride",
        modifier = modifier
    )
}

@Composable
fun HeaderHomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .background(Color.Black)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            LogoApp(
                modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.Center)
            )
            CampanaNotificaciones(
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        MensajeBienvenida("Santiago")
        MensajeMotivacion()
    }
}

// ---------- FILA DE INFO (fecha / ubicación / distancia) ----------

@Composable
fun InfoRow(
    idIcono: Int,
    texto: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(texto, color = Color.LightGray, fontSize = 14.sp)
    }
}

// ---------- TARJETA GRANDE DE CARRERA DESTACADA ----------

@Composable
fun FeaturedRaceCard(
    idImagen: Int,
    titulo: String,
    fecha: String,
    ubicacion: String,
    distancia: String,
    precio: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(idImagen),
            contentDescription = titulo,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.85f)
                        ),
                        startY = 0f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(
                titulo,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            InfoRow(R.drawable.ic_calendario, fecha)
            Spacer(modifier = Modifier.height(4.dp))
            InfoRow(R.drawable.ubicacion_blanco, ubicacion)
            Spacer(modifier = Modifier.height(4.dp))
            InfoRow(R.drawable.corriendo, distancia)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                precio,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            AppButton(
                textoBoton = stringResource(R.string.ver_carrera),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// ---------- BOTÓN ----------

@Composable
fun AppButton(textoBoton: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.electric_lime)
        ),
        modifier = modifier
    ) {
        Text(textoBoton, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}

// ---------- TÍTULO DE SECCIÓN ----------

@Composable
fun SeccionTitulo(texto: String, modifier: Modifier = Modifier) {
    Text(
        texto,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(horizontal = 20.dp, vertical = 12.dp)
    )
}

// ---------- TARJETA PEQUEÑA DE CARRERA ----------

@Composable
fun RaceCard(
    idImagen: Int,
    titulo: String,
    precio: String,
    distancia: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(150.dp)
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
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                titulo,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(precio, color = Color.LightGray, fontSize = 10.sp)
                Text(
                    distancia,
                    color = Color.White,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.DarkGray)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
fun ProximasCarrerasRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RaceCard(R.drawable.running, "Carrera 10K Bogotá", "$95.000 COP", "10K", modifier = Modifier.weight(1f))
        RaceCard(R.drawable.running, "Carrera 5K Universitaria", "$65.000 COP", "5K", modifier = Modifier.weight(1f))
        RaceCard(R.drawable.running, "Media Maratón Bogotá", "$145.000 COP", "21K", modifier = Modifier.weight(1f))
    }
}

// ---------- CHIPS DE DISTANCIA ----------

@Composable
fun DistanciaChip(
    distancia: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(1.dp, colorResource(R.color.electric_lime), RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp)
    ) {
        Text(distancia, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.corriendo),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(R.color.electric_lime)),
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(distancia, color = colorResource(R.color.electric_lime), fontSize = 12.sp)
        }
    }
}

@Composable
fun DistanciasRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        DistanciaChip("5K", modifier = Modifier.weight(1f))
        DistanciaChip("10K", modifier = Modifier.weight(1f))
        DistanciaChip("21K", modifier = Modifier.weight(1f))
        DistanciaChip("42K", modifier = Modifier.weight(1f))
    }
}

// ---------- BARRA DE NAVEGACIÓN INFERIOR ----------

@Composable
fun NavItem(
    idIcono: Int,
    texto: String,
    seleccionado: Boolean,
    modifier: Modifier = Modifier
) {
    val color = if (seleccionado) colorResource(R.color.electric_lime) else Color.Gray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(idIcono),
            contentDescription = texto,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(texto, color = color, fontSize = 12.sp)
    }
}

@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.graphite))
            .padding(vertical = 12.dp, horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NavItem(R.drawable.ic_home, "Inicio", seleccionado = true)
        NavItem(R.drawable.ic_explorar, "Explorar", seleccionado = false)
        NavItem(R.drawable.ic_mis_carreras, "Mis carreras", seleccionado = false)
        NavItem(R.drawable.ic_perfil, "Perfil", seleccionado = false)
    }
}

// ---------- PANTALLA COMPLETA ----------

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HeaderHomeScreen()
            Spacer(modifier = Modifier.height(20.dp))
            FeaturedRaceCard(
                idImagen = R.drawable.running,
                titulo = "Media Maratón de Bogotá 2026",
                fecha = "27 de septiembre de 2026",
                ubicacion = "Bogotá, Colombia",
                distancia = "21K",
                precio = "$145.000 COP",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            SeccionTitulo("Próximas carreras")
            ProximasCarrerasRow()
            SeccionTitulo("Elige tu distancia")
            DistanciasRow()
            Spacer(modifier = Modifier.height(20.dp))
        }
        BottomNavBar()
    }
}

@Composable
@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
fun HomeScreenPreview() {
    HomeScreen()
}