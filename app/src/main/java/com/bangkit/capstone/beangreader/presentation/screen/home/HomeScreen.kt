package com.bangkit.capstone.beangreader.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.data.remote.response.article.BrewsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.DrinksItem
import com.bangkit.capstone.beangreader.data.remote.response.article.RoastsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.TypeCoffeeItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ImageSlider
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ListBrewsItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ListDrinkItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ListRoastsItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ListTypesItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int, Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        query = state.query,
        onQueryChange = { query ->
            viewModel.onEvent(HomeEvent.OnQueryChange(query))
        },
        typesItem = state.listTypes,
        roastsItem = state.listRoasts,
        brewsItem = state.listBrews,
        drinksItem = state.listDrinks,
        navigateToDetail = navigateToDetail,
        bannerImages = state.listBannerImages,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    typesItem: List<TypeCoffeeItem>,
    roastsItem: List<RoastsItem>,
    brewsItem: List<BrewsItem>,
    drinksItem: List<DrinksItem>,
    bannerImages: List<String>,
    navigateToDetail: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier.fillMaxWidth()
        )
        ImageSlider(
            pagerImages = bannerImages
        )
        Text(
            text = stringResource(R.string.type),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp)
        )
        ListTypesItem(
            typesItem = typesItem,
            navigateToDetail = {
                navigateToDetail(it, 0)
            },
        )
        Text(
            text = stringResource(R.string.roast),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp)
        )
        ListRoastsItem(
            roastsItem = roastsItem,
            navigateToDetail = {
                navigateToDetail(it, 1)
            }
        )
        Text(
            text = stringResource(R.string.brew),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp)
        )
        ListBrewsItem(
            brewsItem = brewsItem,
            navigateToDetail = {
                navigateToDetail(it, 2)
            }
        )
        Text(
            text = stringResource(R.string.drink),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp)
        )
        ListDrinkItem(
            drinksItem = drinksItem,
            navigateToDetail = {
                navigateToDetail(it, 3)
            }
        )
    }
}