package com.bangkit.capstone.beangreader.di

import com.bangkit.capstone.beangreader.domain.repository.AuthRepository
import com.bangkit.capstone.beangreader.data.repository.AuthRepositoryImpl
import com.bangkit.capstone.beangreader.domain.repository.BeanRepository
import com.bangkit.capstone.beangreader.data.repository.BeanRepositoryImpl
import com.bangkit.capstone.beangreader.data.repository.DetectionRepositoryImpl
import com.bangkit.capstone.beangreader.domain.repository.FavoriteRepository
import com.bangkit.capstone.beangreader.data.repository.FavoriteRepositoryImpl
import com.bangkit.capstone.beangreader.domain.repository.SettingRepository
import com.bangkit.capstone.beangreader.data.repository.SettingRepositoryImpl
import com.bangkit.capstone.beangreader.domain.repository.UserRepository
import com.bangkit.capstone.beangreader.data.repository.UserRepositoryImpl
import com.bangkit.capstone.beangreader.domain.repository.DetectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideBeanRepository(beanRepositoryImpl: BeanRepositoryImpl): BeanRepository

    @Binds
    @Singleton
    abstract fun provideFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    @Singleton
    abstract fun provideSettingRepository(settingRepositoryImpl: SettingRepositoryImpl): SettingRepository

    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun provideDetectRepository(detectionRepositoryImpl: DetectionRepositoryImpl): DetectionRepository
}