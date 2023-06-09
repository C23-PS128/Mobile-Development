package com.bangkit.capstone.beangreader.presentation.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme

@Composable
fun AboutScreen(
    onClickBack: () -> Unit
) {
    AboutContent(
        onClickBack = onClickBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutContent(
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.about)) },
                navigationIcon = {
                    IconButton(
                        onClick = onClickBack,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Icon Back"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            Image(
                painterResource(id = R.drawable.splashbean),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(144.dp)
                    .padding(vertical = 16.dp)
            )
            AboutText()
        }
    }
}

@Composable
fun AboutText(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.about_application_description),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            softWrap = true,
            letterSpacing = TextUnit.Unspecified,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}

@Preview
@Composable
fun AboutScreenPrev() {
    BeanGreaderTheme {
        AboutScreen {

        }
    }
}