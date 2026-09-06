package com.example.pacetride.data.local

import com.example.pacetride.R
import com.example.pacetride.data.Carrera

object LocalCarreraProvider {
    val listCarrera = listOf(
        Carrera(
            id = 1,
            idImagen = R.drawable.running,
            nombre = "Carrera Atlética Bogotá 10K",
            ubicacion = "Bogotá",
            fecha = "15 de agosto",
            distanciasDisponiblesKm = listOf(10),
            precioBase = 90000,
            distanciaReferenciaKm = 10,
            descripcion = "Un recorrido rápido y plano por las principales vías del norte de la ciudad. Ideal para corredores que buscan superar su mejor marca personal en la distancia reina de los 10 kilómetros."
        ),
        Carrera(
            id = 2,
            idImagen = R.drawable.running,
            nombre = "Corre por Bogotá 5K",
            ubicacion = "Bogotá",
            fecha = "30 de agosto",
            distanciasDisponiblesKm = listOf(5),
            precioBase = 65000,
            distanciaReferenciaKm = 5,
            descripcion = "El evento perfecto para iniciarse en el mundo del running o disfrutar con amigos y familia. Una ruta recreativa llena de puntos de entretenimiento, música en vivo y mucha energía positiva."
        ),
        Carrera(
            id = 3,
            idImagen = R.drawable.running,
            nombre = "Media Maratón Bogotá 2026",
            ubicacion = "Bogotá",
            fecha = "27 de septiembre",
            distanciasDisponiblesKm = listOf(5, 10, 21, 42),
            precioBase = 145000,
            distanciaReferenciaKm = 21,
            descripcion = "Vive una de las experiencias de running ás importantes de Bogotá. Corre, supera tus límites y comparte el recorrido con miles de runners"
        ),
        Carrera(
            id = 4,
            idImagen = R.drawable.running,
            nombre = "Carrera 0 Bogotá",
            ubicacion = "Bogotá",
            fecha = "27 de agosto",
            distanciasDisponiblesKm = listOf(42),
            precioBase = 100000,
            distanciaReferenciaKm = 42,
            ultimosCupos = true,
            descripcion = "Una maratón desafiante diseñada exclusivamente para los atletas más experimentados de la región. El circuito atraviesa zonas de alta exigencia topográfica poniendo a prueba tu resistencia física y mental."
        ),
        Carrera(
            id = 5,
            idImagen = R.drawable.running,
            nombre = "Carrera 10k",
            ubicacion = "Bogotá",
            fecha = "31 de octubre",
            distanciasDisponiblesKm = listOf(10),
            precioBase = 95000,
            distanciaReferenciaKm = 10,
            descripcion = "Disfruta de una edición nocturna muy especial. Una ruta iluminada donde todos los participantes visten prendas reflectivas para crear un río de luces a lo largo de los parques principales de la capital."
        ),
        Carrera(
            id = 6,
            idImagen = R.drawable.running,
            nombre = "Carrera Universitaria 5k",
            ubicacion = "Pontificia Universidad Javeriana",
            fecha = "22 de agosto",
            distanciasDisponiblesKm = listOf(10),
            precioBase = 95000,
            distanciaReferenciaKm = 10,
            descripcion = "Un evento enfocado en la comunidad estudiantil y académica. El trazado recorre las instalaciones del campus universitario promoviendo la integración, los hábitos de vida saludable y el espíritu deportivo."
        )
    )
}