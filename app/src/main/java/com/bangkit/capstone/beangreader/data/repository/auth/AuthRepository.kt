package com.bangkit.capstone.beangreader.data.repository.auth

import android.content.Intent
import com.bangkit.capstone.beangreader.data.repository.Result
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<Result<SignInResult>>
    fun register(name: String, email: String, password: String): Flow<Result<SignInResult>>
    fun signInWithIntent(intent: Intent): Flow<Result<SignInResult>>
    fun getSignedUser(): Flow<Result<UserData?>>
    suspend fun signOut()
}