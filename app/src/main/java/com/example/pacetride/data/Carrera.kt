package com.example.pacetride.data

import androidx.annotation.DrawableRes
import kotlin.math.roundToInt

data class Carrera(
    @DrawableRes val idImagen: Int? = null,
    val id: Int,
    val nombre: String,
    val fecha: String,
    val ubicacion: String,
    val distanciasDisponiblesKm: List<Int> = listOf(5, 10, 21),
    val precioBase: Int,
    val distanciaReferenciaKm: Int = 21,
    val ultimosCupos: Boolean? = false,
    val descripcion: String
) {

    //Calcula el precio de inscripción de forma proporcional a la distancia, usando precioBase como referencia para distanciaReferenciaKm.
    fun calcularPrecio(km: Int): Int {
        val precioExacto = precioBase.toDouble() * km / distanciaReferenciaKm
        return ((precioExacto / 100.0).roundToInt() * 100)
    }

    // La distancia "principal" a mostrar cuando no se ha elegido ninguna explícitamente.
    val distanciaPrincipalKm: Int
        get() = distanciasDisponiblesKm.firstOrNull() ?: distanciaReferenciaKm

    //Precio ya calculado y formateado para la distancia principal, ej. "$90.000 COP"
    val precioMostrable: String
        get() = calcularPrecio(distanciaPrincipalKm).aPrecioCop()

    //Texto de distancia listo para UI, ej. "10K"
    val distanciaMostrable: String
        get() = "${distanciaPrincipalKm}K"
}

// ---------- HELPER DE FORMATO ----------

fun Int.aPrecioCop(): String {
    val formateado = "%,d".format(this).replace(",", ".")
    return "$$formateado COP"
}