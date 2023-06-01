package com.bangkit.capstone.beangreader.presentation.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Register: Screen("register")
    object Forgot : Screen("forgot")
    object Home: Screen("home")
    object Scan: Screen("scan")
    object History: Screen("history")
    object Profile: Screen("profile")
    object Setting: Screen("setting")
    object Favorite: Screen("favorite")
    object MyProfile: Screen("my_profile")
    object Detail : Screen("home/{beanId}") {
        fun createRoute(beanId: Int) = "home/$beanId"
    }
}
