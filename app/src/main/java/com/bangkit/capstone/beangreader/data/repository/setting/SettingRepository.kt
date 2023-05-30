package com.bangkit.capstone.beangreader.data.repository.setting

import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    fun getThemeSetting(): Flow<Boolean>

    suspend fun saveThemeSetting(isDarkMode: Boolean)
}