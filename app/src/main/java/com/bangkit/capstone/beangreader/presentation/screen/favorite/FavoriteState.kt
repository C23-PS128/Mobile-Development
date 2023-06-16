package com.bangkit.capstone.beangreader.presentation.screen.favorite

import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity

data class FavoriteState(
    val favoriteTypeEntity: List<FavoriteEntity> = emptyList(),
    val favoriteRoastEntity: List<FavoriteEntity> = emptyList(),
    val favoriteBrewEntity: List<FavoriteEntity> = emptyList(),
    val favoriteDrinkEntity: List<FavoriteEntity> = emptyList()
)