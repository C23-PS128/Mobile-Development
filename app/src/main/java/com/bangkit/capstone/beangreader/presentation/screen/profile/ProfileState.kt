package com.bangkit.capstone.beangreader.presentation.screen.profile

import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData

data class ProfileState(
    val userData: UserData? = null,
    val signInResult: SignInResult? = null
)
