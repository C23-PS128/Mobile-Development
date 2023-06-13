package com.bangkit.capstone.beangreader.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.data.local.entity.FavoriteEntity
import com.bangkit.capstone.beangreader.domain.model.Result
import com.bangkit.capstone.beangreader.domain.repository.BeanRepository
import com.bangkit.capstone.beangreader.domain.repository.FavoriteRepository
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
                        type = event.type,
                        title = detailResult.title ?: "",
                        description = detailResult.description,
                        image = detailResult.image
                    )
                    addOrRemoveFromFavorite(event.isFav, favEntity)
                }
            }
            is DetailEvent.GetDetailType -> {
                checkIsFavorite(event.name)
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
            favoriteRepository.deleteFavorite(name = favoriteEntity.title)
        }
    }

    private fun checkIsFavorite(id: String) = viewModelScope.launch {
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
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            detailResult = result.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun getRoastsById(id: Int) = viewModelScope.launch {
        beanRepository.getRoastsById(id).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            detailResult = result.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun getBrewsById(id: Int) = viewModelScope.launch {
        beanRepository.getBrewsById(id).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
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
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            detailResult = result.data
                        )
                    }
                }
            }
        }
    }
}