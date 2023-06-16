package com.bangkit.capstone.beangreader.domain.repository

import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData

interface UserRepository {
    suspend fun getUser(): UserData

    fun logout()
}