package com.bangkit.capstone.beangreader.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import com.bangkit.capstone.beangreader.data.local.room.FavoriteDao

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favDao(): FavoriteDao
}