package com.bangkit.capstone.beangreader.presentation.screen.setting

sealed class SettingEvent {
    data class OnSwitchThemeChange(val isDarkMode: Boolean) : SettingEvent()
}