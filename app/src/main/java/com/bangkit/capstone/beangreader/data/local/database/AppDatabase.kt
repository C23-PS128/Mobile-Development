package com.bangkit.capstone.beangreader.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import com.bangkit.capstone.beangreader.data.local.room.FavoriteDao

@Database(entities = [FavoriteEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favDao(): FavoriteDao
}