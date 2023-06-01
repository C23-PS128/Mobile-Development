package com.bangkit.capstone.beangreader.presentation.screen.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.setting.component.ListMenuItem
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme


@Composable
fun SettingScreen(
    onLanguageClick: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: SettingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingContent(
        theme = state.isDarkMode,
        onThemeUpdate = {
            viewModel.onEvent(SettingEvent.OnSwitchThemeChange(it))
        },
        onBackClick = onBackClick,
        onLanguageClick = onLanguageClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingContent(
    theme: Boolean,
    onThemeUpdate: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    onLanguageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.setting)) },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back",
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(top = 16.dp)
        ) {
            ListMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.language),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                modifier = Modifier.clickable {
                    onLanguageClick()
                },
                trailingIcon = {}
            )
            ListMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.theme),
                        style = MaterialTheme.typography.bodyLarge)
                },
                trailingIcon = {
                    Switch(
                        checked = theme,
                        onCheckedChange = onThemeUpdate,
                        thumbContent = {
                            Icon(
                                modifier = Modifier
                                    .size(SwitchDefaults.IconSize),
                                imageVector = if (theme) Icons.Rounded.DarkMode else Icons.Rounded.LightMode,
                                contentDescription = "Switch Icon"
                            )
                        },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = MaterialTheme.colorScheme.primary,
                            checkedThumbColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun SettingScreenPreview() {
    BeanGreaderTheme {
        SettingContent(
            theme = true,
            onThemeUpdate = {},
            onLanguageClick = {},
            onBackClick = {}
        )
    }
}