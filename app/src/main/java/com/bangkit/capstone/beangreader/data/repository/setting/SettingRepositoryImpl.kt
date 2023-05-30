package com.bangkit.capstone.beangreader.data.repository.setting

import com.bangkit.capstone.beangreader.data.local.datastore.SettingPreferences
import com.bangkit.capstone.beangreader.data.local.datastore.SettingPreferencesImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepositoryImpl @Inject constructor(
    private val settingPreferences: SettingPreferences,
) : SettingRepository {
    override fun getThemeSetting() = settingPreferences.getThemeSetting()

    override suspend fun saveThemeSetting(isDarkMode: Boolean) = settingPreferences.saveThemeSetting(isDarkMode)
}