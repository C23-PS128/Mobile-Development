package com.bangkit.capstone.beangreader.data.remote

import com.bangkit.capstone.beangreader.data.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getTypes() = apiService.getTypes()
    suspend fun getDetailTypes(id: Int) = apiService.getTypesById(id)

    suspend fun getRoasts() = apiService.getRoasts()
    suspend fun getDetailRoast(id: Int) = apiService.getRoastsById(id)

    suspend fun getBrews() = apiService.getBrews()
    suspend fun getDetailBrews(id: Int) = apiService.getBrewsById(id)

    suspend fun getDrinks() = apiService.getDrinks()
    suspend fun getDetailDrinks(id: Int) = apiService.getDrinksById(id)
}