package com.example.pacetride.ui.utils

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

@Composable
fun rememberVerificadorUbicacion(
    prioridad: Int = Priority.PRIORITY_BALANCED_POWER_ACCURACY,
): (onResultado: (Boolean) -> Unit) -> Unit {
    val context = LocalContext.current
    var pendiente by remember { mutableStateOf<((Boolean) -> Unit)?>(null) }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        pendiente?.invoke(result.resultCode == Activity.RESULT_OK)
        pendiente = null
    }

    return remember(context, prioridad) {
        { onResultado ->
            val request = LocationSettingsRequest.Builder()
                .addLocationRequest(LocationRequest.Builder(prioridad, 5_000L).build()).build()
            LocationServices.getSettingsClient(context).checkLocationSettings(request)
                .addOnSuccessListener { onResultado(true) }.addOnFailureListener { e ->
                    if (e is ResolvableApiException) {
                        pendiente = onResultado
                        launcher.launch(IntentSenderRequest.Builder(e.resolution).build())
                    } else {
                        onResultado(false)
                    }
                }
        }
    }
}