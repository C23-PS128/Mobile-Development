package com.bangkit.capstone.beangreader.ui.screen.home

sealed class HomeEvent {
    data class OnQueryChange (val query: String): HomeEvent()
}