package com.bangkit.capstone.beangreader.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepository
import com.bangkit.capstone.beangreader.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: BeanRepository) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        searchBean("")
        addImageBanners()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnQueryChange -> {
                _state.update {
                    it.copy(
                        query = event.query
                    )
                }
                searchBean(event.query)
            }
        }
    }

    private fun searchBean(newQuery: String) = viewModelScope.launch {
        repository.searchBean(newQuery)
            .catch { e ->
                _state.update {
                    it.copy(
                        errorMessage = e.message.toString()
                    )
                }
            }
            .collect { beans ->
                Log.d("BEANSSS", "searchBean: $newQuery | $beans")
                _state.update {
                    it.copy(
                        listBean = beans
                    )
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