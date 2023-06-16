package com.bangkit.capstone.beangreader.presentation.screen.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageBanner(
    moveToScan: () -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = "https://storage.googleapis.com/c23-ps128/banner/photo_2023-06-13_19-14-58.jpg",
        contentDescription = "Detection Image",
        modifier = modifier
            .padding(16.dp)
            .clip(ShapeDefaults.Large)
            .clickable {
                moveToScan()
            }
    )
}