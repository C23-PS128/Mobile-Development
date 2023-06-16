package com.bangkit.capstone.beangreader.presentation.screen.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme

@Composable
fun UploadScreen() {
    UploadContent()
}

@Composable
fun UploadContent(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Box(
                modifier = Modifier
                    .padding(vertical = 24.dp)
            ) {
                Image(
                    imageVector = Icons.Default.Padding,
                    contentDescription = "Image Preview",
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Upload",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UploadScreenPreview() {
    BeanGreaderTheme {
        UploadScreen()
    }
}