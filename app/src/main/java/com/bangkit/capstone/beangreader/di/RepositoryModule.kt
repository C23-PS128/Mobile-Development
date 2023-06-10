package com.bangkit.capstone.beangreader.di

import com.bangkit.capstone.beangreader.data.repository.auth.AuthRepository
import com.bangkit.capstone.beangreader.data.repository.auth.AuthRepositoryImpl
import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepository
import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepositoryImpl
import com.bangkit.capstone.beangreader.data.repository.favorite.FavoriteRepository
import com.bangkit.capstone.beangreader.data.repository.favorite.FavoriteRepositoryImpl
import com.bangkit.capstone.beangreader.data.repository.setting.SettingRepository
import com.bangkit.capstone.beangreader.data.repository.setting.SettingRepositoryImpl
import com.bangkit.capstone.beangreader.data.repository.user.UserRepository
import com.bangkit.capstone.beangreader.data.repository.user.UserRepositoryImpl
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
}