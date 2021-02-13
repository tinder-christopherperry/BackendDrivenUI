package com.tinder.backendui.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun getState(): State<ApiResponse> {
        val response = ApiResponse(
            listOf(
                carousel(1),
                messagesSection(2, "Pinned Messages"),
                messagesSection(3, "Your Turn"),
                messagesSection(4, "Archive")
            )
        )

        return State.Success(response)
    }

    private fun carousel(id: Int): Component {
        return Component(
            id = "$id",
            type = "Carousel",
            content = Content.CarouselContent(
                items = listOf(
                    Component(
                        id = "highlightcard1",
                        type = "HighlightCard",
                        content = Content.HighlightCardContent(
                            leftText = "Jenn, 29",
                            topRightText = "IN COMMON",
                            midRightText = "You and Jenn both go to USC",
                            buttonText = "Message"
                        )
                    ),
                    Component(
                        id = "highlightcard1",
                        type = "HighlightCard",
                        content = Content.HighlightCardContent(
                            leftText = "Michelle, 40",
                            topRightText = "IN COMMON",
                            midRightText = "Michelle also loves SUSHI",
                            buttonText = "Message"
                        )
                    ),
                    Component(
                        id = "highlightcard1",
                        type = "HighlightCard",
                        content = Content.HighlightCardContent(
                            leftText = "Christina, 35",
                            topRightText = "IN COMMON",
                            midRightText = "Christina likes DOGS",
                            buttonText = "Message"
                        )
                    )
                )
            )
        )
    }

    private fun messagesSection(id: Int, title: String): Component {
        return Component(
            id = "$id",
            type = "MessagesSection",
            content = Content.MessagesSectionContent(
                sectionTitle = title,
                messages = listOf(
                    MessagesSection.Message(
                        title = "Robert",
                        subtitle = "Give me a scotch. I’m starving."
                    ),
                    MessagesSection.Message(
                        title = "Iron Man",
                        subtitle = "Better clench up, Legolas."
                    ),
                    MessagesSection.Message(
                        title = "RDJ",
                        subtitle = "I told you. I don’t want to join your super secret boy band."
                    )
                )
            )
        )
    }
}