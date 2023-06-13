package com.bangkit.capstone.beangreader.presentation.screen.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    init {
        getTypeFavorite()
        getRoastFavorite()
        getBrewFavorite()
        getDrinkFavorite()
    }

    private fun getTypeFavorite() = viewModelScope.launch {
        favoriteRepository.getAllFavorite(0).collect { result ->
            _state.update {
                it.copy(
                    favoriteTypeEntity = result
                )
            }
        }
    }

    private fun getRoastFavorite() = viewModelScope.launch {
        favoriteRepository.getAllFavorite(1).collect { result ->
            Log.d("Favorite1", "getAllFavorite: $result")
            _state.update {
                it.copy(
                    favoriteRoastEntity = result
                )
            }
        }
    }

    private fun getBrewFavorite() = viewModelScope.launch {
        favoriteRepository.getAllFavorite(2).collect { result ->
            Log.d("Favorite2", "getAllFavorite: $result")
            _state.update {
                it.copy(
                    favoriteBrewEntity = result
                )
            }
        }
    }

    private fun getDrinkFavorite() = viewModelScope.launch {
        favoriteRepository.getAllFavorite(3).collect { result ->
            Log.d("Favorite3", "getAllFavorite: $result")
            _state.update {
                it.copy(
                    favoriteDrinkEntity = result
                )
            }
        }
    }
}


