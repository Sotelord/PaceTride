package com.example.pacetride.ui.screens.publicprofile.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun CarreraCard(modifier: Modifier = Modifier, titulo: String, detalle: String) {
    Row(
        modifier = modifier
            .width(260.dp)
            .background(Color(0xFF2A2A2A), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_medalla),
            contentDescription = "Medalla",
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = titulo, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = detalle, color = Color.LightGray, fontSize = 12.sp)
        }
    }
}

@Composable
@Preview
fun CarreraCardPreview(modifier: Modifier = Modifier){
    CarreraCard(titulo = "Media maraton", detalle = "Increible")
}