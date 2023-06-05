package com.bangkit.capstone.beangreader.presentation.screen.favorite

import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity

data class FavoriteState(
    val favoriteEntity: List<FavoriteEntity> = emptyList(),
)