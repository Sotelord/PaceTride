package com.example.pacetride.ui.utils.reviews

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.R
import com.example.pacetride.data.Resena
import com.example.pacetride.data.local.LocalResenaProvider
import com.example.pacetride.ui.theme.PacetrideTheme
import com.example.pacetride.ui.utils.ProfileAsyncImage

@Composable
fun ResenaCard(
    modifier: Modifier = Modifier,
    resena: Resena,
    isOwn: Boolean,
    onClickEdit: (String, String) -> Unit,
    onClickDelete: (String) -> Unit
) {
    var meGustaActivo by remember { mutableStateOf(false) }
    var cantidadLikes by remember (resena.id) { mutableIntStateOf(resena.likes) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileAsyncImage(
                imageURL = resena.fotoUsuario,
                size = 48,
                imgSize = 22,
                background = MaterialTheme.colorScheme.background,
                errorIcon = R.drawable.ic_user_profile
            )

            Spacer(modifier = Modifier.width(12.dp))

            if (resena.usuario != null){
                Text(
                    text = resena.usuario,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(text = resena.fechaPublicacion,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Renderiza de manera dinámica la calificación
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (resena.carrera != null){
                Text(
                    text = resena.carrera,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = resena.calificacion,
                color = MaterialTheme.colorScheme.primaryContainer,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(R.drawable.ic_estrella),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.size(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        // Renderiza de manera dinámica el texto de la reseña
        Text(
            text = resena.resena,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            // ---------- LIKE ----------
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        Log.d("PubliProfileScreen", "Like clicked")
                        meGustaActivo = !meGustaActivo
                        cantidadLikes += if (meGustaActivo) 1 else -1
                    }
                )
            ) {
                Image(
                    painter = painterResource(
                        id = if (meGustaActivo) R.drawable.ic_me_gusta_lleno else R.drawable.ic_me_gusta
                    ),
                    contentDescription = "Likes",
                    colorFilter = if (meGustaActivo) ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer) else ColorFilter.tint(
                        MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Esta reseña le gusto a $cantidadLikes personas",
                    color = if (meGustaActivo) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        if (isOwn) {
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
            )
            Row( modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable{
                        onClickEdit(resena.carreraId, resena.id)
                        Log.d("ResenaCard", "Editar Pressed")
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_edit_resena),
                        contentDescription = "Editar",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Editar",
                        color = MaterialTheme.colorScheme.primaryContainer,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable{
                        onClickDelete(resena.id)
                        Log.d("ResenaCard", "Eliminar Pressed")
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_eliminar_resena),
                        contentDescription = "Eliminar",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiaryContainer),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Eliminar",
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ResenaCardPreview() {
    PacetrideTheme(darkTheme = true) {
        val resena = LocalResenaProvider.listaResenas[3]
        ResenaCard(
            resena = resena,
            isOwn = false,
            onClickEdit = { _, _ -> },
            onClickDelete = {}
        )
    }
}

@Composable
@Preview
fun ResenaCardPreview2() {
    PacetrideTheme(darkTheme = true) {
        val resena = LocalResenaProvider.listaResenas[3]
        ResenaCard(
            resena = resena,
            isOwn = true,
            onClickEdit = { _, _ -> },
            onClickDelete = {}
        )
    }
}