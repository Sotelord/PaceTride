package com.example.pacetride.ui.screens.raceDetail.components.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.utils.LogoApp

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
@Preview
fun BadgeMarcaPreview(modifier: Modifier = Modifier){
    BadgeMarca()
}