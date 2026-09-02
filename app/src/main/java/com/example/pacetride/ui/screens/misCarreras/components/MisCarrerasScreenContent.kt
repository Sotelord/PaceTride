package com.example.pacetride.ui.screens.misCarreras.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.CarreraRealizada
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.data.local.LocalCarreraRealizadaProvider
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun MisCarrerasScreenContent(
    proximas: List<Carrera>,
    completadas: List<CarreraRealizada>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    notificacionButtonPressed: () -> Unit,
    verCarreraButtonPressed: (Int) -> Unit,
    explorarPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderMisCarreras(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = onTabSelected,
            notificacionButtonPressed = notificacionButtonPressed
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (selectedTabIndex == 0) {
            proximas.forEach { carrera ->
                ItemCarreraCard(
                    titulo = carrera.nombre,
                    fecha = carrera.fecha,
                    ubicacion = carrera.ubicacion,
                    distancia = "${carrera.distanciaPrincipalKm}K",
                    textoBoton = "Ver carrera",
                    botonAnchoCompleto = true,
                    onClick = { verCarreraButtonPressed(carrera.id) }
                )
            }
        } else {
            completadas.forEach { carreraRealizada ->
                ItemCarreraCard(
                    titulo = carreraRealizada.carrera.nombre,
                    fecha = carreraRealizada.carrera.fecha,
                    ubicacion = carreraRealizada.carrera.ubicacion,
                    distancia = "${carreraRealizada.carrera.distanciaPrincipalKm}K",
                    textoBoton = "Ver detalles",
                    botonAnchoCompleto = false,
                    onClick = { verCarreraButtonPressed(carreraRealizada.carrera.id) }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿Buscas otra carrera?",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Encuentra tu próximo reto y únete a la comunidad.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start).padding(bottom = 16.dp, top = 4.dp)
            )

            OutlinedButton(
                onClick = explorarPressed,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.primaryContainer),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text("Explorar carreras", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun MisCarrerasScreenContentPreview(){
    val carreraRealizadas = LocalCarreraRealizadaProvider.historial
    val carrerasProx = LocalCarreraProvider.listCarrera
    PacetrideTheme(darkTheme = true) {
        MisCarrerasScreenContent(
            completadas = carreraRealizadas,
            proximas = carrerasProx,
            onTabSelected = {},
            selectedTabIndex = 1,
            notificacionButtonPressed = {},
            verCarreraButtonPressed = {},
            explorarPressed = {}
        )
    }
}