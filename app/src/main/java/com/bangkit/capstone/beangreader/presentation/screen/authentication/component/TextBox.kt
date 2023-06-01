package com.bangkit.capstone.beangreader.presentation.screen.authentication.component

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextBox(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit
) {
    OutlinedTextField(
        value = text,
        shape = ShapeDefaults.Medium,
        onValueChange = onValueChange,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        label = label,
        modifier = modifier
    )
}