package com.example.pacetride.data.local

import com.example.pacetride.data.Resena

object LocalResenaProvider {
    val listaResenas = listOf(
        Resena(
            id = "1",
            usuarioId = "1",
            carreraId = "1",
            resena = "¡Una experiencia increíble! La ruta por el centro histórico fue espectacular y el apoyo de la gente en las calles te da mucha energía. Los puntos de hidratación estaban muy bien ubicados.",
            calificacion = "5"
        ),
        Resena(
            id = "2",
            usuarioId = "1",
            carreraId = "2",
            resena = "Buena organización en general, pero la entrega de kits fue un poco lenta. El recorrido es plano y rápido, perfecto para buscar una mejor marca personal.",
            calificacion = "4"
        ),
        Resena(
            id = "3",
            usuarioId = "2",
            carreraId = "3",
            resena = "El paisaje de la montaña es insuperable, pero faltó señalización en el kilómetro 7. Casi me pierdo junto con otros corredores. Espero que mejoren eso para el próximo año.",
            calificacion = "3.5"
        ),
        Resena(
            id = "4",
            usuarioId = "3",
            carreraId = "4",
            resena = "Mi primera carrera de 5K y me encantó el ambiente familiar. Muy inclusiva y con medallas muy bonitas para todos los participantes. ¡Altamente recomendada!",
            calificacion = "4.8"
        ),
        Resena(
            id = "5",
            usuarioId = "2",
            carreraId = "5",
            resena = "Demasiada gente para una ruta tan estrecha en los primeros kilómetros. Fue difícil mantener el ritmo al principio, aunque la llegada en el estadio fue emocionante.",
            calificacion = "3"
        )
    )
}