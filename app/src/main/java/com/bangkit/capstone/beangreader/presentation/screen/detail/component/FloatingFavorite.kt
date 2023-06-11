package com.bangkit.capstone.beangreader.presentation.screen.detail.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme

@Composable
fun FloatingFavorite(
    isFav: Boolean,
    onFavClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onFavClick,
        shape = MaterialTheme.shapes.large,
        modifier = modifier
    ) {
        Icon(
            tint = if (isFav) MaterialTheme.colorScheme.primary else Color.White,
            imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "favorite_icon"
        )
    }
}

@Preview
@Composable
fun FloatingFavoritePreview() {
    BeanGreaderTheme {
        FloatingFavorite(
            isFav = false,
            onFavClick = { /*TODO*/ }
        )
    }
}