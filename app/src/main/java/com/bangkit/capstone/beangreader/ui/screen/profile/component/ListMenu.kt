package com.bangkit.capstone.beangreader.ui.screen.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.R

@Composable
fun ListMenu(
    modifier: Modifier = Modifier,
    onCLick: () -> Unit,
    text: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
) {
    ListItem(
        modifier = modifier
            .clickable { onCLick() },
        headlineContent = text,
        leadingContent = leadingIcon,
        trailingContent = {
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = stringResource(R.string.icon_arrow),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    )
    Divider(Modifier.padding(horizontal = 16.dp))
}