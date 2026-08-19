package com.example.pacetride.ui.screens.publicprofile.components.header

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import androidx.compose.ui.tooling.preview.Preview

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
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(28.dp)
                .offset(x = (-24).dp)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}



@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HeaderPublicoPreview() {
    HeaderPublico()
}