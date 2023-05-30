package com.bangkit.capstone.beangreader.ui.screen.setting

sealed class SettingEvent {
    data class OnSwitchThemeChange(val isDarkMode: Boolean) : SettingEvent()
}