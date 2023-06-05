package com.bangkit.capstone.beangreader

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bangkit.capstone.beangreader.presentation.common.AuthSharedViewModel
import com.bangkit.capstone.beangreader.presentation.model.User
import com.bangkit.capstone.beangreader.presentation.navigation.NavigationItem
import com.bangkit.capstone.beangreader.presentation.navigation.Screen
import com.bangkit.capstone.beangreader.presentation.screen.about.AboutScreen
import com.bangkit.capstone.beangreader.presentation.screen.authentication.login.LoginScreen
import com.bangkit.capstone.beangreader.presentation.screen.authentication.register.RegisterScreen
import com.bangkit.capstone.beangreader.presentation.screen.detail.DetailScreen
import com.bangkit.capstone.beangreader.presentation.screen.favorite.FavoriteScreen
import com.bangkit.capstone.beangreader.presentation.screen.history.HistoryScreen
import com.bangkit.capstone.beangreader.presentation.screen.home.HomeScreen
import com.bangkit.capstone.beangreader.presentation.screen.myprofile.MyProfileScreen
import com.bangkit.capstone.beangreader.presentation.screen.profile.ProfileScreen
import com.bangkit.capstone.beangreader.presentation.screen.scan.ScanScreen
import com.bangkit.capstone.beangreader.presentation.screen.setting.SettingScreen

@Composable
fun BeanGreaderApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            if (currentRoute.shouldShowButtonAppBar()) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "auth-graph",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id, type ->
                        navController.navigate(Screen.Detail.createRoute(id, type))
                    }
                )
            }
            composable(Screen.Scan.route) {
                ScanScreen(
                    onCloseClick = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            navigation(
                startDestination = Screen.Login.route,
                route = "auth-graph"
            ) {
                composable(Screen.Login.route) { entry ->
                    val viewModel = entry.sharedViewModel<AuthSharedViewModel>(navController)
                    LoginScreen(
                        onSignUpClick = {
                            navController.navigate(Screen.Register.route)
                        },
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        },
//                        moveToUserPreference = { userData ->
//                            viewModel.setUser(userData?.toUser() ?: User())
//                        }
                    )
                }
                composable(Screen.Register.route) {
                    RegisterScreen(
                        onRegisterClick = {
                            navController.navigate(Screen.Login.route)
                        },
                        onSigInClick = {
                            navController.navigate(Screen.Login.route)
                        }
                    )
                }
            }
            navigation(
                startDestination = Screen.Profile.route,
                route = "about-graph"
            ) {
                composable(Screen.Setting.route) {
                    val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                    SettingScreen(
                        onLanguageClick = {
                            navController.context.startActivity(intent)
                        },
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
                composable(Screen.Favorite.route) {
                    FavoriteScreen(
                        onBackClick = {
                            navController.navigateUp()
                        },
                        navigateToDetail = { id, type ->
                            navController.navigate(Screen.Detail.createRoute(id, type))
                        }
                    )
                }
                composable(Screen.MyProfile.route) {
                    MyProfileScreen(
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
                composable(Screen.Profile.route) {
                    ProfileScreen(
                        onClickMyProfile = {
                            navController.navigate(Screen.MyProfile.route)
                        },
                        onClickSetting = {
                            navController.navigate(Screen.Setting.route)
                        },
                        onClickFavorite = {
                            navController.navigate(Screen.Favorite.route)
                        },
                        onClickAbout = {
                            navController.navigate(Screen.About.route)
                        }
                    )
                }
                composable(Screen.About.route) {
                    AboutScreen(
                        onClickBack = {
                            navController.navigateUp()
                        }
                    )
                }
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument("beanId") { type = NavType.IntType },
                    navArgument("type") { type = NavType.IntType }
                ),
            ) {
                val id = it.arguments?.getInt("beanId") ?: 1
                val type = it.arguments?.getInt("type") ?: 1
                DetailScreen(
                    id = id,
                    type = type,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(modifier = modifier) {
        val items = listOf(
            NavigationItem(
                title = stringResource(R.string.home),
                icon = Icons.Default.Home,
                contentScreen = Screen.Home,
                contentDescription = "home_page"
            ),
            NavigationItem(
                title = stringResource(R.string.scan),
                icon = Icons.Default.QrCodeScanner,
                contentScreen = Screen.Scan,
                contentDescription = "scan_page"
            ),
            NavigationItem(
                title = stringResource(R.string.history),
                icon = Icons.Default.History,
                contentScreen = Screen.History,
                contentDescription = "history_page"
            ),
            NavigationItem(
                title = stringResource(R.string.profile),
                icon = Icons.Default.Person,
                contentScreen = Screen.Profile,
                contentDescription = "profile_page"
            )
        )
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDescription
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.contentScreen.route,
                    onClick = {
                        navController.navigate(item.contentScreen.route) {
                            popUpTo(navController.graph[Screen.Home.route].id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

private fun String?.shouldShowButtonAppBar(): Boolean {
    return this in setOf(
        Screen.Home.route,
        Screen.History.route,
        Screen.Profile.route
    )
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

