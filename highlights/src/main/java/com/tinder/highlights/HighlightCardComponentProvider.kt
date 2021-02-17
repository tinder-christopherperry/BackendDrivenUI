package com.tinder.highlights

import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.tinder.plugins.Component
import com.tinder.plugins.ComponentProvider
import com.tinder.plugins.Content

internal class HighlightCardComponentProvider: ComponentProvider {

    override fun getComponent(component: Component): EpoxyModel<out View> {
        val content = component.content as Content.HighlightCardContent
        return HighlightCardModel_()
            .id(component.id)
            .leftText(content.leftText)
            .topRightText(content.topRightText)
            .midRightText(content.midRightText)
            .buttonText(content.buttonText)
    }

}