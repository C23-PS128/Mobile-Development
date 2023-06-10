package com.bangkit.capstone.beangreader.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.bangkit.capstone.beangreader.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(
    onTimeOut: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn
    val currentOnTimeOut by rememberUpdatedState(onTimeOut)
    val systemUiController = rememberSystemUiController()

    DisposableEffect(key1 = true){
        systemUiController.isSystemBarsVisible = false
        onDispose {
            systemUiController.isSystemBarsVisible = true
        }
    }
    
    LaunchedEffect(currentOnTimeOut) {
        delay(3000.milliseconds)
        onTimeOut(isLoggedIn)
    }
    
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.beanicon),
            contentDescription = null
        )
    }
}