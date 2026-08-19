package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.data.local.LocalPublicacionProvider

@Composable
fun PostCard(
    idAvatar: Int,
    nombre: String,
    tiempo: String,
    texto: String,
    distancia: String? = null,
    tiempoCarrera: String? = null,
    ritmo: String? = null,
    calificacion: String? = null,
    likes: String,
    comentarios: String,
    modifier: Modifier = Modifier
) {
    var meGustaActivo by remember { mutableStateOf(false) }
    // Convertimos el string inicial a Int una sola vez
    var cantidadLikes by remember { mutableStateOf(likes.toIntOrNull() ?: 0) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(colorResource(R.color.graphite))
            .padding(16.dp)
    ) {
        HeaderPost(idAvatar, nombre, tiempo)
        Spacer(modifier = Modifier.height(12.dp))
        Text(texto, color = Color.White, fontSize = 14.sp, lineHeight = 20.sp)
        Spacer(modifier = Modifier.height(14.dp))

        if (distancia != null && tiempoCarrera != null && ritmo != null) {
            EstadisticasPostGrid(distancia, tiempoCarrera, ritmo)
            Spacer(modifier = Modifier.height(14.dp))
        }

        if (calificacion != null) {
            CalificacionPost(calificacion)
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
fun PostCardPreview(modifier: Modifier = Modifier){
    val publicacion = LocalPublicacionProvider.publicaciones[0] // Santiago Rayo
    PostCard(
        idAvatar = publicacion.idAvatar,
        nombre = publicacion.nombre,
        tiempo = publicacion.tiempo,
        texto = publicacion.texto,
        distancia = publicacion.distancia,
        tiempoCarrera = publicacion.tiempoCarrera,
        ritmo = publicacion.ritmo,
        likes = publicacion.likes,
        comentarios = publicacion.comentarios
    )
}

//Publicacion de reseña
@Composable
@Preview
fun PostCardPreview2(modifier: Modifier = Modifier){
    val publicacion = LocalPublicacionProvider.publicaciones[1] // Laura Gómez
    PostCard(
        idAvatar = publicacion.idAvatar,
        nombre = publicacion.nombre,
        tiempo = publicacion.tiempo,
        texto = publicacion.texto,
        calificacion = publicacion.calificacion,
        likes = publicacion.likes,
        comentarios = publicacion.comentarios
    )
}

//Publicacion estilo comentario
@Composable
@Preview
fun PostCardPreview3(modifier: Modifier = Modifier){
    val publicacion = LocalPublicacionProvider.publicaciones[2] // Luis Martínez
    PostCard(
        idAvatar = publicacion.idAvatar,
        nombre = publicacion.nombre,
        tiempo = publicacion.tiempo,
        texto = publicacion.texto,
        likes = publicacion.likes,
        comentarios = publicacion.comentarios
    )
}