package com.bangkit.capstone.beangreader.presentation.screen.search

sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()
}
