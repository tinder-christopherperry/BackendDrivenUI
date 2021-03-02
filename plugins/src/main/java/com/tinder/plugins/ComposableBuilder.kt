package com.tinder.plugins

import androidx.compose.runtime.Composable

interface ComposableBuilder {

    @Composable
    fun BuildComposable(component: Component)
}