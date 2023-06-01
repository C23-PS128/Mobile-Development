package com.bangkit.capstone.beangreader.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.detail.component.FloatingFavorite
import com.bangkit.capstone.beangreader.ui.theme.BeanGreaderTheme

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(DetailEvent.GetDetail(id))
    }
    val state by viewModel.state.collectAsStateWithLifecycle()


    state.bean?.let {
        DetailContent(
            urlImage = it.image,
            title = it.title,
            description = it.description,
            isFav = state.isFav,
            onFavClick = {
                viewModel.onEvent(DetailEvent.OnFavClick(state.isFav))
            },
            navigateBack = navigateBack
    )
    }
}

@Composable
fun DetailContent(
    urlImage: String,
    title: String,
    description: String,
    isFav: Boolean,
    onFavClick: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.height(264.dp)) {
            AsyncImage(
                model = urlImage,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(240.dp)
            )
            IconButton(
                onClick = navigateBack,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
            FloatingFavorite(
                isFav = isFav,
                onFavClick = onFavClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 32.dp)
            )
        }
        DetailInformation(
            title = title,
            description = description,
        )
    }
}

@Composable
fun DetailInformation(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(24.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    BeanGreaderTheme {
        DetailContent(
            urlImage = "",
            title = "Arabica",
            description = "Lorem Ipsum",
            isFav = true,
            onFavClick = { /*TODO*/ },
            navigateBack = { /*TODO*/ })
    }
}