package com.example.pacetride.data.datasource.impl

import android.annotation.SuppressLint
import android.util.Log
import com.example.pacetride.data.Ubicacion
import com.example.pacetride.data.datasource.LocationDataSource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class FusedLocationDataSourceImpl @Inject constructor(
    private val client: FusedLocationProviderClient
) : LocationDataSource {

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Ubicacion? {
        return try {
            val token = CancellationTokenSource().token
            val location = client
                .getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, token)
                .await()
            location?.let { Ubicacion(it.latitude, it.longitude) }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e("LOCATION", "No se pudo obtener la ubicación", e)
            null
        }
    }
}