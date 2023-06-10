package com.bangkit.capstone.beangreader.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.domain.model.Result
import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BeanRepository) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        addImageBanners()
        getTypes()
        getRoasts()
        getBrews()
        getDrinks()
    }

    private fun getTypes() = viewModelScope.launch {
        repository.getTypes().collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = result.message
                        )
                    }
                }
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            listTypes = result.data
                        )
                    }
                }
            }
        }
    }

    private fun getRoasts() = viewModelScope.launch {
        repository.getRoasts().collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = result.message
                        )
                    }
                }
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            listRoasts = result.data
                        )
                    }
                }
            }
        }
    }

    private fun getBrews() = viewModelScope.launch {
        repository.getBrews().collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            listBrews = result.data
                        )
                    }
                }
            }
        }
    }

    private fun getDrinks() = viewModelScope.launch {
        repository.getDrinks().collect { result ->
            when (result) {
                is Result.Error -> {}
                is Result.Loading -> {}
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            listDrinks = result.data
                        )
                    }
                }
            }
        }
    }

    private fun addImageBanners() {
        _state.update {
            it.copy(
                listBannerImages = listOf(
                    "https://assets.materialup.com/uploads/1784c696-2bc0-45f2-9de1-2461b711ee12/cover.jpg",
                    "https://img.freepik.com/free-vector/coffee-beans-banner-template_23-2148903660.jpg",
                    "https://img.freepik.com/premium-photo/coffee-beans-dark-background-top-view-coffee-concept-banner_1220-6140.jpg"
                )
            )
        }
    }
}