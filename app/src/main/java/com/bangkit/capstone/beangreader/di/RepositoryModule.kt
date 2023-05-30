package com.bangkit.capstone.beangreader.di

import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepository
import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepositoryImpl
import com.bangkit.capstone.beangreader.data.repository.setting.SettingRepository
import com.bangkit.capstone.beangreader.data.repository.setting.SettingRepositoryImpl
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
    abstract fun provideSettingRepository(settingRepositoryImpl: SettingRepositoryImpl): SettingRepository
}