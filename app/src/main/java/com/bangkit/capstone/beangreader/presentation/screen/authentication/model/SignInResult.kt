package com.bangkit.capstone.beangreader.presentation.screen.authentication.model

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val email: String?,
    val phone: String?,
    val profilePicture: String?,
    val isNewUser: Boolean = false
)
