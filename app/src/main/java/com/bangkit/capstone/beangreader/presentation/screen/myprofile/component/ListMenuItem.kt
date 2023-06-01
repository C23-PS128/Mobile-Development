package com.bangkit.capstone.beangreader.presentation.screen.myprofile.component

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListMenuItem(
    modifier: Modifier = Modifier,
    title: String,
    text: @Composable () -> Unit,
) {
    Text(
        text = title,
        color = Color.Gray,
        modifier = Modifier.padding(start = 16.dp)
    )
    ListItem(
        modifier = modifier,
        headlineContent = text
    )
    Divider(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
    )
}