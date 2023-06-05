package com.bangkit.capstone.beangreader.presentation.screen.authentication.register

sealed class RegisterEvent {
    data class OnNameChange(val name: String) : RegisterEvent()
    data class OnEmailChange(val email: String) : RegisterEvent()
    data class OnPasswordChange(val password: String) : RegisterEvent()
    object OnRegisterClick : RegisterEvent()
}
