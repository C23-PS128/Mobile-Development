package com.bangkit.capstone.beangreader.presentation.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.data.remote.response.article.DrinksItem

@Composable
fun ListDrinkItem(
    drinksItem: List<DrinksItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int, String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(drinksItem, key = { it.id }) { item ->
            CardItem(
                urlImage = "${item.image}",
                title = "${item.title}",
                onClick = {
                    navigateToDetail(item.id, item.title ?: "")
                }
            )
        }
    }
}