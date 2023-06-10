package com.bangkit.capstone.beangreader.presentation.screen.authentication.forgot

sealed class ForgotEvent {
    data class OnEmailChange(val email: String) : ForgotEvent()
    data class SendEmailResetPassword(val email: String) : ForgotEvent()
}
