    package com.example.pacetride.data.local

    import com.example.pacetride.data.Notificacion
    import java.time.LocalDateTime

    object LocalNotificacionProvider {

        private val usuarios = LocalUsuarioProvider.usuarios
        private val carreras = LocalCarreraProvider.listCarrera

        val notificaciones = listOf(
            Notificacion(
                id = 1,
                paraUsuarioId = 1,
                usuario = usuarios[1],
                action = "comenzó a seguirte.",
                buttonText = "Ver perfil",
                fecha = LocalDateTime.now().minusHours(2)
            ),

            Notificacion(
                id = 2,
                paraUsuarioId = 2,
                usuario = usuarios[0],
                action = "comentó tu reseña de la Media Maratón Bogotá 2026.",
                subtext = "¡Excelente tiempo! 🔥",
                fecha = LocalDateTime.now().minusHours(5)
            ),

            Notificacion(
                id = 3,
                paraUsuarioId = 3,
                usuario = usuarios[2],
                action = "recibió 12 nuevos Me gusta.",
                hasHeart = true,
                fecha = LocalDateTime.now().minusHours(8)
            ),

            Notificacion(
                id = 4,
                paraUsuarioId = 1,
                usuario = usuarios[3],
                action = "respondió a tu comentario.",
                subtext = "También me encantó la ruta.",
                fecha = LocalDateTime.now().minusDays(1)
            ),

            Notificacion(
                id = 5,
                paraUsuarioId = 3,
                carrera = carreras[2],
                action = "se acerca.",
                subtext = "Faltan 47 días.",
                buttonText = "Ver carrera",
                fecha = LocalDateTime.now().minusDays(3)
            ),

            Notificacion(
                id = 6,
                paraUsuarioId = 3,
                usuario = usuarios[1],
                action = "empezó a seguirte.",
                buttonText = "Ver perfil",
                fecha = LocalDateTime.now().minusDays(5)
            ),
            Notificacion(
                id = 7,
                paraUsuarioId = 1,
                usuario = usuarios[2],
                action = "comenzó a seguirte.",
                buttonText = "Ver perfil",
                fecha = LocalDateTime.now().minusWeeks(2)
            ),

            Notificacion(
                id = 8,
                paraUsuarioId = 2,
                usuario = usuarios[0],
                action = "comentó tu reseña de la Media Maratón Bogotá 2026.",
                subtext = "¡Excelente tiempo! 🔥",
                fecha = LocalDateTime.now().minusWeeks(1)
            ),

            Notificacion(
                id = 9,
                paraUsuarioId = 3,
                usuario = usuarios[2],
                action = "recibió 12 nuevos Me gusta.",
                hasHeart = true,
                fecha = LocalDateTime.now().minusWeeks(3)
            ),

            Notificacion(
                id = 10,
                paraUsuarioId = 1,
                usuario = usuarios[3],
                action = "respondió a tu comentario.",
                subtext = "También me encantó la ruta.",
                fecha = LocalDateTime.now().minusMinutes(1)
            ),

            Notificacion(
                id = 11,
                paraUsuarioId = 2,
                carrera = carreras[2],
                action = "se acerca.",
                subtext = "Faltan 47 días.",
                buttonText = "Ver carrera",
                fecha = LocalDateTime.now().minusWeeks(2)
            ),

            Notificacion(
                id = 12,
                paraUsuarioId = 3,
                usuario = usuarios[0],
                action = "empezó a seguirte.",
                buttonText = "Ver perfil",
                fecha = LocalDateTime.now().minusDays(3)
            )
        )

        fun notificacionesDe(usuarioId: Int): List<Notificacion> =
            notificaciones.filter { it.paraUsuarioId == usuarioId }
    }