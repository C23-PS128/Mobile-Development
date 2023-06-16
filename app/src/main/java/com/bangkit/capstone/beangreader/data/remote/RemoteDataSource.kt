package com.bangkit.capstone.beangreader.data.remote

import com.bangkit.capstone.beangreader.data.remote.response.detection.DetectionResponse
import com.bangkit.capstone.beangreader.data.remote.retrofit.ApiDetectService
import com.bangkit.capstone.beangreader.data.remote.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val apiDetectService: ApiDetectService
) {
    suspend fun getTypes() = apiService.getTypes()
    suspend fun getDetailTypes(id: Int) = apiService.getTypesById(id)

    suspend fun getRoasts() = apiService.getRoasts()
    suspend fun getDetailRoast(id: Int) = apiService.getRoastsById(id)

    suspend fun getBrews() = apiService.getBrews()
    suspend fun getDetailBrews(id: Int) = apiService.getBrewsById(id)

    suspend fun getDrinks() = apiService.getDrinks()
    suspend fun getDetailDrinks(id: Int) = apiService.getDrinksById(id)

    suspend fun getSearch(query: String) = apiService.search(query)

    suspend fun uploadImage(image: File): DetectionResponse {
        val requestImageFile = image.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultiPart = MultipartBody.Part.createFormData(
            "file",
            image.name,
            requestImageFile
        )
        return apiDetectService.uploadImage(imageMultiPart)
    }
}