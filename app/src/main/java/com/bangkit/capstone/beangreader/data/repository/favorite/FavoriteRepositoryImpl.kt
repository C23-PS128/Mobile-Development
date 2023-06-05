package com.bangkit.capstone.beangreader.data.repository.favorite

import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import com.bangkit.capstone.beangreader.data.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun isFavorite(id: Int): Flow<Boolean> = favoriteDao.isFavorite(id)

    override suspend fun insertFavorite(fav: FavoriteEntity) = favoriteDao.insert(fav)

    override suspend fun updateFavorite(fav: FavoriteEntity) = favoriteDao.updateFavorite(fav)

    override fun getAllFavorite(): Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorite()

    override suspend fun deleteFavorite(id: Int) = favoriteDao.delete(id)
}