package com.bangkit.capstone.beangreader.presentation.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchSimmerScreen() {
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

    SearchShimmerContent(brush = brush)
}

@Composable
fun SearchShimmerContent(
    brush: Brush,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ListItemShimmer(brush = brush)
        ListItemShimmer(brush = brush)
        ListItemShimmer(brush = brush)
        ListItemShimmer(brush = brush)
        ListItemShimmer(brush = brush)
        ListItemShimmer(brush = brush)
        ListItemShimmer(brush = brush)
        ListItemShimmer(brush = brush)
    }
}

@Composable
fun ListItemShimmer(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier
            .size(56.dp)
            .clip(MaterialTheme.shapes.small)
            .background(brush)
        )
        Spacer(modifier = Modifier
            .height(16.dp)
            .padding(start = 16.dp)
            .clip(MaterialTheme.shapes.small)
            .fillMaxWidth(0.4f)
            .background(brush)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchShimmerPreview() {
    SearchSimmerScreen()
}