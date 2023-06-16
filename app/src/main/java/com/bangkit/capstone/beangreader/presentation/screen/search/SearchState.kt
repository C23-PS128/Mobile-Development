package com.bangkit.capstone.beangreader.presentation.screen.search

import com.bangkit.capstone.beangreader.data.remote.response.article.ResultsItem

data class SearchState(
    val query: String = "",
    val searchItem: List<ResultsItem> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: String? = null
)
