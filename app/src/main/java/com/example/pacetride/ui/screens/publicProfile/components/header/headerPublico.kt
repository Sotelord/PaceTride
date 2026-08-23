package com.example.pacetride.ui.screens.publicprofile.components.header

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.ui.utils.LogoApp

@Composable
fun HeaderPublico(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { Log.d("PubliProfileScreen", "Atrás clicked") }) {
            Image(
                painter = painterResource(id = R.drawable.ic_atras),
                contentDescription = "Atrás",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        LogoApp(modifier = Modifier
            .height(28.dp)
            .offset(x = (-24).dp))

        Spacer(modifier = Modifier.weight(1f))
    }
}



@Preview(showBackground = true)
@Composable
fun HeaderPublicoPreview() {
    HeaderPublico()
}