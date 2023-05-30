package com.bangkit.capstone.beangreader.ui.screen.profile.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.R

@Composable
fun LogoutButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = ShapeDefaults.Medium,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
    ) {
        Text(
            text = stringResource(R.string.logout),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}