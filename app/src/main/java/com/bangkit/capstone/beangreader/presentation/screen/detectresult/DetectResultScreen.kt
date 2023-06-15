package com.bangkit.capstone.beangreader.presentation.screen.detectresult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R

@Composable
fun DetectResultScreen(
    image: String,
    moisture: String,
    description: String,
    onBackPressed: () -> Unit
) {
    DetectResultContent(
        image = image,
        moisture = moisture,
        description = description,
        onBackPressed = onBackPressed
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetectResultContent(
    image: String,
    moisture: String,
    description: String,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Hasil")
            },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) {
        Column(
            modifier
                .padding(it)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(250.dp)
                    .clip(ShapeDefaults.Medium)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.moisture), modifier = Modifier.width(72.dp))
                Text(text = ":", modifier = Modifier.padding(horizontal = 8.dp))
                Text(
                    text = moisture,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.message), modifier = Modifier.width(72.dp))
                Text(text = ":", modifier = Modifier.padding(horizontal = 8.dp))
                Text(
                    text = description,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}