package com.bangkit.capstone.beangreader.data.repository.user

import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData

interface UserRepository {
    suspend fun getUser(): UserData

    fun logout()
}