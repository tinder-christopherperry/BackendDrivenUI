package com.tinder.plugins

data class Component(
    val id: String,
    val type: String,
    val content: Content
)

sealed class Content {
    data class CarouselContent(val items: List<Component>) : Content()

    data class TextRowContent(val text: String) : Content()

    data class ButtonRowContent(val text: String) : Content()

    data class HighlightCardContent(
        val leftText: String,
        val topRightText: String,
        val midRightText: String,
        val buttonText: String
    ) : Content()

    data class MessagesSectionContent(
        val sectionTitle: String,
        val messages: List<Message>
    ) : Content()
}

data class Message(val title: String, val subtitle: String)