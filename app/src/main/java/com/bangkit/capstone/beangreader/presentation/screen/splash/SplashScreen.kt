package com.bangkit.capstone.beangreader.presentation.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onTimeOut: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn
    val currentOnTimeOut by rememberUpdatedState(onTimeOut)

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(2000)
    )
    
    LaunchedEffect(currentOnTimeOut) {
        startAnimation = true
        delay(3000L)
        onTimeOut(isLoggedIn)
    }
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.splashbean),
            contentDescription = "logo",
            alpha = alphaAnim.value,
            modifier = Modifier.size(196.dp)
        )
    }
}

@Preview
@Composable
fun SplashPreview() {
    BeanGreaderTheme {
        SplashScreen(onTimeOut = {})
    }
}