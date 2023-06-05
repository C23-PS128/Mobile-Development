package com.bangkit.capstone.beangreader.presentation.screen.favorite

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import com.bangkit.capstone.beangreader.presentation.screen.favorite.component.ListFavoriteItem

@Composable
fun FavoriteScreen(
    onBackClick: () -> Unit,
    navigateToDetail: (Int, Int) -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Log.d("Favorite", "FavoriteScreen: ${state.favoriteEntity}")

    FavoriteContent(
        onBackClick = onBackClick,
        listFavorite = state.favoriteEntity,
        navigateToDetail = navigateToDetail
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteContent(
    onBackClick: () -> Unit,
    listFavorite: List<FavoriteEntity>,
    navigateToDetail: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.favorite)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                modifier = modifier
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(listFavorite, key = { it.id }) { item ->
                Log.d("Fav", "FavoriteItem: $item")
                ListFavoriteItem(
                    title = "${item.title}",
                    image = "${item.image}",
                    navigateToDetail = {
                        navigateToDetail(item.id, 0)
                    }
                )
            }
        }
    }
}