package com.bangkit.capstone.beangreader.di

import android.content.Context
import androidx.room.Room
import com.bangkit.capstone.beangreader.data.local.database.AppDatabase
import com.bangkit.capstone.beangreader.data.local.room.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun appDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesFavoriteDao(appDatabase: AppDatabase) : FavoriteDao {
        return appDatabase.favDao()
    }
}