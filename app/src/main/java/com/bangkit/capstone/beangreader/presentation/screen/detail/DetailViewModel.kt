package com.bangkit.capstone.beangreader.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import com.bangkit.capstone.beangreader.data.repository.Result
import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepository
import com.bangkit.capstone.beangreader.data.repository.favorite.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val beanRepository: BeanRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnFavClick -> {
                if (event.detailResult != null) {
                    val detailResult = event.detailResult
                    val favEntity = FavoriteEntity(
                        id = detailResult.id,
                        title = detailResult.title,
                        description = detailResult.description,
                        image = detailResult.image
                    )
                    addOrRemoveFromFavorite(event.isFav, favEntity)
                }
            }
            is DetailEvent.GetDetailType -> {
                checkIsFavorite(event.id)
                when (event.type) {
                    0 -> getTypesById(event.id)
                    1 -> getRoastsById(event.id)
                    2 -> getBrewsById(event.id)
                    3 -> getDrinksById(event.id)
                }
            }
        }
    }

    private fun addOrRemoveFromFavorite(isFav: Boolean, favoriteEntity: FavoriteEntity) = viewModelScope.launch {
        if (isFav) {
            favoriteRepository.insertFavorite(favoriteEntity)
        } else {
            favoriteRepository.deleteFavorite(id = favoriteEntity.id)
        }
    }

    private fun checkIsFavorite(id: Int) = viewModelScope.launch {
        favoriteRepository.isFavorite(id).collect { isFav ->
            _state.update {
                it.copy(
                    isFav = isFav
                )
            }
        }
    }

    private fun getTypesById(id: Int) = viewModelScope.launch {
        beanRepository.getTypesById(id).collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            detailResult = result.data
                        )
                    }
                }
            }
        }
    }

    private fun getRoastsById(id: Int) = viewModelScope.launch {
        beanRepository.getRoastsById(id).collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            detailResult = result.data
                        )
                    }
                }
            }
        }
    }

    private fun getBrewsById(id: Int) = viewModelScope.launch {
        beanRepository.getBrewsById(id).collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            detailResult = result.data
                        )
                    }
                }
            }
        }
    }

    private fun getDrinksById(id: Int) = viewModelScope.launch {
        beanRepository.getDrinksById(id).collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            detailResult = result.data
                        )
                    }
                }
            }
        }
    }
}