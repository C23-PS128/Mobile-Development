package com.bangkit.capstone.beangreader.data.repository

import com.bangkit.capstone.beangreader.domain.repository.UserRepository
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    UserRepository {
    override suspend fun getUser(): UserData {
        val firebaseUser = firebaseAuth.currentUser ?: throw Exception("User not Found")
        return UserData(
            firebaseUser.uid,
            firebaseUser.displayName,
            firebaseUser.email,
            firebaseUser.photoUrl?.toString()
        )
    }

    override fun logout() = firebaseAuth.signOut()
}