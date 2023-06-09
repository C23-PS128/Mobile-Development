package com.bangkit.capstone.beangreader.data.remote.retrofit

import com.bangkit.capstone.beangreader.data.remote.response.article.BrewsResponse
import com.bangkit.capstone.beangreader.data.remote.response.article.DetailCoffeesResponse
import com.bangkit.capstone.beangreader.data.remote.response.article.DrinksResponse
import com.bangkit.capstone.beangreader.data.remote.response.article.RoastsResponse
import com.bangkit.capstone.beangreader.data.remote.response.article.SearchResponse
import com.bangkit.capstone.beangreader.data.remote.response.article.TypeCoffeeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("coffee/typecoffee")
    suspend fun getTypes() : TypeCoffeeResponse

    @GET("coffee/typecoffee/{id}")
    suspend fun getTypesById(
        @Path("id") id: Int
    ) : DetailCoffeesResponse

    @GET("coffee/typeroast")
    suspend fun getRoasts() : RoastsResponse

    @GET("coffee/typeroast/{id}")
    suspend fun getRoastsById(
        @Path("id") id: Int
    ) : DetailCoffeesResponse

    @GET("coffee/typebrew")
    suspend fun getBrews() : BrewsResponse

    @GET("coffee/typebrew/{id}")
    suspend fun getBrewsById(
        @Path("id") id: Int
    ) : DetailCoffeesResponse

    @GET("coffee/typecoffeedrink")
    suspend fun getDrinks() : DrinksResponse

    @GET("coffee/typecoffeedrink/{id}")
    suspend fun getDrinksById(
        @Path("id") id: Int
    ) : DetailCoffeesResponse

    @GET("search")
    suspend fun search(
        @Query("query") query: String
    ) : SearchResponse
}