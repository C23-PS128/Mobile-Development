package com.bangkit.capstone.beangreader.presentation

import androidx.lifecycle.ViewModel
import com.bangkit.capstone.beangreader.domain.repository.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    settingRepository: SettingRepository
) : ViewModel() {
    val isDarkMode = settingRepository.getThemeSetting()
}