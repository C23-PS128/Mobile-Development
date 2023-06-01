package com.bangkit.capstone.beangreader.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: BeanRepository) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.OnFavClick -> {}
            is DetailEvent.GetDetail -> {
                getBeanById(event.id)
            }
        }
    }

    private fun getBeanById(id: Int) = viewModelScope.launch {
        repository.getBeanById(id)
            .catch { e ->
                _state.update {
                    it.copy(
                        error = e.message.toString()
                    )
                }
            }
            .collect { beans ->
                _state.update {
                    it.copy(
                        bean = beans
                    )
                }
            }
    }

    fun isFavorite(id: Int) = repository.isFavorite(id)

    fun deleteFavorite(id: Int) = viewModelScope.launch {
        repository.deleteFavorite(id)
    }
}