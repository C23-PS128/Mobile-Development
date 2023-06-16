package com.bangkit.capstone.beangreader.presentation.screen.authentication.login

import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isConnectLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val signInResult: SignInResult? = null,
    val isError: String? = null
)