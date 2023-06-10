package com.bangkit.capstone.beangreader.presentation.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.data.remote.response.article.ResultsItem
import com.bangkit.capstone.beangreader.presentation.component.EmptyScreen
import com.bangkit.capstone.beangreader.presentation.screen.search.component.ListSearchItem
import com.bangkit.capstone.beangreader.presentation.screen.search.component.SearchBar

@Composable
fun SearchScreen(
    onClickBack: () -> Unit,
    navigateToDetail: (Int, Int, String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SearchContent(
        query = state.query,
        onQueryChange = { query ->
            viewModel.onEvent(SearchEvent.OnQueryChange(query))
        },
        onClickBack = onClickBack,
        listSearch = state.searchItem,
        navigateToDetail = navigateToDetail
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    query: String,
    onQueryChange: (String) -> Unit,
    onClickBack: () -> Unit,
    listSearch: List<ResultsItem>,
    navigateToDetail: (Int, Int, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Explore")
                },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.icon_arrow)
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
            )
            if (listSearch.isEmpty()) {
                EmptyScreen()
            } else {
                LazyColumn(verticalArrangement = Arrangement.Center) {
                    items(listSearch) { item ->
                        ListSearchItem(
                            id = item.id,
                            type = item.type,
                            title = "${item.title}",
                            image = "${item.image}",
                            navigateToDetail = navigateToDetail
                        )
                    }
                }
            }
        }
    }
}
