package com.bangkit.capstone.beangreader.presentation.screen.authentication.login

import android.app.Activity.RESULT_OK
import android.content.IntentSender
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.authentication.common.signInIntentSender
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.GoogleButton
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.NormalTextField
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.PasswordTextField
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    navigateToHome: () -> Unit,
//    moveToUserPreference: (userData: UserData?) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                scope.launch {
                    viewModel.onEvent(
                        LoginEvent.SignInGoogleWithIntent(
                            result.data ?: return@launch
                        )
                    )
                }
            }
        }
    )

    LaunchedEffect(key1 = state.isSuccess) {
        if (state.isSuccess) {
//            if (state.signInResult?.data?.isNewUser == true) moveToUserPreference(state.signInResult?.data)
//            else
            navigateToHome()
            viewModel.onEvent(LoginEvent.ResetState)
        }
    }

    LoginContent(
        email = state.email,
        password = state.password,
        onEmailChange = { email ->
            viewModel.onEvent(LoginEvent.OnEmailChange(email))
        },
        onPasswordChange = { password ->
            viewModel.onEvent(LoginEvent.OnPasswordChange(password))
        },
        onLoginClick = {
            viewModel.onEvent(LoginEvent.IsLoginClick(state.email, state.password))
        },
        onGoogleClick = {
            scope.launch {
                Log.d("GOOGLE", "LoginScreen: $this")
                val signInIntentSender = signInIntentSender(context)
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        },
        onSignUpClick = onSignUpClick,
        isLoading = state.isLoading,
        loginError = state.isError,
    )
    if (state.isSuccess) {
        LaunchedEffect(Unit) {
            navigateToHome()
        }
    }
}

@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onSignUpClick: () -> Unit,
    isLoading: Boolean,
    loginError: String?,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }
        LaunchedEffect(key1 = loginError) {
            loginError?.let {
                Toast.makeText(context, loginError, Toast.LENGTH_SHORT).show()
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            AsyncImage(
                model = "https://cdn2.iconfinder.com/data/icons/coffee-store/64/coffee-11-1024.png",
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clip(CircleShape)
                    .size(128.dp)
            )
            Text(
                text = stringResource(R.string.sign_in),
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = stringResource(R.string.description_sign_in),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 42.dp)
            )
            NormalTextField(
                value = email,
                onValueChange = onEmailChange,
                imageVector = Icons.Outlined.Email,
                contentDescription = stringResource(R.string.icon_email),
                label = stringResource(R.string.email),
                keyboardType = KeyboardType.Email
            )
            PasswordTextField(
                text = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.password)
            )
            TextButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.forgot),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Button(
                onClick = onLoginClick,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = stringResource(R.string.or),
                style = MaterialTheme.typography.bodyMedium,
            )
            GoogleButton(
                clicked = onGoogleClick,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(48.dp)
            )
            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.need_account))
                TextButton(onClick = onSignUpClick) {
                    Text(
                        text = stringResource(R.string.sign_up),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}