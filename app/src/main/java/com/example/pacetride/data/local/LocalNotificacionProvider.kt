    package com.example.pacetride.data.local

    import com.example.pacetride.data.Notificacion

    object LocalNotificacionProvider {
        val notificacionHoy = listOf(
            Notificacion(
                userName = "Laura Gómez",
                action = "comenzó a seguirte.",
                buttonText = "Ver perfil"
            ),
            Notificacion(
                userName = "Carlos Ramírez",
                action = "comentó tu reseña de la Media Maratón Bogotá 2026.",
                subtext = "¡Excelente tiempo! 🔥"
            ),
            Notificacion(
                userName = "Tu reseña",
                action = "recibió 12 nuevos Me gusta.",
                hasHeart = true
            ),
            Notificacion(
                userName = "María López",
                action = "respondió a tu comentario.",
                subtext = "También me encantó la ruta."
            )
        )

        val notificacionEstaSemana = listOf(
            Notificacion(
                userName = "Media Maratón Bogotá 2026",
                action = "se acerca.",
                subtext = "Faltan 47 días.",
                buttonText = "Ver carrera"
            ),
            Notificacion(
                userName = "Diego Rodríguez",
                action = "empezó a seguirte.",
                buttonText = "Ver perfil"
            )
        )
    }