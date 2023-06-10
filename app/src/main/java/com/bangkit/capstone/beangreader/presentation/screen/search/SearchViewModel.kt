package com.bangkit.capstone.beangreader.presentation.screen.search

import android.util.Log
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
        repository.searchBean(newQuery)
            .catch { e ->
                _state.update {
                    it.copy(
                        isError = e.message.toString()
                    )
                }
            }
            .collect { result ->
                Log.d("Search", "searchType: $result")
                _state.update {
                    it.copy(
                        searchItem = result
                    )
                }
            }
    }
}