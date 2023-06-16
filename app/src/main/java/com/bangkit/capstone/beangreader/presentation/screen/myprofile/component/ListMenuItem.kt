package com.bangkit.capstone.beangreader.presentation.screen.myprofile.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListMenuItem(
    modifier: Modifier = Modifier,
    headlineContent: @Composable () -> Unit,
    overLineContent: @Composable () -> Unit,
) {
    ListItem(
        modifier = modifier,
        headlineContent = headlineContent,
        overlineContent = overLineContent
    )
    Divider(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
    )
}