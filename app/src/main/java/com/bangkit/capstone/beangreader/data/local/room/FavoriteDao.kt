package com.bangkit.capstone.beangreader.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fav: FavoriteEntity)

    @Update
    suspend fun updateFavorite(fav: FavoriteEntity)

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT EXISTS (SELECT * FROM favorite WHERE id = :id)")
    fun isFavorite(id: Int): Flow<Boolean>
}