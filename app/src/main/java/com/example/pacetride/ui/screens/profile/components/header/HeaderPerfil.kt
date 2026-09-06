package com.example.pacetride.ui.screens.profile.components.header

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R

@Composable
fun HeaderPerfil(
    configurationPressed: () -> Unit,
    logOutPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            stringResource(R.string.mi_perfil),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center)
        )

        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Configuracion(onClick = {
                configurationPressed()
                Log.d("ProfileScreen", "Configuracion clicked")
            })
            Spacer(modifier = Modifier.width(5.dp))
            LogOut(onClick = {
                logOutPressed()
                Log.d("ProfileScreen", "LogOut cliked")
            })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HeaderPerfilPreview(){
    HeaderPerfil({}, {})
}