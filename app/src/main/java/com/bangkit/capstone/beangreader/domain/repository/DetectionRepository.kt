package com.bangkit.capstone.beangreader.domain.repository

import com.bangkit.capstone.beangreader.data.remote.response.detection.DetectionResponse
import com.bangkit.capstone.beangreader.domain.model.Result
import kotlinx.coroutines.flow.Flow
import java.io.File

interface DetectionRepository {
    fun uploadImage(image: File): Flow<Result<DetectionResponse>>
}