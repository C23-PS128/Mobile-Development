package com.bangkit.capstone.beangreader.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    fun getThemeSetting(): Flow<Boolean>

    suspend fun saveThemeSetting(isDarkMode: Boolean)
}