package com.bangkit.capstone.beangreader.presentation.screen.home

import com.bangkit.capstone.beangreader.data.remote.response.article.BrewsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.DrinksItem
import com.bangkit.capstone.beangreader.data.remote.response.article.RoastsItem
import com.bangkit.capstone.beangreader.data.remote.response.article.TypeCoffeeItem

data class HomeState(
    val listTypes: List<TypeCoffeeItem> = emptyList(),
    val listRoasts: List<RoastsItem> = emptyList(),
    val listBrews: List<BrewsItem> = emptyList(),
    val listDrinks: List<DrinksItem> = emptyList(),
    val listBannerImages: List<String> = emptyList(),
    val query: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)