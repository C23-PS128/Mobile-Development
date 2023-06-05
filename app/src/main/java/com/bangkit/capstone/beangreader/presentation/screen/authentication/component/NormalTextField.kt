package com.bangkit.capstone.beangreader.presentation.screen.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun NormalTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    imageVector: ImageVector,
    keyboardType: KeyboardType,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        shape = ShapeDefaults.Medium,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription
            )
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = { Text(text = label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    )
}