package com.tinder.messages

import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.tinder.plugins.Component
import com.tinder.plugins.ComponentProvider
import com.tinder.plugins.Content

internal class MessagesSectionComponentProvider: ComponentProvider {

    override fun getComponent(component: Component): EpoxyModel<out View> {
        val messagesSectionContent = component.content as Content.MessagesSectionContent
        return MessagesSectionModel_()
            .id(component.id)
            .sectionTitle(messagesSectionContent.sectionTitle)
            .messages(messagesSectionContent.messages)
    }

}