package com.bangkit.capstone.beangreader.presentation.screen.myprofile

import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData

data class MyProfileState(
    val userData: UserData? = null,
    val signInResult: SignInResult? = null,
    val isSuccess: Boolean = false,
    val isError: String? = null,
    val isLoading: Boolean = false
)