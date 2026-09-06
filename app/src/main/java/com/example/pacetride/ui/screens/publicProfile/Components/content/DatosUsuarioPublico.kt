package com.example.pacetride.ui.screens.publicProfile.Components.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.profile.components.content.DatosUsuario
import com.example.pacetride.ui.utils.AppButton

@Composable
fun DatosUsuarioPublico(
    usuario: Usuario,
    configPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    var siguiendo by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        DatosUsuario(
            usuario.nombre,
            usuario.usuario,
            usuario.email,
            usuario.ubicacion,
            usuario.bio
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (siguiendo) {
                OutlinedButton(
                    onClick = {
                        siguiendo = false
                        Log.d("PubliProfileScreen", "Dejar de seguir clicked")
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primaryContainer),
                    modifier = Modifier.width(140.dp).height(48.dp)
                ) {
                    Text("Siguiendo", fontWeight = FontWeight.Bold)
                }
            } else {
                AppButton(
                    textoBoton = "Seguir",
                    onClick = {
                        siguiendo = true
                        Log.d("PubliProfileScreen", "Seguir clicked")
                    },
                    modifier = Modifier.width(140.dp).height(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(
                onClick = {
                    configPressed()
                    Log.d("PubliProfileScreen", "Configuracion clicked")
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer, CircleShape)
                    .size(48.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_configuracion),
                    contentDescription = "Opciones",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatosUsuarioPublicoPreview() {
    val usuario = LocalUsuarioProvider.usuarios[1]
    DatosUsuarioPublico(
        usuario,
        {},
        modifier = Modifier.padding(16.dp)
    )
}