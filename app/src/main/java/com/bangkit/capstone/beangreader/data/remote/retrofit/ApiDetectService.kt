package com.bangkit.capstone.beangreader.data.remote.retrofit

import com.bangkit.capstone.beangreader.data.remote.response.detection.DetectionResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiDetectService {

    @Multipart
    @POST("predict")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ) : DetectionResponse
}