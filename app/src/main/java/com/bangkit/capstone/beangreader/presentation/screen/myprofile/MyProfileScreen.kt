package com.bangkit.capstone.beangreader.presentation.screen.myprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.myprofile.component.ListMenuItem

@Composable
fun MyProfileScreen(
    onBackClick: () -> Unit
) {
    MyProfileContent(onBackClick = onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileContent(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { 
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.my_profile))}, 
                navigationIcon = {
                    IconButton (onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back"
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
            AsyncImage(
                model = "https://pbs.twimg.com/media/FjU2lkcWYAgNG6d.jpg",
                contentDescription = stringResource(R.string.profile_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 24.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .size(148.dp)
            )
            ListMenuItem(
                title = "name",
                text = {
                    Text(
                        text = "John Wick",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
            ListMenuItem(
                title = "email",
                text = {
                    Text(
                        text = "johnwick@gmail.com",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
            ListMenuItem(
                title = "phone",
                text = {
                    Text(
                        text = "083217382931",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
        }
    }
}