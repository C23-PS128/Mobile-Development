package com.bangkit.capstone.beangreader.presentation.screen.authentication.forgot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.domain.repository.AuthRepository
import com.bangkit.capstone.beangreader.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _state = MutableStateFlow(ForgotState())
    val state = _state.asStateFlow()

    fun onEvent(event: ForgotEvent) {
        when (event) {
            is ForgotEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }

            is ForgotEvent.SendEmailResetPassword -> {
                sendPasswordResetEmail(event.email)
            }
        }
    }

    private fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        authRepository.sendPasswordResetEmail(email).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message,
                            loading = false,
                            success = false
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            success = result.data,
                        )
                    }
                }
            }
        }
    }
}