package com.bangkit.capstone.beangreader.presentation.common

import androidx.lifecycle.ViewModel
import com.bangkit.capstone.beangreader.presentation.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthSharedViewModel @Inject constructor(): ViewModel() {

    private val _user = MutableStateFlow(User())
    val user = _user.asStateFlow()

    fun setUser(user: User) {
        _user.value = user
    }
}