package com.bangkit.capstone.beangreader.presentation.screen.authentication.register

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.authentication.common.rememberImeState
import com.bangkit.capstone.beangreader.presentation.screen.authentication.common.signInIntentSender
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.GoogleButton
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.NormalTextField
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.PasswordTextField
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.TextChoice
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
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
                        RegisterEvent.SignInGoogleWithIntent(
                            result.data ?: return@launch
                        )
                    )
                }
            }
        }
    )

    LaunchedEffect(key1 = state.isError) {
        state.isError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(key1 = state.isSuccess) {
        if (state.isSuccess) {
            navigateToHome()
            Toast.makeText(context, context.getString(R.string.login_success), Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = state.registerSuccess) {
        if (state.registerSuccess) {
            Toast.makeText(context, context.getString(R.string.register_success), Toast.LENGTH_SHORT).show()
            navigateToLogin()
        }
    }

    RegisterContent(
        name = state.name,
        email = state.email,
        password = state.password,
        onNameChange = { name ->
            viewModel.onEvent(RegisterEvent.OnNameChange(name))
        },
        onEmailChange = { email ->
            viewModel.onEvent(RegisterEvent.OnEmailChange(email))
        },
        onPasswordChange = { password ->
            viewModel.onEvent(RegisterEvent.OnPasswordChange(password))
        },
        onRegisterClick = {
            viewModel.onEvent(
                RegisterEvent.OnRegisterClick(
                    state.name,
                    state.email,
                    state.password
                )
            )
        },
        onGoogleClick = {
            scope.launch {
                val intentSender = signInIntentSender(context)
                launcher.launch(
                    IntentSenderRequest.Builder(
                        intentSender ?: return@launch
                    ).build()
                )
            }
        },
        onSigInClick = navigateToLogin,
        isLoading = state.isLoading,
        isConnectLoading = state.isConnectLoading
    )
}

@Composable
fun RegisterContent(
    name: String,
    email: String,
    password: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onSigInClick: () -> Unit,
    isLoading: Boolean,
    isConnectLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        } else {
            scrollState.scrollTo(0)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = stringResource(R.string.sign_up),
                style = MaterialTheme.typography.headlineLarge,
            )

            Text(
                text = stringResource(R.string.description_signup),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            NormalTextField(
                value = name,
                onValueChange = onNameChange,
                imageVector = Icons.Outlined.Person,
                contentDescription = stringResource(R.string.icon_person),
                label = stringResource(R.string.name),
            )

            NormalTextField(
                value = email,
                onValueChange = onEmailChange,
                imageVector = Icons.Outlined.Email,
                contentDescription = stringResource(R.string.icon_email),
                label = stringResource(R.string.email),
            )

            PasswordTextField(
                text = password,
                onValueChange = onPasswordChange,
                label = stringResource(R.string.password)
            )

            Button(
                onClick = onRegisterClick,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 16.dp)
                    .height(48.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 3.dp,
                        modifier = Modifier
                            .size(32.dp)
                    )
                } else {
                    Text(
                        text = stringResource(R.string.register),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            TextChoice()

            GoogleButton(
                clicked = onGoogleClick,
                isConnectLoading = isConnectLoading,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(48.dp)
            )

            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = stringResource(R.string.have_account))
                TextButton(onClick = onSigInClick) {
                    Text(
                        text = stringResource(R.string.sign_in),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}