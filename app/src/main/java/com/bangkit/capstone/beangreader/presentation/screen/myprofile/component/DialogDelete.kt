package com.bangkit.capstone.beangreader.presentation.screen.myprofile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bangkit.capstone.beangreader.R

@Composable
fun DialogDeleteScreen(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    loading: Boolean,
) {
    DialogDeleteContent(
        onDismiss = onDismiss,
        onConfirm = onConfirm,
        loading = loading
    )
}

@Composable
fun DialogDeleteContent(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    loading: Boolean,
    modifier: Modifier = Modifier,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(),
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            modifier = modifier
                .fillMaxWidth(0.95f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.deleted_confirm))
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                        )
                    }
                    Button(
                        onClick = onConfirm,
                        modifier = Modifier.weight(1f)

                    ) {
                        if (loading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.confirm)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DeleteDialogPreview() {
    DialogDeleteScreen(onConfirm = {}, onDismiss = {}, loading = false)
}