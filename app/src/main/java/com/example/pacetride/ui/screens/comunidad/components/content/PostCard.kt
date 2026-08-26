package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.data.Resena
import com.example.pacetride.data.EstadisticasCarrera
import com.example.pacetride.data.local.LocalPublicacionProvider

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    idAvatar: Int?,
    nombre: String,
    tiempo: String,
    texto: String,
    estadisticasCarrera: EstadisticasCarrera? = null,
    resena: Resena? = null,
    likes: String,
    comentarios: String
) {
    var meGustaActivo by remember { mutableStateOf(false) }
    // Convertimos el string inicial a Int una sola vez
    var cantidadLikes by remember { mutableIntStateOf(likes.toIntOrNull() ?: 0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp)
    ) {
        HeaderPost(idAvatar, nombre, tiempo)
        Spacer(modifier = Modifier.height(12.dp))
        Text(texto, color = MaterialTheme.colorScheme.onBackground, fontSize = 14.sp, lineHeight = 20.sp)
        Spacer(modifier = Modifier.height(14.dp))

        if (estadisticasCarrera != null) {
            EstadisticasPostGrid(estadisticasCarrera.distancia, estadisticasCarrera.tiempoCarrera, estadisticasCarrera.ritmo)
            Spacer(modifier = Modifier.height(14.dp))
        }

        if (resena != null) {
            ResenaPost(resena)
            Spacer(modifier = Modifier.height(14.dp))
        }

        ContadoresRow(cantidadLikes.toString(), comentarios)
        Spacer(modifier = Modifier.height(12.dp))
        AccionesPostRow(
            meGustaSeleccionado = meGustaActivo,
            onMeGustaClick = {
                meGustaActivo = !meGustaActivo
                cantidadLikes += if (meGustaActivo) 1 else -1
            },
            onComentarClick = {/*Comentario*/}
        )
    }
}

//Publicacion con estadisticas de la persona en el evento
@Composable
@Preview
fun PostCardPreview(){
    val publicacion = LocalPublicacionProvider.publicaciones[0] // Santiago Rayo
    PostCard(
        idAvatar = publicacion.idAvatar,
        nombre = publicacion.nombre,
        tiempo = publicacion.tiempo,
        texto = publicacion.textoAMostrar,
        estadisticasCarrera = publicacion.estadisiticas,
        likes = publicacion.likes,
        comentarios = publicacion.comentarios
    )
}

//Publicacion de reseña
@Composable
@Preview
fun PostCardPreview2(){
    val publicacion = LocalPublicacionProvider.publicaciones[1] // Laura Gómez
    PostCard(
        idAvatar = publicacion.idAvatar,
        nombre = publicacion.nombre,
        tiempo = publicacion.tiempo,
        texto = publicacion.textoAMostrar,
        resena = publicacion.resena,
        likes = publicacion.likes,
        comentarios = publicacion.comentarios
    )
}

//Publicacion estilo comentario
@Composable
@Preview
fun PostCardPreview3(){
    val publicacion = LocalPublicacionProvider.publicaciones[2] // Luis Martínez
    PostCard(
        idAvatar = publicacion.idAvatar,
        nombre = publicacion.nombre,
        tiempo = publicacion.tiempo,
        texto = publicacion.textoAMostrar,
        likes = publicacion.likes,
        comentarios = publicacion.comentarios
    )
}