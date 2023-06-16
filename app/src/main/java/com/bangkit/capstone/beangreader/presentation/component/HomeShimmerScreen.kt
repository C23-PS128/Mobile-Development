package com.bangkit.capstone.beangreader.presentation.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeSimmerScreen() {
    val shimmerColor = listOf(
        Color.LightGray.copy(0.6f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    HomeSimmerContent(brush)
}

@Composable
fun HomeSimmerContent(
    brush: Brush,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
                    .clip(ShapeDefaults.ExtraLarge)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(164.dp)
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
                    .clip(ShapeDefaults.Medium)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Spacer(
                modifier = Modifier
                    .width(156.dp)
                    .height(32.dp)
                    .padding(start = 16.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .size(164.dp)
                        .clip(ShapeDefaults.Small)
                        .background(brush)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Spacer(
                    modifier = Modifier
                        .size(164.dp)
                        .clip(ShapeDefaults.Small)
                        .background(brush)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Spacer(
                    modifier = Modifier
                        .size(164.dp)
                        .clip(ShapeDefaults.Small)
                        .background(brush)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Spacer(
                modifier = Modifier
                    .width(156.dp)
                    .height(32.dp)
                    .padding(start = 16.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .size(164.dp)
                        .clip(ShapeDefaults.Small)
                        .background(brush)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Spacer(
                    modifier = Modifier
                        .size(164.dp)
                        .clip(ShapeDefaults.Small)
                        .background(brush)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Spacer(
                    modifier = Modifier
                        .size(164.dp)
                        .clip(ShapeDefaults.Small)
                        .background(brush)
                )
            }
        }
    }
}