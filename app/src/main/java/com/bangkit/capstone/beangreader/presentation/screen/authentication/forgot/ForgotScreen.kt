package com.bangkit.capstone.beangreader.presentation.screen.authentication.forgot

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.authentication.common.rememberImeState
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme

@Composable
fun ForgotScreen(
    moveToLogin: () -> Unit,
    viewModel: ForgotViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.success) {
        if (state.success) {
            Toast.makeText(
                context,
                context.getString(R.string.password_message),
                Toast.LENGTH_SHORT
            ).show()
            moveToLogin()
        }
    }

    ForgotContent(
        email = state.email,
        onEmailChange = { email ->
            viewModel.onEvent(ForgotEvent.OnEmailChange(email))
        },
        sendEmail = { email ->
            viewModel.onEvent(ForgotEvent.SendEmailResetPassword(email))
        },
        isSendLoading = state.loading,
        moveToLogin = moveToLogin
    )
}

@Composable
fun ForgotContent(
    email: String,
    onEmailChange: (String) -> Unit,
    moveToLogin: () -> Unit,
    isSendLoading: Boolean,
    sendEmail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("lottie/password.json"))
    val scrollState = rememberScrollState()
    val imeState = rememberImeState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        } else {
            scrollState.scrollTo(0)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = moveToLogin,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(24.dp)
            ) {
                Spacer(modifier = Modifier.height(56.dp))
                LottieAnimation(
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(200.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = stringResource(R.string.description_forgot),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    shape = ShapeDefaults.Medium,
                    label = {
                        Text(text = stringResource(R.string.email))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        sendEmail(email)
                    },
                    shape = MaterialTheme.shapes.large,
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                ) {
                    if (isSendLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 3.dp,
                            modifier = Modifier
                                .size(32.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.confirm_email),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                Spacer(modifier = Modifier.height(240.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPreview() {
    BeanGreaderTheme {
        ForgotScreen(
            moveToLogin = {}
        )
    }
}