package com.bangkit.capstone.beangreader.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import com.bangkit.capstone.beangreader.presentation.screen.profile.component.ListMenu

@Composable
fun ProfileScreen(
    onClickMyProfile: () -> Unit,
    onClickSetting: () -> Unit,
    onClickFavorite: () -> Unit,
    onClickAbout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileContent(
        onClickMyProfile = onClickMyProfile,
        onClickSetting = onClickSetting,
        onClickFavorite = onClickFavorite,
        onClickAbout = onClickAbout,
        userData = state.userData
    )
}

@Composable
fun ProfileContent(
    onClickMyProfile: () -> Unit,
    onClickSetting: () -> Unit,
    onClickFavorite: () -> Unit,
    onClickAbout: () -> Unit,
    userData: UserData?,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        if (userData?.profilePicture != null) {
            AsyncImage(
                model = userData.profilePicture,
                contentDescription = stringResource(R.string.profile_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .clip(CircleShape)
                    .size(128.dp)
            )
        } else {
            AsyncImage(
                model = "https://i.pinimg.com/280x280_RS/73/01/55/7301553dbeb6f667ad47fa206d26ce82.jpg",
                contentDescription = stringResource(R.string.profile_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 16.dp)
                    .clip(CircleShape)
                    .size(128.dp)
            )
        }
        if (userData?.username != null) {
            Text(
                text = userData.username,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                softWrap = true,
            )
        }
        ListMenuProfile(
            onClickMyProfile = onClickMyProfile,
            onClickFavorite = onClickFavorite,
            onClickSetting = onClickSetting,
            onClickAbout = onClickAbout
        )
    }
}

@Composable
fun ListMenuProfile(
    modifier: Modifier = Modifier,
    onClickMyProfile: () -> Unit,
    onClickFavorite: () -> Unit,
    onClickSetting: () -> Unit,
    onClickAbout: () -> Unit,
) {
    Column(modifier = modifier.padding(vertical = 32.dp)) {
        ListMenu(
            text = stringResource(R.string.my_profile),
            imageVector = Icons.Outlined.Person,
            contentDescription = stringResource(R.string.icon_person),
            onCLick = onClickMyProfile
        )
        ListMenu(
            text = stringResource(R.string.favorite),
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = stringResource(R.string.favorite),
            onCLick = onClickFavorite
        )
        ListMenu(
            text = stringResource(R.string.setting),
            imageVector = Icons.Outlined.Settings,
            contentDescription = stringResource(R.string.icon_setting),
            onCLick = onClickSetting
        )
        ListMenu(
            onCLick = onClickAbout,
            text = stringResource(R.string.about),
            imageVector = Icons.Outlined.Info,
            contentDescription = "Icon About"
        )
    }
}