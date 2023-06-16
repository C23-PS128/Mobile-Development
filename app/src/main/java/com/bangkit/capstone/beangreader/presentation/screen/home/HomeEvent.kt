package com.bangkit.capstone.beangreader.presentation.screen.home

sealed class HomeEvent {
    data class OnQueryChange (val query: String): HomeEvent()
}