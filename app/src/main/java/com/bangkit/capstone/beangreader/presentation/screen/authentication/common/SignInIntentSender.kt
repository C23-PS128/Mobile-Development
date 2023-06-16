package com.bangkit.capstone.beangreader.presentation.screen.authentication.common

import android.content.Context
import android.content.IntentSender
import com.bangkit.capstone.beangreader.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.tasks.await

suspend fun signInIntentSender(context: Context): IntentSender ? {
    return try {
        val oneTapClient = Identity.getSignInClient(context)
        val result = oneTapClient.beginSignIn(
            buildSignInRequest(context)
        ).await()
        result?.pendingIntent?.intentSender
    } catch (e: Exception) {
        null
    }
}

fun buildSignInRequest(context: Context): BeginSignInRequest {
    return BeginSignInRequest.Builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id))
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()
}
