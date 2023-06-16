package com.bangkit.capstone.beangreader.presentation.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun DetailShimmerScreen() {
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

    DetailShimmerContent(brush)
}

@Composable
fun DetailShimmerContent(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .background(brush)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Spacer(
            modifier = Modifier
                .width(164.dp)
                .height(32.dp)
                .clip(ShapeDefaults.Small)
                .align(Alignment.CenterHorizontally)
                .background(brush)
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(32.dp),
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(ShapeDefaults.Small)
                    .background(brush)
            )
        }
    }
}