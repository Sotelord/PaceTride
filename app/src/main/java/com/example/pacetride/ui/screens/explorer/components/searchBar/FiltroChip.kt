package com.example.pacetride.ui.screens.explorer.components.searchBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- CHIPS DE FILTRO (Todas / 5K / 10K / ...) ----------

@Composable
fun FiltroChip(
    texto: String,
    seleccionado: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorFondo = if (seleccionado) colorResource(R.color.electric_lime) else colorResource(R.color.graphite)
    val colorTexto = if (seleccionado) Color.Black else Color.White

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(colorFondo)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
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
@Preview
fun FiltroChipPreview(modifier: Modifier = Modifier){
    FiltroChip("Todas",
        true,
        onClick = {}
    )
}

@Composable
@Preview(showBackground = true)
fun FiltroChipPreview2(modifier: Modifier = Modifier){
    FiltroChip("5k",
        false,
        onClick = {}
    )
}

@Composable
@Preview(showBackground = true)
fun FiltroChipPreview3(modifier: Modifier = Modifier){
    FiltroChip("10k",
        false,
        onClick = {}
    )
}

@Composable
@Preview(showBackground = true)
fun FiltroChipPreview4(modifier: Modifier = Modifier){
    FiltroChip("21k",
        false,
        onClick = {}
    )
}

@Composable
@Preview(showBackground = true)
fun FiltroChipPreview5(modifier: Modifier = Modifier){
    FiltroChip("42k",
        false,
        onClick = {}
    )
}