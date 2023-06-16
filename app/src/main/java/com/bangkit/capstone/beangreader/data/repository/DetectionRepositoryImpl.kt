package com.bangkit.capstone.beangreader.data.repository

import com.bangkit.capstone.beangreader.data.remote.RemoteDataSource
import com.bangkit.capstone.beangreader.data.remote.response.detection.DetectionResponse
import com.bangkit.capstone.beangreader.domain.model.Result
import com.bangkit.capstone.beangreader.domain.repository.DetectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetectionRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : DetectionRepository {
    override fun uploadImage(image: File): Flow<Result<DetectionResponse>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.uploadImage(image)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}