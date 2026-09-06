package com.example.pacetride.ui.screens.misCarreras.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.utils.CampanaNotificaciones
import com.example.pacetride.ui.utils.LogoApp

@Composable
fun HeaderMisCarreras(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    notificacionButtonPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            LogoApp(
                modifier = Modifier
                    .height(40.dp)
                    .align(Alignment.Center)
            )

            CampanaNotificaciones(
                onClick = notificacionButtonPressed,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        // Títulos
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(
                text = "Mis carreras",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Tus próximos retos y tus carreras completadas.",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Pestañas (Tabs)
        SecondaryTabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(selectedTabIndex),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            },
            divider = { HorizontalDivider(color = MaterialTheme.colorScheme.outline) }
        ) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { onTabSelected(0) },
                text = { Text("PRÓXIMAS", fontWeight = FontWeight.Bold, color = if (selectedTabIndex == 0) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant) }
            )
            Tab(
                selected = selectedTabIndex == 1,
                onClick = { onTabSelected(1) },
                text = { Text("COMPLETADAS", fontWeight = FontWeight.Bold, color = if (selectedTabIndex == 1) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant) }
            )
        }
    }
}

@Preview
@Composable
fun HeaderMisCarrerasPreview() {
    HeaderMisCarreras(
        selectedTabIndex = 0,
        onTabSelected = {},
        notificacionButtonPressed = {}
    )
}