package com.bangkit.capstone.beangreader.presentation.screen.favorite.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ListFavoriteItem(
    title: String,
    image: String,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(
                text = title,
            )
        },
        leadingContent = {
            AsyncImage(
                model = image,
                contentDescription = "Image Item",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
        },
        modifier = modifier.clickable {
            navigateToDetail
        }
    )
    Divider()
}