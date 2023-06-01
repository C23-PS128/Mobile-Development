package com.bangkit.capstone.beangreader.presentation.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.data.repository.setting.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val settingRepository: SettingRepository) : ViewModel() {

    private val _state = MutableStateFlow(SettingState())
    val state = _state.asStateFlow()

    init {
        getThemeSetting()
    }

    fun onEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.OnSwitchThemeChange -> {
                _state.update {
                    it.copy(
                        isDarkMode = event.isDarkMode
                    )
                }
                saveThemeSetting(event.isDarkMode)
            }
        }
    }

    private fun getThemeSetting() {
        viewModelScope.launch {
            settingRepository.getThemeSetting().collect { isDarkMode ->
                _state.update {
                    it.copy(
                        isDarkMode = isDarkMode
                    )
                }
            }
        }
    }

    private fun saveThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch {
            settingRepository.saveThemeSetting(isDarkMode)
        }
    }
}