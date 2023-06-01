package com.bangkit.capstone.beangreader.data.repository.bean

import com.bangkit.capstone.beangreader.model.bean.Bean
import kotlinx.coroutines.flow.Flow

interface BeanRepository {
    fun searchBean(query: String): Flow<List<Bean>>

    fun getBeanById(id: Int): Flow<Bean>

    fun isFavorite(id: Int)

    suspend fun deleteFavorite(id: Int)
}