package com.bangkit.capstone.beangreader.presentation.screen.authentication

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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.GoogleButton
import com.bangkit.capstone.beangreader.presentation.screen.authentication.component.TextBox
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onSigInClick: () -> Unit
) {
    RegisterContent(
        urlImage = "",
        text = "",
        onValueChange = {},
        onRegisterClick = onRegisterClick,
        onSigInClick = onSigInClick
    )
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    urlImage: String,
    text: String,
    onValueChange: (String) -> Unit,
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
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(top = 4.dp, bottom = 16.dp)
        )
        TextBox(
            text = text,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.icon_person)
                )
            },
            label = {
                Text(stringResource(R.string.name))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {}
        TextBox(
            text = text,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = stringResource(R.string.icon_email)
                )
            },
            label = {
                Text(stringResource(R.string.email))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {}
        TextBox(
            text = text,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = stringResource(R.string.icon_password)
                )
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.RemoveRedEye,
                        contentDescription = stringResource(R.string.icon_eye),
                    )
                }
            },
            label = {
                Text(stringResource(R.string.password))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        TextBox(
            text = text,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = stringResource(R.string.icon_password)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.RemoveRedEye,
                    contentDescription = stringResource(R.string.icon_eye)
                )
            },
            label = {
                Text(stringResource(R.string.confirm_password))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Button(
            onClick = onRegisterClick,
            shape = ShapeDefaults.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(48.dp)
        ) {
            Text(
                text = stringResource(R.string.register),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            text = stringResource(R.string.or),
            style = MaterialTheme.typography.labelMedium
        )
        GoogleButton(
            clicked = { /*TODO*/ },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(48.dp)
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
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