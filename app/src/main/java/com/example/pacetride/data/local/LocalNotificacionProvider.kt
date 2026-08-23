    package com.example.pacetride.data.local

    import com.example.pacetride.data.Notificacion
    import java.time.LocalDateTime

    object LocalNotificacionProvider {

        private val usuarios = LocalUsuarioProvider.usuarios
        private val carreras = LocalCarreraProvider.listCarrera

        val notificaciones = listOf(
            Notificacion(
                usuario = usuarios[1],
                action = "comenzó a seguirte.",
                buttonText = "Ver perfil",
                fecha = LocalDateTime.now().minusHours(2)
            ),

            Notificacion(
                usuario = usuarios[0],
                action = "comentó tu reseña de la Media Maratón Bogotá 2026.",
                subtext = "¡Excelente tiempo! 🔥",
                fecha = LocalDateTime.now().minusHours(5)
            ),

            Notificacion(
                usuario = usuarios[2],
                action = "recibió 12 nuevos Me gusta.",
                hasHeart = true,
                fecha = LocalDateTime.now().minusHours(8)
            ),

            Notificacion(
                usuario = usuarios[3],
                action = "respondió a tu comentario.",
                subtext = "También me encantó la ruta.",
                fecha = LocalDateTime.now().minusDays(1)
            ),

            Notificacion(
                carrera = carreras[2],
                action = "se acerca.",
                subtext = "Faltan 47 días.",
                buttonText = "Ver carrera",
                fecha = LocalDateTime.now().minusDays(3)
            ),

            Notificacion(
                usuario = usuarios[0],
                action = "empezó a seguirte.",
                buttonText = "Ver perfil",
                fecha = LocalDateTime.now().minusDays(5)
            )
        )
    }