package com.example.pacetride.ui.screens.comunidad.components.content

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun TabsComunidad(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val titulos = listOf("TODAS", "SIGUIENDO")
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
        divider = { HorizontalDivider(color = MaterialTheme.colorScheme.outline) },
        modifier = modifier
    ) {
        titulos.forEachIndexed { index, titulo ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        titulo,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedTabIndex == index) MaterialTheme.colorScheme.onBackground
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun TabsComunidadPreview(){
    PacetrideTheme(darkTheme = true) {
        TabsComunidad(selectedTabIndex = 0, onTabSelected = {})
    }
}