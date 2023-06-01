package com.bangkit.capstone.beangreader.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.profile.component.ListMenu
import com.bangkit.capstone.beangreader.presentation.screen.profile.component.LogoutButton

@Composable
fun ProfileScreen(
    onClickMyProfile: () -> Unit,
    onClickSetting: () -> Unit,
    onClickFavorite: () -> Unit
) {
    ProfileContent(
        onClickMyProfile = onClickMyProfile,
        onClickSetting = onClickSetting,
        onClickFavorite = onClickFavorite
    )
}

@Composable
fun ProfileContent(
    onClickMyProfile: () -> Unit,
    onClickSetting: () -> Unit,
    onClickFavorite: () -> Unit,
    modifier: Modifier = Modifier
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = "https://pbs.twimg.com/media/FjU2lkcWYAgNG6d.jpg",
            contentDescription = stringResource(R.string.profile_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .clip(CircleShape)
                .size(148.dp)
        )
        Text(
            text = "Jhon Wick",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "jhonwick@gmail.com",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ListMenuProfile(
            onClickMyProfile = onClickMyProfile,
            onClickFavorite = onClickFavorite,
            onClickSetting = onClickSetting
        )
        LogoutButton(
            onClick = {}
        )
    }
}

@Composable
fun ListMenuProfile(
    modifier: Modifier = Modifier,
    onClickMyProfile: () -> Unit,
    onClickFavorite: () -> Unit,
    onClickSetting: () -> Unit
) {
    Column(modifier = modifier.padding(vertical = 16.dp)) {
        ListMenu(
            text = { Text(stringResource(R.string.my_profile)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = stringResource(R.string.icon_person)
                )
            },
            onCLick = onClickMyProfile
        )
        ListMenu(
            text = { Text(stringResource(R.string.favorite)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorite)
                )
            },
            onCLick = onClickFavorite
        )
        ListMenu(
            text = { Text(stringResource(R.string.setting)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = stringResource(R.string.icon_setting)
                )
            },
            onCLick = onClickSetting
        )
    }
}