package com.bangkit.capstone.beangreader.data.repository

sealed class Result<out R> {
    data class Loading<out T>(val data: T? = null) : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String? = null) : Result<Nothing>()
}