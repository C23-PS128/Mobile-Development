package com.bangkit.capstone.beangreader.presentation.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.bangkit.capstone.beangreader.presentation.component.EmptyScreen
import com.bangkit.capstone.beangreader.presentation.screen.favorite.component.ListFavoriteItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    onBackClick: () -> Unit,
    navigateToDetail: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

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
    ) {
        Box(modifier = Modifier.padding(it)) {
            if (
                state.favoriteTypeEntity.isEmpty() &&
                state.favoriteRoastEntity.isEmpty() &&
                state.favoriteBrewEntity.isEmpty() &&
                state.favoriteDrinkEntity.isEmpty()
            ) {
                EmptyScreen()
            } else {
                FavoriteContent(
                    listTypeFavorite = state.favoriteTypeEntity,
                    listRoastFavorite = state.favoriteRoastEntity,
                    listBrewFavorite = state.favoriteBrewEntity,
                    listDrinkFavorite = state.favoriteDrinkEntity,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listTypeFavorite: List<FavoriteEntity>,
    listRoastFavorite: List<FavoriteEntity>,
    listBrewFavorite: List<FavoriteEntity>,
    listDrinkFavorite: List<FavoriteEntity>,
    navigateToDetail: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listTypeFavorite) { item ->
            ListFavoriteItem(
                id = item.id,
                title = item.title,
                type = item.type,
                image = "${item.image}",
                navigateToDetail = navigateToDetail
            )
        }
        items(listRoastFavorite) { item ->
            ListFavoriteItem(
                id = item.id,
                title = item.title,
                type = item.type,
                image = "${item.image}",
                navigateToDetail = navigateToDetail
            )
        }
        items(listBrewFavorite) { item ->
            ListFavoriteItem(
                id = item.id,
                title = item.title,
                type = item.type,
                image = "${item.image}",
                navigateToDetail = navigateToDetail
            )
        }
        items(listDrinkFavorite) { item ->
            ListFavoriteItem(
                id = item.id,
                title = item.title,
                type = item.type,
                image = "${item.image}",
                navigateToDetail = navigateToDetail
            )
        }
    }
}