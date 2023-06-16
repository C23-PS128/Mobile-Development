package com.bangkit.capstone.beangreader.presentation.screen.camera

import com.bangkit.capstone.beangreader.data.remote.response.detection.DetectionResponse

data class CameraState(
    val error: String? = null,
    val loading: Boolean = false,
    val success: Boolean = false,
    val result: DetectionResponse? = null
)