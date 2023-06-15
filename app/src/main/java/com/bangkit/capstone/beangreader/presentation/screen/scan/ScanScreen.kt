package com.bangkit.capstone.beangreader.presentation.screen.scan

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme


@Composable
fun ScanScreen(
    moveToCamera: () -> Unit
) {
    ScanContent(
        moveToCamera = moveToCamera
    )
}

@Composable
fun ScanContent(
    moveToCamera: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.title_scan),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(128.dp))
//            LottieAnimation(
//                composition = composition,
//                iterations = LottieConstants.IterateForever,
//                modifier = Modifier.weight(1f)
//            )
            Image(
                painter = painterResource(id = R.drawable.camera_add),
                contentDescription = "Image Scan",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .size(144.dp)
            )
            Spacer(modifier = Modifier.height(128.dp))
            Text(
                text = stringResource(R.string.description_scan),
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
            )
            Button(
                onClick = moveToCamera,
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.upload),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanScreenPreview() {
    BeanGreaderTheme {
        ScanScreen() {}
    }
}