package com.example.pacetride.ui.screens.explorer.components.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.ui.utils.LogoApp

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

@Composable
@Preview
fun HeaderExplorarPreview(modifier: Modifier = Modifier){
    HeaderExplorar()
}