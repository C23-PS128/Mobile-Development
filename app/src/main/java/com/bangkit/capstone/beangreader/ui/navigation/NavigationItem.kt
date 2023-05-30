package com.bangkit.capstone.beangreader.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val contentScreen: Screen,
    val contentDescription: String
)
