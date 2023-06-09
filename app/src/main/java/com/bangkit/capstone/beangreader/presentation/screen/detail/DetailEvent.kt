package com.bangkit.capstone.beangreader.presentation.screen.detail

import com.bangkit.capstone.beangreader.data.remote.response.article.DetailItem

sealed class DetailEvent {
    data class OnFavClick(val isFav: Boolean, val type: Int, val detailResult: DetailItem?): DetailEvent()
    data class GetDetailType(val id: Int, val name: String, val type: Int) : DetailEvent()
}