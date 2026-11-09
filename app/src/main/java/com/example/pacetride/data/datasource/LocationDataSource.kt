package com.example.pacetride.data.datasource

import com.example.pacetride.data.Ubicacion

interface LocationDataSource {
    suspend fun getCurrentLocation(): Ubicacion?
}