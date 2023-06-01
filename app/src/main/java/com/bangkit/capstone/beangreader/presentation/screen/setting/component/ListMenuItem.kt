package com.bangkit.capstone.beangreader.presentation.screen.setting.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListMenuItem(
    modifier: Modifier = Modifier,
    text: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
) {
    ListItem(
        modifier = modifier,
        headlineContent = text,
        trailingContent = trailingIcon
    )
    Divider(Modifier.padding(horizontal = 16.dp))
}