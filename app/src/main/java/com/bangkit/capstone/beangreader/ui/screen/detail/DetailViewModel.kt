package com.bangkit.capstone.beangreader.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.data.repository.bean.BeanRepository
import com.bangkit.capstone.beangreader.model.bean.Bean
import com.bangkit.capstone.beangreader.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: BeanRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Bean>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Bean>> get() = _uiState

    fun getBeanById(id: Int) = viewModelScope.launch {
        repository.getBeanById(id)
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }
}