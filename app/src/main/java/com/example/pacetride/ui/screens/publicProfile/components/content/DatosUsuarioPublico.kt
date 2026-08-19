package com.example.pacetride.ui.screens.publicprofile.components.content


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.screens.profile.components.content.DatosUsuario
import com.example.pacetride.ui.utils.AppButton
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DatosUsuarioPublico(
    nombre: String,
    usuario: String,
    ubicacion: String,
    bio: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        // Reutilizamos tu componente DatosUsuario
        DatosUsuario(
            nombre = nombre,
            usuario = usuario,
            ubicacion = ubicacion,
            bio = bio
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Botones de acción del perfil público
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            AppButton(
                textoBoton = "Seguir",
                onClick = { Log.d("PubliProfileScreen", "Seguir clicked") },
                modifier = Modifier.width(140.dp).height(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(
                onClick = { Log.d("PubliProfileScreen", "Configuracion clicked") },
                modifier = Modifier
                    .background(Color(0xFF1E1E1E), CircleShape)
                    .size(48.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_configuracion),
                    contentDescription = "Opciones",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun DatosUsuarioPublicoPreview() {
    DatosUsuarioPublico(
        nombre = "Laura Gómez",
        usuario = "@lauragomez",
        ubicacion = "Bogotá, Colombia",
        bio = "Runner apasionada por las carreras de ruta 🏃‍♀️. Siempre buscando nuevos retos.",
        modifier = Modifier.padding(16.dp)
    )
}