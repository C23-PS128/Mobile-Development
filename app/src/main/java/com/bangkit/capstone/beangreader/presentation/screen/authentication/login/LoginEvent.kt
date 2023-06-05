package com.bangkit.capstone.beangreader.presentation.screen.authentication.login

import android.content.Intent

sealed class LoginEvent {
    data class OnEmailChange(val email: String) : LoginEvent()
    data class OnPasswordChange(val password: String) : LoginEvent()
    data class IsLoginClick(val email: String, val password: String) : LoginEvent()
    data class SignInGoogleWithIntent(val intent: Intent) : LoginEvent()
    object ResetState : LoginEvent()
}