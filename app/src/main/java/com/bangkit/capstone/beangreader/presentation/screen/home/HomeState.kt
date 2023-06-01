package com.bangkit.capstone.beangreader.presentation.screen.home

import com.bangkit.capstone.beangreader.model.bean.Bean

data class HomeState(
    val listBean: List<Bean> = emptyList(),
    val listBannerImages: List<String> = emptyList(),
    val query: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)