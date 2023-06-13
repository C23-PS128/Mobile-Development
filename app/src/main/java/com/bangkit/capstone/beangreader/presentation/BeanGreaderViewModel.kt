package com.bangkit.capstone.beangreader.presentation

import androidx.lifecycle.ViewModel
import com.bangkit.capstone.beangreader.data.remote.response.detection.DetectionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BeanGreaderViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(DetectionResponse())
    val state = _state.asStateFlow()
}