package com.bangkit.capstone.beangreader.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fav: FavoriteEntity)

    @Insert
    suspend fun updateFavorite(fav: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE title = :name")
    suspend fun delete(name: String)

    @Query("SELECT * FROM favorite WHERE type = :type")
    fun getAllFavorite(type: Int): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS (SELECT * FROM favorite WHERE title = :name)")
    fun isFavorite(name: String): Flow<Boolean>
}