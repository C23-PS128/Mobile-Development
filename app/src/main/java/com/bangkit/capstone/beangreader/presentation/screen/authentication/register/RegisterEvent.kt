package com.bangkit.capstone.beangreader.presentation.screen.authentication.register

import android.content.Intent

sealed class RegisterEvent {
    data class OnNameChange(val name: String) : RegisterEvent()
    data class OnEmailChange(val email: String) : RegisterEvent()
    data class OnPasswordChange(val password: String) : RegisterEvent()
    data class OnRegisterClick(
        val name: String,
        val email: String,
        val password: String
    ) : RegisterEvent()
    data class SignInGoogleWithIntent(val intent: Intent) : RegisterEvent()
}
