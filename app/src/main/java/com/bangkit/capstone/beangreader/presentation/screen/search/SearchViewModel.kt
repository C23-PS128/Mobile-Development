package com.bangkit.capstone.beangreader.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.capstone.beangreader.domain.model.Result
import com.bangkit.capstone.beangreader.domain.repository.BeanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: BeanRepository) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    init {
        searchType("")
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChange -> {
                _state.update {
                    it.copy(
                        query = event.query
                    )
                }
                searchType(event.query)
            }
        }
    }

    private fun searchType(newQuery: String) = viewModelScope.launch {
        repository.searchBean(newQuery).collect { result ->
            when (result) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = result.message
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
                            searchItem = result.data
                        )
                    }
                }
            }
        }
    }
}