package com.bangkit.capstone.beangreader.presentation.screen.authentication.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.data.repository.auth.AuthRepository
import com.bangkit.capstone.beangreader.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.IsLoginClick -> {
                login(_state.value.email, _state.value.password)
            }

            is LoginEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }

            is LoginEvent.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }

            is LoginEvent.SignInGoogleWithIntent -> {
                signInWithIntent(event.intent)
            }

            LoginEvent.ResetState -> {
                _state.update {
                    LoginState()
                }
            }
        }
    }

    private fun login(emails: String, password: String) = viewModelScope.launch {
        repository.login(emails, password).collect { result ->
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
                            signInResult = result.data,
                            isSuccess = true,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun signInWithIntent(intent: Intent) {
        viewModelScope.launch {
            repository.signInWithIntent(intent).collect { result ->
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
}