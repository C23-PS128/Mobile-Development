package com.bangkit.capstone.beangreader.presentation.screen.authentication.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.R

@Composable
fun GoogleButton(
    clicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = clicked,
        shape = MaterialTheme.shapes.large,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(R.drawable.google_icon),
            contentDescription = stringResource(R.string.google_button),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            stringResource(R.string.connect_google),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton(clicked = {})
}