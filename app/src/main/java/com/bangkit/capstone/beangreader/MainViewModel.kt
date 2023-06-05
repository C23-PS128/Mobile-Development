package com.bangkit.capstone.beangreader

import androidx.lifecycle.ViewModel
import com.bangkit.capstone.beangreader.data.repository.setting.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModel() {
    val isDarkMode = settingRepository.getThemeSetting()
}