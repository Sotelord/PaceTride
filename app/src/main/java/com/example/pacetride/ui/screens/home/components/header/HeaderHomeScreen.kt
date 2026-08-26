package com.example.pacetride.ui.screens.home.components.header

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.utils.CampanaNotificaciones
import com.example.pacetride.ui.utils.LogoApp

@Composable
fun HeaderHomeScreen(
    usuario: Usuario,
    notificacionButtonPressed: (Int)-> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            LogoApp(
                modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.Center)
            )
            CampanaNotificaciones(
                onClick = {
                    notificacionButtonPressed(usuario.id)
                    Log.d("HomeScreen", "Campana notificaciones clicked")
                          },
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        MensajeBienvenida(usuario.nombre)
        MensajeMotivacion()
    }
}

@Composable
@Preview
fun HeaderHomeScreenPreview(){
    val usuario = LocalUsuarioProvider.usuarios[0]
    HeaderHomeScreen(usuario, {})
}