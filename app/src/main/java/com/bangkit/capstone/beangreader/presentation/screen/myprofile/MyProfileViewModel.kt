package com.bangkit.capstone.beangreader.presentation.screen.myprofile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.domain.model.Result
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

    var isDialogShown by mutableStateOf(false)
        private set

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
                    isLogoutSuccess = true
                )
            }
        }
    }

    fun revokeAccess() = viewModelScope.launch {
        authRepository.revokeAccess().collect { result ->
            when (result) {
                is Result.Error -> {
                    Log.d("RevokeError", "revokeAccess: ${result.message}")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        Log.d("RevokeSuccess", "revokeAccess: ${result.data}")
                        it.copy(
                            isLoading = false,
                            isRevokeSuccess = result.data
                        )
                    }
                }
            }
        }
    }

    fun resetState() {
        _state.update {
            MyProfileState()
        }
    }

    fun isDialogShown() {
        isDialogShown = true
    }

    fun isDialogCancel() {
        isDialogShown = false
    }
}