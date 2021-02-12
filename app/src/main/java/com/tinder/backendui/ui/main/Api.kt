package com.tinder.backendui.ui.main

sealed class State<out T> {
    data class Success<T>(val data: T) : State<T>()
    object Loading
}

data class Component(
    val id: String,
    val type: String,
    val content: Content
)

sealed class Content {
    data class CarouselContent(val items: List<Content>) : Content()

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
        val messages: List<MessagesSection.Message>
    ) : Content()
}

data class ApiResponse(
    val components: List<Component>
)