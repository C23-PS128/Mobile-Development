package com.bangkit.capstone.beangreader.domain.repository

import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun isFavorite(name: String): Flow<Boolean>

    suspend fun insertFavorite(fav: FavoriteEntity)

    suspend fun updateFavorite(fav: FavoriteEntity)

    fun getAllFavorite(type: Int): Flow<List<FavoriteEntity>>

    suspend fun deleteFavorite(name: String)
}