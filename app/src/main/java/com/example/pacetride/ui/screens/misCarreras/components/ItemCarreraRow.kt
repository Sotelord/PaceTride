package com.example.pacetride.ui.screens.misCarreras.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun ItemCarreraCard(
    titulo: String,
    fecha: String,
    ubicacion: String,
    distancia: String,
    diasRestantes: String? = null,
    precio: String? = null,
    textoBoton: String,
    onClick: () -> Unit,
    botonAnchoCompleto: Boolean = true
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = titulo, color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            // Detalles con Iconos del Drawable
            IconTextRow(iconRes = R.drawable.ic_calendario, text = fecha)
            IconTextRow(iconRes = R.drawable.ubicacion, text = ubicacion)
            IconTextRow(iconRes = R.drawable.ic_distancia, text = distancia)

            Spacer(modifier = Modifier.height(4.dp))

            // Estado Inscrito
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "INSCRITO", color = MaterialTheme.colorScheme.primaryContainer, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Variante 1: Barra de progreso (con ic_reloj)
            if (diasRestantes != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_reloj),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Faltan $diasRestantes", color = MaterialTheme.colorScheme.primaryContainer, fontSize = 14.sp)
                    }
                    Text(text = "Preparación", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 0.7f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Fila inferior: Precio (Variante 2) y Botón
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (precio != null) {
                    Text(text = precio, color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold)
                } else if (!botonAnchoCompleto) {
                    Spacer(modifier = Modifier.weight(1f))
                }

                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                    modifier = if (botonAnchoCompleto) Modifier.fillMaxWidth() else Modifier
                ) {
                    Text(text = textoBoton, color = MaterialTheme.colorScheme.background, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// Función auxiliar para mantener el código limpio en las filas de ícono + texto
@Composable
fun IconTextRow(iconRes: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp)
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
    }
}

@Preview
@Composable
fun ItemCarreraCardPreview() {
    ItemCarreraCard(
        titulo = "Media Maratón Bogotá 2026",
        fecha = "27 de septiembre de 2026",
        ubicacion = "Bogotá, Colombia",
        distancia = "21K",
        diasRestantes = "39 días",
        textoBoton = "Ver carrera",
        onClick = {}
    )
}