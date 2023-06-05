package com.bangkit.capstone.beangreader.data.repository.auth

import android.content.Intent
import com.bangkit.capstone.beangreader.data.repository.Result
import com.bangkit.capstone.beangreader.data.repository.firebase.FireBaseAuth
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FireBaseAuth
) : AuthRepository {

    override fun login(email: String, password: String) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.signInWithEmail(email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun register(
        name: String,
        email: String,
        password: String
    ) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.signUpWithEmail(name, email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }

    override fun signInWithIntent(intent: Intent) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.signInWithIntent(intent)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }

    override fun getSignedUser(): Flow<Result<UserData?>> = flow {
        emit(Result.Loading())
        try {
            val result = firebaseAuth.getSignedUser()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}