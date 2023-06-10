package com.bangkit.capstone.beangreader.presentation.screen.authentication.forgot

data class ForgotState(
    val email: String = "",
    val error: String? = null,
    val success: Boolean = false,
    val loading: Boolean = false,
)
