package com.bangkit.capstone.beangreader.data.repository

import android.util.Log
import com.bangkit.capstone.beangreader.data.remote.RemoteDataSource
import com.bangkit.capstone.beangreader.data.remote.response.article.BrewsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.DetailItem
import com.bangkit.capstone.beangreader.data.remote.response.article.DrinksItem
import com.bangkit.capstone.beangreader.data.remote.response.article.ResultsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.RoastsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.TypeCoffeeItem
import com.bangkit.capstone.beangreader.domain.model.Result
import com.bangkit.capstone.beangreader.domain.repository.BeanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeanRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BeanRepository {

    override fun searchBean(query: String): Flow<List<ResultsItem>> = flow {
        val result = remoteDataSource.getSearch(query).results.filter {
            Log.d("Searching1", "searchBean: $it")
            it.title?.contains(query, ignoreCase = true) ?: true
        }
        emit(result)
    }

    override fun getTypes(): Flow<Result<List<TypeCoffeeItem>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getTypes()
            emit(Result.Success(response.result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getTypesById(id: Int): Flow<Result<DetailItem>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getDetailTypes(id)
            emit(Result.Success(response.result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getRoasts(): Flow<Result<List<RoastsItem>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getRoasts()
            emit(Result.Success(response.result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getRoastsById(id: Int): Flow<Result<DetailItem>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getDetailRoast(id)
            emit(Result.Success(response.result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getBrews(): Flow<Result<List<BrewsItem>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getBrews()
            emit(Result.Success(response.result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getBrewsById(id: Int): Flow<Result<DetailItem>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getDetailBrews(id)
            emit(Result.Success(response.result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getDrinks(): Flow<Result<List<DrinksItem>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getDrinks()
            emit(Result.Success(response.result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getDrinksById(id: Int): Flow<Result<DetailItem>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getDetailDrinks(id)
            emit(Result.Success(response.result))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}