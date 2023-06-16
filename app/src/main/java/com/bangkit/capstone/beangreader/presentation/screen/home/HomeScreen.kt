package com.bangkit.capstone.beangreader.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.data.remote.response.article.BrewsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.DrinksItem
import com.bangkit.capstone.beangreader.data.remote.response.article.RoastsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.TypeCoffeeItem
import com.bangkit.capstone.beangreader.presentation.component.HomeSimmerScreen
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ImageBanner
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ListBrewsItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ListDrinkItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ListRoastsItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.ListTypesItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.SearchBarView

@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    moveToScan: () -> Unit,
    navigateToDetail: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    if (state.isLoading) {
        HomeSimmerScreen()
    } else {
        HomeContent(
            typesItem = state.listTypes,
            roastsItem = state.listRoasts,
            brewsItem = state.listBrews,
            drinksItem = state.listDrinks,
            navigateToSearch = navigateToSearch,
            navigateToDetail = navigateToDetail,
            moveToScan = moveToScan,
            modifier = modifier
        )
    }
}

@Composable
fun HomeContent(
    typesItem: List<TypeCoffeeItem>,
    roastsItem: List<RoastsItem>,
    brewsItem: List<BrewsItem>,
    drinksItem: List<DrinksItem>,
    moveToScan: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToDetail: (Int, String, Int) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
    ) {
        SearchBarView(
            navigateToSearch = navigateToSearch,
            modifier = Modifier
                .fillMaxWidth()
        )
        ImageBanner(moveToScan = moveToScan)
        Text(
            text = stringResource(R.string.type),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp)
        )
        ListTypesItem(
            typesItem = typesItem,
            navigateToDetail = { id, title ->
                navigateToDetail(id, title, 0)
            },
        )
        Text(
            text = stringResource(R.string.roast),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp)
        )
        ListRoastsItem(
            roastsItem = roastsItem,
            navigateToDetail = { id, title ->
                navigateToDetail(id, title, 1)
            }
        )
        Text(
            text = stringResource(R.string.brew),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp)
        )
        ListBrewsItem(
            brewsItem = brewsItem,
            navigateToDetail = { id, title ->
                navigateToDetail(id, title, 2)
            }
        )
        Text(
            text = stringResource(R.string.drink),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp)
        )
        ListDrinkItem(
            drinksItem = drinksItem,
            navigateToDetail = { id, title ->
                navigateToDetail(id, title, 3)
            }
        )
    }
}