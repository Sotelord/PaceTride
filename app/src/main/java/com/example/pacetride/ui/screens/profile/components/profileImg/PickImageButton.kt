package com.example.pacetride.ui.screens.profile.components.profileImg

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pacetride.R
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun PickImageButton(
    action: (uri: Uri) -> Unit,
    modifier: Modifier = Modifier
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        uri: Uri? ->
        uri?.let {
            Log.d("ProfileScreen", uri.toString())
            action(uri)
        }
    }

    SmallFloatingActionButton(
        onClick = {
            launcher.launch("image/*")
                  },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_select_foto),
            contentDescription = "seleccionar foto",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background),
            modifier = Modifier.size(20.dp)
        )
    }
}


@Composable
@Preview
fun PickImageButtonPreview(){
    PacetrideTheme(darkTheme = true) {
        PickImageButton(
            action = {}
        )
    }
}