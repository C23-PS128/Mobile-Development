package com.bangkit.capstone.beangreader.data.repository.bean

import com.bangkit.capstone.beangreader.data.local.datastore.SettingPreferencesImpl
import com.bangkit.capstone.beangreader.model.bean.Bean
import com.bangkit.capstone.beangreader.model.bean.BeanData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeanRepositoryImpl @Inject constructor(private val settingPreferences: SettingPreferencesImpl) : BeanRepository {
    private val bean = mutableListOf<Bean>()

    init {
        if (bean.isEmpty()) {
            bean.addAll(BeanData.dummyBean)
        }
    }

    override fun searchBean(query: String) = flow {
        val data = bean.filter {
            it.title.contains(query, ignoreCase = true)
        }
        emit(data)
    }

    override fun getBeanById(id: Int): Flow<Bean> {
        return flowOf(bean.first { it.id == id})
    }
}