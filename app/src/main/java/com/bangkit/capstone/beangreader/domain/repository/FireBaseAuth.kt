package com.bangkit.capstone.beangreader.domain.repository

import android.content.Intent
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData

interface FireBaseAuth {
    suspend fun signInWithIntent(intent: Intent) : SignInResult
    suspend fun signInWithEmail(emails: String, password: String): SignInResult
    suspend fun signUpWithEmail(name: String, emails: String, password: String): SignInResult
    suspend fun sendPasswordResetEmail(email: String)
    suspend fun revokeAccess()
    suspend fun signOut()
    suspend fun getSignedUser(): UserData?
}