package com.bangkit.capstone.beangreader.presentation.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Register: Screen("register")
    object Forgot : Screen("forgot")
    object Home: Screen("home")
    object Search: Screen("search")
    object Scan: Screen("scan")
    object History: Screen("history")
    object Profile: Screen("profile")
    object Setting: Screen("setting")
    object Favorite: Screen("favorite")
    object MyProfile: Screen("my_profile")
    object About: Screen("about")
    object Camera: Screen("camera")
    object DetectionResult: Screen("detection-result")
    object Upload: Screen("upload")
    object Detail : Screen("home/{beanId}/{type}/{name}") {
        fun createRoute(beanId: Int, name: String, type: Int) = "home/$beanId/$type/$name"
    }
}
