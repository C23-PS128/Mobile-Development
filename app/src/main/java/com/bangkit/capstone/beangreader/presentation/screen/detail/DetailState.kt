package com.bangkit.capstone.beangreader.presentation.screen.detail

import com.bangkit.capstone.beangreader.model.bean.Bean

data class DetailState(
    val bean: Bean? = null,
    val id: Int = 0,
    val isFav: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)