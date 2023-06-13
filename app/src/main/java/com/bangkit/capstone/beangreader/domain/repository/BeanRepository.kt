package com.bangkit.capstone.beangreader.domain.repository

import com.bangkit.capstone.beangreader.data.remote.response.article.BrewsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.DetailItem
import com.bangkit.capstone.beangreader.data.remote.response.article.DrinksItem
import com.bangkit.capstone.beangreader.data.remote.response.article.ResultsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.RoastsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.TypeCoffeeItem
import com.bangkit.capstone.beangreader.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface BeanRepository {
    fun searchBean(query: String): Flow<List<ResultsItem>>

    fun getTypes(): Flow<Result<List<TypeCoffeeItem>>>

    fun getTypesById(id: Int): Flow<Result<DetailItem>>

    fun getRoasts(): Flow<Result<List<RoastsItem>>>

    fun getRoastsById(id: Int): Flow<Result<DetailItem>>

    fun getBrews(): Flow<Result<List<BrewsItem>>>

    fun getBrewsById(id: Int): Flow<Result<DetailItem>>

    fun getDrinks(): Flow<Result<List<DrinksItem>>>

    fun getDrinksById(id: Int): Flow<Result<DetailItem>>
}