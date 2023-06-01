package com.bangkit.capstone.beangreader.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.model.bean.Bean
import com.bangkit.capstone.beangreader.presentation.component.EmptyScreen
import com.bangkit.capstone.beangreader.presentation.screen.home.component.CardItem
import com.bangkit.capstone.beangreader.presentation.screen.home.component.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        query = state.query,
        onQueryChange = {
            viewModel.onEvent(HomeEvent.OnQueryChange(it))
        },
        beansItem = state.listBean,
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
    beansItem: List<Bean>,
    bannerImages: List<String>,
    navigateToDetail: (Int) -> Unit,
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
        if (beansItem.isNotEmpty()) {
            Text(
                text = stringResource(R.string.popular),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 20.dp, top = 24.dp)
            )
            ListBean(
                beanItem = beansItem,
                navigateToDetail = navigateToDetail,
            )
            Text(
                text = stringResource(R.string.popular),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 20.dp, top = 24.dp)
            )
            ListBean(
                beanItem = beansItem,
                navigateToDetail = navigateToDetail
            )
        } else {
            EmptyScreen()
        }
    }
}

@Composable
fun ListBean(
    beanItem: List<Bean>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(beanItem, key = { it.id }) { item ->
            CardItem(
                urlImage = item.image,
                title = item.title,
                onClick = {
                    navigateToDetail(item.id)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(
    pagerImages: List<String>,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        HorizontalPager(
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageCount = pagerImages.size,
            state = pagerState,
            pageSpacing = 16.dp
        ) {
            AsyncImage(
                model = pagerImages[it],
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .height(144.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        DotsIndicator(
            total = pagerImages.size,
            selectedIndex = pagerState.currentPage
        )
    }
}

@Composable
fun DotsIndicator(
    total: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(count = total) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .width(12.dp)
                        .height(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }
            if (index != total -1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}