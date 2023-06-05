package com.bangkit.capstone.beangreader.data.repository.firebase

import android.content.Intent
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData

interface FireBaseAuth {
    suspend fun signInWithIntent(intent: Intent) : SignInResult
    suspend fun signInWithEmail(email: String, password: String): SignInResult
    suspend fun signUpWithEmail(name: String, email: String, password: String): SignInResult
    suspend fun signOut()
    fun getSignedUser(): UserData?
}