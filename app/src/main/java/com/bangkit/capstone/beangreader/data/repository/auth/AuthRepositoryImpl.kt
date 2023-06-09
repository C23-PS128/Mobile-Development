package com.bangkit.capstone.beangreader.data.repository.auth

import android.content.Intent
import com.bangkit.capstone.beangreader.data.repository.firebase.FireBaseAuth
import com.bangkit.capstone.beangreader.domain.model.Result
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FireBaseAuth
) : AuthRepository {

    override fun login(emails: String, password: String) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.signInWithEmail(emails, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun register(
        name: String,
        emails: String,
        password: String
    ): Flow<Result<SignInResult>> = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.signUpWithEmail(name, emails, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun signInWithIntent(intent: Intent) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.signInWithIntent(intent)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getSignedUser() = flow {
        val result = firebaseAuth.getSignedUser()
        emit(result)
    }.flowOn(Dispatchers.IO)

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}