package com.example.pacetride.data.dtos

import com.example.pacetride.data.Resena
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class ResenaCarreraDto(val nombre: String? = null)
data class ResenaUsuarioDto(
    val nombre: String? = null,
    val fotoPerfil: String? = null
)

data class ResenaDto(
    val usuarioId: Int, //1
    val carreraId: Int, //2
    val id: Int, //2
    val resena: String, //Buena organización en general, pero la entrega de kits fue un poco lenta. El recorrido es plano y rápido, perfecto para buscar una mejor marca personal.
    val calificacion: Float, //4
    val categoriasDestacadas: List<String>?, //"categoriasDestacadas": ["Organización", "Seguridad"] o null
    val likes: Int, //1
    val createdAt: String, //2026-10-04T00:50:38.234Z
    val updatedAt: String, //2026-10-04T00:50:38.234Z
    val carrera: ResenaCarreraDto? = null, //{ "nombre": "Corre por Bogotá 5K" }
    val usuario: ResenaUsuarioDto? = null, //{ "nombre": "Santiago Rayo", "fotoPerfil": "https://media.istockphoto.com/id/545805760/photo/man-runner-jogger-running-isolated.jpg?s=612x612&w=0&k=20&c=h_yH1K2Ou_b6fjL8At0TY2wV5rhasGFNu4sdFVZW54A=" }
)

fun ResenaDto.toResena(): Resena {
    return Resena(
        id = id.toString(),
        usuarioId = usuarioId.toString(),
        usuario = usuario?.nombre,
        fotoUsuario = usuario?.fotoPerfil,
        carreraId = carreraId.toString(),
        carrera = carrera?.nombre,
        resena = resena,
        calificacion = calificacion.toString(),
        categoriasDestacadas = categoriasDestacadas,
        likes = likes,
        fechaPublicacion = formaterarFechaPublicacion(createdAt)
    )
}

private fun formaterarFechaPublicacion(fecha: String): String {
    val fechaPublicacion = Instant.parse(fecha)
    val ahora = Instant.now()

    val diferencia = Duration.between(fechaPublicacion, ahora)

    val minutos = diferencia.toMinutes()
    val horas = diferencia.toHours()
    val dias = diferencia.toDays()

    return when {
        minutos < 1 -> {
            "Hace un momento"
        }

        minutos < 60 -> {
            "Hace $minutos ${if (minutos == 1L) "minuto" else "minutos"}"
        }

        horas < 24 -> {
            "Hace $horas ${if (horas == 1L) "hora" else "horas"}"
        }

        dias <= 7 -> {
            "Hace $dias ${if (dias == 1L) "día" else "días"}"
        }

        else -> {
            val formater = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                .withZone(ZoneId.systemDefault())
            formater.format(fechaPublicacion)
        }
    }
}
