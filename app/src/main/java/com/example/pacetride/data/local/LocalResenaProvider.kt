package com.example.pacetride.data.local

import com.example.pacetride.data.Resena

object LocalResenaProvider {
    val listaResenas = listOf(
        Resena(
            id = "1",
            usuarioId = "1",
            usuario = "Santiago Rayo",
            fotoUsuario = "https://media.istockphoto.com/id/545805760/photo/man-runner-jogger-running-isolated.jpg?s=612x612&w=0&k=20&c=h_yH1K2Ou_b6fjL8At0TY2wV5rhasGFNu4sdFVZW54A=",
            carreraId = "1",
            carrera = "Carrera Atlética Bogotá 10K",
            resena = "¡Una experiencia increíble! La ruta por el centro histórico fue espectacular y el apoyo de la gente en las calles te da mucha energía. Los puntos de hidratación estaban muy bien ubicados.",
            calificacion = "5",
            fechaPublicacion = "hace 1h",
            categoriasDestacadas = listOf("Organización", "Kit"),
            likes = 1,
        ),
        Resena(
            id = "2",
            usuarioId = "1",
            usuario = "Santiago Rayo",
            fotoUsuario = "https://media.istockphoto.com/id/545805760/photo/man-runner-jogger-running-isolated.jpg?s=612x612&w=0&k=20&c=h_yH1K2Ou_b6fjL8At0TY2wV5rhasGFNu4sdFVZW54A=",
            carreraId = "2",
            carrera = "Corre por Bogotá 5K",
            resena = "Buena organización en general, pero la entrega de kits fue un poco lenta. El recorrido es plano y rápido, perfecto para buscar una mejor marca personal.",
            calificacion = "4",
            fechaPublicacion = "hace 1h",
            categoriasDestacadas = listOf("Organización", "Kit"),
            likes = 2,
        ),
        Resena(
            id = "3",
            usuarioId = "2",
            usuario = "Sara Castro",
            fotoUsuario = "https://images.pexels.com/photos/3763996/pexels-photo-3763996.jpeg?cs=srgb&dl=pexels-olly-3763996.jpg&fm=jpg",
            carreraId = "3",
            carrera = "Media Maratón Bogotá 2026",
            resena = "El paisaje de la montaña es insuperable, pero faltó señalización en el kilómetro 7. Casi me pierdo junto con otros corredores. Espero que mejoren eso para el próximo año.",
            calificacion = "3.5",
            fechaPublicacion = "hace 1h",
            categoriasDestacadas = listOf("Organización", "Kit"),
            likes = 3,
        ),
        Resena(
            id = "4",
            usuarioId = "3",
            usuario = "David Sotelo",
            carreraId = "4",
            carrera = "Carrera 0 Bogotá",
            resena = "Mi primera carrera de 5K y me encantó el ambiente familiar. Muy inclusiva y con medallas muy bonitas para todos los participantes. ¡Altamente recomendada!",
            calificacion = "4.8",
            fechaPublicacion = "hace 1h",
            categoriasDestacadas = listOf("Organización", "Kit"),
            likes = 4,
        ),
        Resena(
            id = "5",
            usuarioId = "2",
            usuario = "Sara Castro",
            fotoUsuario = "https://images.pexels.com/photos/3763996/pexels-photo-3763996.jpeg?cs=srgb&dl=pexels-olly-3763996.jpg&fm=jpg",
            carreraId = "5",
            carrera = "Carrera 10k",
            resena = "Demasiada gente para una ruta tan estrecha en los primeros kilómetros. Fue difícil mantener el ritmo al principio, aunque la llegada en el estadio fue emocionante.",
            calificacion = "3",
            fechaPublicacion = "hace 1h",
            categoriasDestacadas = listOf("Organización", "Kit"),
            likes = 5,
        )
    )
}