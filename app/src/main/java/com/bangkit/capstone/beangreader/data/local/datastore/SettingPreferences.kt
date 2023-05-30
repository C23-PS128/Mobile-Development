package com.bangkit.capstone.beangreader.data.local.datastore

import kotlinx.coroutines.flow.Flow

interface SettingPreferences {
    fun getThemeSetting(): Flow<Boolean>

    suspend fun saveThemeSetting(isDarkMode: Boolean)
}