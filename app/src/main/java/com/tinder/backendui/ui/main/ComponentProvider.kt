package com.tinder.backendui.ui.main

import android.view.View
import com.airbnb.epoxy.EpoxyModel

interface ComponentProvider {
    fun getComponent(component: Component): EpoxyModel<out View>
}

class MessagesSectionComponentProvider: ComponentProvider {

    override fun getComponent(component: Component): EpoxyModel<out View> {
        val messagesSectionContent = component.content as Content.MessagesSectionContent
        return MessagesSectionModel_()
            .id(component.id)
            .sectionTitle(messagesSectionContent.sectionTitle)
            .messages(messagesSectionContent.messages)
    }

}

class HighlightCardComponentProvider: ComponentProvider {

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