package com.example.pacetride.ui.screens.explorer.components.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.pacetride.data.Carrera
import com.example.pacetride.data.local.LocalCarreraProvider
import com.example.pacetride.ui.utils.AppButton

@Composable
fun ExplorarRaceCard(
    carrera: Carrera,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Image(
            painter = painterResource(id = carrera.idImagen ?: R.drawable.running),
            contentDescription = carrera.nombre,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                carrera.nombre,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "${carrera.ubicacion} • ${carrera.fecha}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            if (carrera.ultimosCupos == true) {
                Text(
                    stringResource(R.string.ultimos_cupos),
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            } else if (carrera.precioMostrable != null) {
                Text(
                    "${carrera.distanciaMostrable} • ${carrera.precioMostrable}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                AppButton(
                    textoBoton = stringResource(R.string.ver_detalles),
                    onClick = { Log.d("ExploreScreen", "Ver detalles race card clicked") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun ExplorarRaceCardPreview(modifier: Modifier = Modifier) {
    ExplorarRaceCard(
        LocalCarreraProvider.listCarrera[0],
        modifier = Modifier.width(180.dp)
    )
}

@Composable
@Preview
fun ExplorarRaceCardPreview2(modifier: Modifier = Modifier) {
    ExplorarRaceCard(
        LocalCarreraProvider.listCarrera[1],
        modifier = Modifier.width(180.dp)
    )
}

@Composable
@Preview
fun ExplorarRaceCardPreview3(modifier: Modifier = Modifier) {
    ExplorarRaceCard(
        LocalCarreraProvider.listCarrera[2],
        modifier = Modifier.width(180.dp)
    )
}

@Composable
@Preview
fun ExplorarRaceCardPreview4(modifier: Modifier = Modifier) {
    ExplorarRaceCard(
        LocalCarreraProvider.listCarrera[3],
        modifier = Modifier.width(180.dp)
    )
}