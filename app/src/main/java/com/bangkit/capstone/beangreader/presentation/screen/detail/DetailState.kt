package com.bangkit.capstone.beangreader.presentation.screen.detail

import com.bangkit.capstone.beangreader.data.remote.response.article.DetailItem

data class DetailState(
    val detailResult: DetailItem? = null,
    val id: Int = 0,
    val isFav: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)