package com.bangkit.capstone.beangreader.presentation.screen.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.R

@Composable
fun SearchBarView(
    navigateToSearch: () -> Unit,
    modifier: Modifier = Modifier,
    ) {
    TextField(
        value = "",
        onValueChange = { },
        enabled = false,
        placeholder = {
            Text(stringResource(R.string.search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search_icon"
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = MaterialTheme.shapes.extraLarge,
        singleLine = true,
        modifier = modifier
            .padding(16.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .clickable { navigateToSearch() }
    )
}