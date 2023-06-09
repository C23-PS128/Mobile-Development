package com.bangkit.capstone.beangreader.presentation

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QrCode
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bangkit.capstone.beangreader.R
import com.bangkit.capstone.beangreader.data.remote.response.detection.DetectionResponse
import com.bangkit.capstone.beangreader.presentation.navigation.NavigationItem
import com.bangkit.capstone.beangreader.presentation.navigation.Screen
import com.bangkit.capstone.beangreader.presentation.screen.about.AboutScreen
import com.bangkit.capstone.beangreader.presentation.screen.authentication.forgot.ForgotScreen
import com.bangkit.capstone.beangreader.presentation.screen.authentication.login.LoginScreen
import com.bangkit.capstone.beangreader.presentation.screen.authentication.register.RegisterScreen
import com.bangkit.capstone.beangreader.presentation.screen.camera.CameraScreen
import com.bangkit.capstone.beangreader.presentation.screen.detail.DetailScreen
import com.bangkit.capstone.beangreader.presentation.screen.detectresult.DetectResultScreen
import com.bangkit.capstone.beangreader.presentation.screen.favorite.FavoriteScreen
import com.bangkit.capstone.beangreader.presentation.screen.home.HomeScreen
import com.bangkit.capstone.beangreader.presentation.screen.myprofile.MyProfileScreen
import com.bangkit.capstone.beangreader.presentation.screen.onboarding.OnBoardingScreen
import com.bangkit.capstone.beangreader.presentation.screen.profile.ProfileScreen
import com.bangkit.capstone.beangreader.presentation.screen.scan.ScanScreen
import com.bangkit.capstone.beangreader.presentation.screen.search.SearchScreen
import com.bangkit.capstone.beangreader.presentation.screen.setting.SettingScreen
import com.bangkit.capstone.beangreader.presentation.screen.splash.SplashScreen
import com.bangkit.capstone.beangreader.presentation.screen.upload.UploadScreen

@Composable
fun BeanGreaderApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute.shouldShowButtonAppBar()) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash-screen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("splash-screen") {
                SplashScreen(
                    onTimeOut = { isLoggedIn ->
                        if (isLoggedIn) {
                            navController.navigate(Screen.Home.route) {
                                popUpTo("splash-screen") {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate("auth-graph") {
                                popUpTo("splash-screen") {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToSearch = {
                        navController.navigate(Screen.Search.route)
                    },
                    navigateToDetail = { id, title, type ->
                        navController.navigate(Screen.Detail.createRoute(id, title, type))
                    },
                    moveToScan = {
                        navController.navigate("scan-graph") {
                            popUpTo(navController.graph[Screen.Home.route].id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    onClickBack = {
                        navController.navigateUp()
                    },
                    navigateToDetail = { id, type, title ->
                        navController.navigate(Screen.Detail.createRoute(id, title, type))
                    }
                )
            }
            navigation(
                startDestination = "on-boarding",
                route = "auth-graph"
            ) {
                composable("on-boarding") {
                    OnBoardingScreen(
                        moveToLogin = {
                            navController.navigate(Screen.Login.route)
                        }
                    )
                }
                composable(Screen.Login.route) {
                    LoginScreen(
                        onSignUpClick = {
                            navController.navigate(Screen.Register.route)
                        },
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo("auth-graph") {
                                    inclusive = true
                                }
                            }
                        },
                        moveToForgot = {
                            navController.navigate(Screen.Forgot.route) {
                                popUpTo(Screen.Forgot.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
                composable(Screen.Forgot.route) {
                    ForgotScreen(
                        moveToLogin = {
                            navController.navigateUp()
                        }
                    )
                }
                composable(Screen.Register.route) {
                    RegisterScreen(
                        navigateToLogin = {
                            navController.navigate(Screen.Login.route)
                        },
                        navigateToHome = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo("auth-graph") {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
            navigation(
                startDestination = Screen.Scan.route,
                route = "scan-graph"
            ) {
                composable(Screen.Scan.route) {
                    ScanScreen(
                        moveToCamera = {
                            navController.navigate(Screen.Camera.route)
                        }
                    )
                }
                composable(Screen.Camera.route) {
                    CameraScreen(
                        onCloseClick = {
                            navController.navigateUp()
                        },
                        moveToResult = { detectionResponse ->
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "result",
                                detectionResponse
                            )
                            navController.navigate(Screen.DetectionResult.route)
                        }
                    )
                }
                composable(Screen.Upload.route) {
                    UploadScreen()
                }
                composable(
                    route = Screen.DetectionResult.route,
                ) {
                    val state =
                        navController.previousBackStackEntry?.savedStateHandle?.get<DetectionResponse>(
                            "result"
                        )
                    DetectResultScreen(
                        image = state?.userImage ?: "",
                        moisture = state?.labels ?: "",
                        description = state?.pesanKualitas ?: "",
                        onBackPressed = {
                            navController.navigateUp()
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
                        navigateToDetail = { id, title, type ->
                            navController.navigate(Screen.Detail.createRoute(id, title, type))
                        }
                    )
                }
                composable(Screen.MyProfile.route) {
                    MyProfileScreen(
                        onBackClick = {
                            navController.navigateUp()
                        },
                        navigateToLogin = {
                            navController.navigate(Screen.Login.route) {
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                }
                            }
                        },
                        navigateToSignUp = {
                            navController.navigate(Screen.Register.route) {
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                }
                            }
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
                    navArgument("type") { type = NavType.IntType },
                    navArgument("name") { type = NavType.StringType }
                ),
            ) {
                val id = it.arguments?.getInt("beanId") ?: 1
                val type = it.arguments?.getInt("type") ?: 1
                val name = it.arguments?.getString("name") ?: ""
                DetailScreen(
                    id = id,
                    type = type,
                    name = name,
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
                iconActive = Icons.Default.Home,
                iconNonActive = Icons.Outlined.Home,
                contentScreen = Screen.Home,
                contentDescription = "home_page",
            ),
            NavigationItem(
                title = stringResource(R.string.scan),
                iconActive = Icons.Default.QrCode,
                iconNonActive = Icons.Outlined.QrCode,
                contentScreen = Screen.Scan,
                contentDescription = "scan_page"
            ),
            NavigationItem(
                title = stringResource(R.string.favorite),
                iconActive = Icons.Default.Favorite,
                iconNonActive = Icons.Default.FavoriteBorder,
                contentScreen = Screen.Favorite,
                contentDescription = "history_page"
            ),
            NavigationItem(
                title = stringResource(R.string.profile),
                iconActive = Icons.Default.Person,
                iconNonActive = Icons.Outlined.Person,
                contentScreen = Screen.Profile,
                contentDescription = "profile_page"
            )
        )
        NavigationBar {
            items.forEach { item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = if (currentRoute == item.contentScreen.route) {
                                item.iconActive
                            } else {
                                item.iconNonActive
                            },
                            contentDescription = item.contentDescription,
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
        Screen.Scan.route,
        Screen.Favorite.route,
        Screen.Profile.route,
    )
}

