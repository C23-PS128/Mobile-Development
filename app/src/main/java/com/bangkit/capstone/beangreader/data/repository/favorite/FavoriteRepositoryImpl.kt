package com.bangkit.capstone.beangreader.data.repository.favorite

import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import com.bangkit.capstone.beangreader.data.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun isFavorite(name: String): Flow<Boolean> = favoriteDao.isFavorite(name)

    override suspend fun insertFavorite(fav: FavoriteEntity) = favoriteDao.insert(fav)

    override suspend fun updateFavorite(fav: FavoriteEntity) = favoriteDao.updateFavorite(fav)

    override fun getAllFavorite(type: Int): Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorite(type)

    override suspend fun deleteFavorite(name: String) = favoriteDao.delete(name)
}