package com.bangkit.capstone.beangreader.presentation.screen.authentication.register

import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val signInResult: SignInResult? = null,
    val isLoading: Boolean = false,
    val isConnectLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val registerSuccess: Boolean = false,
    val isError: String? = null,
)