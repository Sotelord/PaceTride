package com.example.pacetride.ui.screens.home.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

// ---------- CHIPS DE DISTANCIA ----------

@Composable
fun DistanciaChip(
    distancia: String,
    onClick: ()-> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(1.dp, colorResource(R.color.electric_lime), RoundedCornerShape(16.dp))
            .padding(vertical = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
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
@Preview
fun DistanciaChipPreview(modifier: Modifier = Modifier){
    DistanciaChip("5K")
}

@Composable
@Preview
fun DistanciaChipPreview2(modifier: Modifier = Modifier){
    DistanciaChip("10K")
}

@Composable
@Preview
fun DistanciaChipPreview3(modifier: Modifier = Modifier){
    DistanciaChip("21K")
}

@Composable
@Preview
fun DistanciaChipPreview4(modifier: Modifier = Modifier){
    DistanciaChip("42K")
}