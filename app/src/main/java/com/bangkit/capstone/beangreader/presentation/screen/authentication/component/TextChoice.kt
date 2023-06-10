package com.bangkit.capstone.beangreader.presentation.screen.authentication.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme

@Composable
fun TextChoice(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(R.string.or),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextChoicePreview() {
    BeanGreaderTheme {
        TextChoice()
    }
}