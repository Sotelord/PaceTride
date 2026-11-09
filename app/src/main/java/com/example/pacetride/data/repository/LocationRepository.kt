package com.example.pacetride.data.repository

import com.example.pacetride.data.Ubicacion
import com.example.pacetride.data.datasource.LocationDataSource
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationDataSource: LocationDataSource
) {
    suspend fun getCurrentLocation(): Ubicacion? {
        val ubicacion = locationDataSource.getCurrentLocation() ?: return null
        return Ubicacion(
            latitud = ubicacion.latitud,
            longitud = ubicacion.longitud
        )
    }
}