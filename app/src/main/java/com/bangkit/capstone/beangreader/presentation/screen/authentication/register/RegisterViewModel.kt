package com.bangkit.capstone.beangreader.presentation.screen.authentication.register

import android.content.Intent
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
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is RegisterEvent.OnNameChange -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }
            is RegisterEvent.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }
            is RegisterEvent.OnRegisterClick -> {
                register(_state.value.name, _state.value.email, _state.value.password)
            }

            is RegisterEvent.SignInGoogleWithIntent -> {
                signInWithIntent(event.intent)
            }
        }
    }

    private fun register(name: String, email: String, password: String) = viewModelScope.launch {
        authRepository.register(name, email, password).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isError = result.message,
                            isLoading = false
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
                        it.copy(
                            registerSuccess = true,
                            signInResult = result.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun signInWithIntent(intent: Intent) = viewModelScope.launch {
        authRepository.signInWithIntent(intent).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isError = result.message,
                            isConnectLoading = false
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isConnectLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            signInResult = result.data,
                            isSuccess = true,
                            isConnectLoading = false
                        )
                    }
                }
            }
        }
    }
}