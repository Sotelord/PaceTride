package com.example.pacetride.ui.screens.conexiones.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.data.Usuario
import com.example.pacetride.data.local.LocalUsuarioProvider
import com.example.pacetride.ui.screens.conexiones.TipoConexion
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun ConexionesScreenContent(
    usuarios: List<Usuario>,
    tipo: TipoConexion,
    atrasPressed: () -> Unit,
    onClickUsuario: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            IconButton(onClick = atrasPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text (
                text = if (tipo == TipoConexion.SEGUIDORES) "Seguidores" else "Siguiendo",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        if(usuarios.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = if (tipo == TipoConexion.SEGUIDORES)
                        "Aún no tiene seguidores."
                    else
                        "Aún no sigue a nadie.",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(usuarios, key = { it.id }) { usuario ->
                    UsuarioCard(usuario = usuario, onClick = onClickUsuario)
                }
            }
        }
    }
}

@Composable
@Preview
fun ConexionesScreenContentPreview() {
    PacetrideTheme(darkTheme = true) {
        ConexionesScreenContent(
            usuarios = emptyList(),
            tipo = TipoConexion.SEGUIDORES,
            atrasPressed = {},
            onClickUsuario = {}
        )
    }
}

@Composable
@Preview
fun ConexionesScreenContentPreview2() {
    val usuarios = LocalUsuarioProvider.usuarios
    PacetrideTheme(darkTheme = true) {
        ConexionesScreenContent(
            usuarios = usuarios,
            tipo = TipoConexion.SEGUIDORES,
            atrasPressed = {},
            onClickUsuario = {}
        )
    }
}

@Composable
@Preview
fun ConexionesScreenContentPreview3() {
    PacetrideTheme(darkTheme = true) {
        ConexionesScreenContent(
            usuarios = emptyList(),
            tipo = TipoConexion.SEGUIDOS,
            atrasPressed = {},
            onClickUsuario = {}
        )
    }
}

@Composable
@Preview
fun ConexionesScreenContentPreview4() {
    val usuarios = LocalUsuarioProvider.usuarios
    PacetrideTheme(darkTheme = true) {
        ConexionesScreenContent(
            usuarios = usuarios,
            tipo = TipoConexion.SEGUIDOS,
            atrasPressed = {},
            onClickUsuario = {}
        )
    }
}