package com.bangkit.capstone.beangreader

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QrCodeScanner
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
import com.bangkit.capstone.beangreader.ui.navigation.NavigationItem
import com.bangkit.capstone.beangreader.ui.navigation.Screen
import com.bangkit.capstone.beangreader.ui.screen.authentication.LoginScreen
import com.bangkit.capstone.beangreader.ui.screen.authentication.RegisterScreen
import com.bangkit.capstone.beangreader.ui.screen.detail.DetailScreen
import com.bangkit.capstone.beangreader.ui.screen.history.HistoryScreen
import com.bangkit.capstone.beangreader.ui.screen.home.HomeScreen
import com.bangkit.capstone.beangreader.ui.screen.profile.ProfileScreen
import com.bangkit.capstone.beangreader.ui.screen.scan.ScanScreen
import com.bangkit.capstone.beangreader.ui.screen.setting.SettingScreen

@Composable
fun BeanGreaderApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute.shouldShowButtonAppBar()) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginClick = {
                        navController.navigate(Screen.Home.route)
                    },
                    onSignUpClick = {
                        navController.navigate(Screen.Register.route)
                    }
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
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { beansId ->
                        navController.navigate(Screen.Detail.createRoute(beansId))
                    }
                )
            }
            composable(Screen.Scan.route) {
                ScanScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onClickSetting = {
                        navController.navigate(Screen.Setting.route)
                    }
                )
            }
            composable(Screen.Setting.route) {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                SettingScreen(
                    onLanguageClick = {
                        navController.context.startActivity(intent)
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("beanId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("beanId") ?: 1
                DetailScreen(
                    id = id,
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
               icon = Icons.Outlined.Home,
               contentScreen = Screen.Home,
               contentDescription = "home_page"
           ),
           NavigationItem(
               title = stringResource(R.string.scan),
               icon = Icons.Outlined.QrCodeScanner,
               contentScreen = Screen.Scan,
               contentDescription = "scan_page"
           ),
           NavigationItem(
               title = stringResource(R.string.history),
               icon = Icons.Outlined.History,
               contentScreen = Screen.History,
               contentDescription = "history_page"
           ),
           NavigationItem(
               title = stringResource(R.string.profile),
               icon = Icons.Outlined.Person,
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
        Screen.Scan.route,
        Screen.History.route,
        Screen.Profile.route
    )
}

