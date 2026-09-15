package com.example.pacetride.ui.screens.profile.components.profileImg

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.ProfileAsyncImage

@Composable
fun ProfileImgAndEdit(
    action: (uri: Uri) -> Unit,
    imageURL: String?,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
    ){
        ProfileAsyncImage(imageURL = imageURL, size = 100, imgSize = 48)
        PickImageButton(
            action = action,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
@Preview
fun ProfileImgAndEditPreview(){
    PacetrideTheme(darkTheme = true) {
        ProfileImgAndEdit(
            action = {},
            imageURL =  null
        )
    }
}