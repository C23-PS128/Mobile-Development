package com.bangkit.capstone.beangreader.presentation.screen.myprofile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import com.bangkit.capstone.beangreader.presentation.screen.myprofile.component.ListMenuItem

@Composable
fun MyProfileScreen(
    onBackClick: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: MyProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.isSuccess) {
        if (state.isSuccess) {
            navigateToLogin()
            Toast.makeText(context, context.getString(R.string.logout_success), Toast.LENGTH_SHORT)
                .show()
        }
    }

    MyProfileContent(
        onBackClick = onBackClick,
        userData = state.userData,
        onLogoutClick = {
            viewModel.logout()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileContent(
    userData: UserData?,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var openMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.my_profile)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        openMenu = !openMenu
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "menu"
                        )
                        DropdownMenu(
                            expanded = openMenu,
                            onDismissRequest = {
                                openMenu = !openMenu
                            }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = stringResource(R.string.logout),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                },
                                onClick = onLogoutClick
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = stringResource(R.string.delete_account),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                },
                                onClick = { /*TODO*/ }
                            )
                        }
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
            if (userData?.profilePicture != null) {
                AsyncImage(
                    model = userData.profilePicture,
                    contentDescription = stringResource(R.string.profile_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                        .size(144.dp)
                )
            } else {
                AsyncImage(
                    model = "https://i.pinimg.com/280x280_RS/73/01/55/7301553dbeb6f667ad47fa206d26ce82.jpg",
                    contentDescription = stringResource(R.string.profile_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 24.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(CircleShape)
                        .size(144.dp)
                )
            }
            ListMenuItem(
                overLineContent = {
                    Text(
                        text = stringResource(R.string.my_name),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                },
                headlineContent = {
                    if (userData?.username != null) {
                        Text(
                            text = userData.username,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
            )
            ListMenuItem(
                overLineContent = {
                    Text(
                        text = stringResource(R.string.my_email),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                },
                headlineContent = {
                    if (userData?.email != null) {
                        Text(
                            text = userData.email,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                },
            )
        }
    }
}