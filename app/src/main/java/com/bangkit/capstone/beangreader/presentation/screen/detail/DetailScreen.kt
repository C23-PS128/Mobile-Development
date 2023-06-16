package com.bangkit.capstone.beangreader.presentation.screen.detail

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.component.DetailShimmerScreen
import com.bangkit.capstone.beangreader.presentation.screen.detail.component.FloatingFavorite

@Composable
fun DetailScreen(
    id: Int,
    type: Int,
    name: String,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(DetailEvent.GetDetailType(id, name, type))
    }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        state.error?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    if (state.isLoading) {
        DetailShimmerScreen()
    } else {
        state.detailResult?.let {
            DetailContent(
                urlImage = "${it.image}",
                title = "${it.title}",
                description = "${it.description}",
                isFav = state.isFav,
                onFavClick = {
                    viewModel.onEvent(
                        DetailEvent.OnFavClick(
                            !state.isFav,
                            type,
                            state.detailResult
                        )
                    )
                },
                navigateBack = navigateBack
            )
        }
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
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
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
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White),
                modifier = Modifier
                    .padding(start = 4.dp, top = 8.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_button),
                    tint = Color.Black
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
