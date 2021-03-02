package com.tinder.backendui

import com.tinder.plugins.Component

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    object Loading
}

data class ApiResponse(
    val components: List<Component>
)