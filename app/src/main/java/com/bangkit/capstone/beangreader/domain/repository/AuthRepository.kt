package com.bangkit.capstone.beangreader.domain.repository

import android.content.Intent
import com.bangkit.capstone.beangreader.domain.model.Result
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(emails: String, password: String): Flow<Result<SignInResult>>
    fun register(name: String, emails: String, password: String): Flow<Result<SignInResult>>
    fun signInWithIntent(intent: Intent): Flow<Result<SignInResult>>
    fun sendPasswordResetEmail(emails: String): Flow<Result<Boolean>>
    fun getSignedUser(): Flow<UserData?>
    suspend fun signOut()
}