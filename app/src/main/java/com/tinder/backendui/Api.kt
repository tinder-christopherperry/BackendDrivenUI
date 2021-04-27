package com.tinder.backendui

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.tinder.plugins.Component

data class MainState(
    val components: Async<List<Component>> = Uninitialized
): MavericksState