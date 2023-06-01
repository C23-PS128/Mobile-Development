package com.bangkit.capstone.beangreader.presentation.screen.detail

sealed class DetailEvent {
    data class OnFavClick(val isFav: Boolean): DetailEvent()
    data class GetDetail(val id: Int) : DetailEvent()
}