package com.bangkit.capstone.beangreader.data.repository.favorite

import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import com.bangkit.capstone.beangreader.data.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun isFavorite(id: Int): Flow<Boolean>

    suspend fun insertFavorite(fav: FavoriteEntity)

    suspend fun updateFavorite(fav: FavoriteEntity)

    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    suspend fun deleteFavorite(id: Int)
}