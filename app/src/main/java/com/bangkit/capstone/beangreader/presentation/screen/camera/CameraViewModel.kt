package com.bangkit.capstone.beangreader.presentation.screen.camera

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.domain.model.Result
import com.bangkit.capstone.beangreader.domain.repository.DetectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val detectionRepository: DetectionRepository
): ViewModel() {

    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    fun uploadImage(image: File) = viewModelScope.launch {
        Log.d("CEK", "uploadImage: $image")
        detectionRepository.uploadImage(image).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = result.message
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
                            result = result.data,
                            success = true
                        )
                    }
                }
            }
        }
    }

    fun resetState() {
        _state.update {
            CameraState()
        }
    }
}