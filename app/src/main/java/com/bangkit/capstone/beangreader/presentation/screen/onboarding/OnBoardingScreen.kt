package com.bangkit.capstone.beangreader.presentation.screen.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.onboarding.model.OnBoardingData
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    moveToLogin: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val (selectedPage, setSelectedPage) = remember {
        mutableStateOf(0)
    }

    val onBoarding = OnBoardingData.onBoardingItems

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            setSelectedPage(page)
        }
    }

    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            HorizontalPager(
                pageCount = onBoarding.size,
                state = pagerState,
                modifier = Modifier.weight(0.6f)
            ) { page ->
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(onBoarding[page].resId))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    LottieAnimation(
                        composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = stringResource(onBoarding[page].title),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(onBoarding[page].description),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                for (i in onBoarding.indices) {
                    Box(
                        modifier = Modifier
                            .padding(end = if (i == onBoarding.size - 1) 0.dp else 5.dp)
                            .width(if (i == selectedPage) 20.dp else 10.dp)
                            .height(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (i == selectedPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.1f
                                )
                            )
                    )
                }
            }

            if (selectedPage != onBoarding.size - 1) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(onBoarding.size - 1)
                            }
                        },
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.skip),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    Button(
                        onClick = {
                            scope.launch {
                                val nextPage = selectedPage + 1
                                pagerState.animateScrollToPage(nextPage)
                            }
                        },
                        modifier = Modifier.height(48.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.next),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            if (selectedPage == onBoarding.size - 1) {
                Button(
                    onClick = {
                        moveToLogin()
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                ) {
                    Text(
                        text = stringResource(R.string.get_started),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
