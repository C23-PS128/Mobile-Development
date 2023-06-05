package com.bangkit.capstone.beangreader.presentation.screen.authentication.register

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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.GoogleButton
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.NormalTextField
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.PasswordTextField
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onSigInClick: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
        onRegisterClick = onRegisterClick,
        onSigInClick = onSigInClick
    )
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    password: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    onSigInClick: () -> Unit
) {
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
            text = stringResource(R.string.sign_up),
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = stringResource(R.string.description_signup),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 4.dp, bottom = 16.dp)
        )
        NormalTextField(
            value = name,
            onValueChange = onNameChange,
            imageVector = Icons.Outlined.Person,
            contentDescription = stringResource(R.string.icon_person),
            label = stringResource(R.string.name),
            keyboardType = KeyboardType.Text
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
        Button(
            onClick = onRegisterClick,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp)
                .height(48.dp)
        ) {
            Text(
                text = stringResource(R.string.register),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            text = stringResource(R.string.or),
            style = MaterialTheme.typography.bodyMedium
        )
        GoogleButton(
            clicked = { /*TODO*/ },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .height(48.dp)
        )
        Row(
            modifier = Modifier,
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
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    BeanGreaderTheme {
        RegisterScreen(
            onRegisterClick = {},
            onSigInClick = {}
        )
    }
}