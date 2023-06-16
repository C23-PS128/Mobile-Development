package com.bangkit.capstone.beangreader.presentation.screen.scan.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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
            if (index != total - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}