package com.example.pacetride.ui.utils.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pacetride.ui.theme.PacetrideTheme

@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    value: String?,
    onValueChange: (String) -> Unit,
    label: String,
    colors: TextFieldColors,
    maxLength: Int? = null,
    singleLine: Boolean = false,
    minLines: Int = 1,
) {
    val texto = value ?: ""

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value ?: "",
            onValueChange =  { nuevo ->
                onValueChange(if (maxLength != null) nuevo.take(maxLength) else nuevo)
            },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = colors,
            singleLine = singleLine,
            minLines = minLines,
            supportingText = if(maxLength != null) {
                {
                    Text(
                        text = "${texto.length} / $maxLength",
                        fontSize = 11.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            } else {
                null
            }
        )
    }
}

@Composable
@Preview
fun FormTextFieldPreview() {
    PacetrideTheme(darkTheme = true) {
        val fieldColors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
        FormTextField(
            value = "",
            onValueChange = {},
            label = "Nombre completo",
            colors = fieldColors
        )
    }
}
