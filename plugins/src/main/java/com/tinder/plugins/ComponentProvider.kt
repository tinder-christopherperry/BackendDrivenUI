package com.tinder.plugins

import android.view.View
import com.airbnb.epoxy.EpoxyModel

interface ComponentProvider {
    fun getComponent(component: Component): EpoxyModel<out View>
}