package com.bangkit.capstone.beangreader.presentation.screen.myprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(MyProfileState())
    val state = _state.asStateFlow()

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        authRepository.getSignedUser().collect { result ->
            _state.update {
                it.copy(
                    userData = result
                )
            }
        }
    }

    fun logout() = viewModelScope.launch {
        authRepository.signOut().let {
            _state.update {
                it.copy(
                    isSuccess = true
                )
            }
        }
    }
}