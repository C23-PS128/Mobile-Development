package com.bangkit.capstone.beangreader.presentation.screen.camera.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bangkit.capstone.beangreader.R

@Composable
fun CustomLoading(
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(
            shape = MaterialTheme.shapes.extraLarge,
            modifier = modifier
                .fillMaxWidth(0.90f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(56.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.loading_desc),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}