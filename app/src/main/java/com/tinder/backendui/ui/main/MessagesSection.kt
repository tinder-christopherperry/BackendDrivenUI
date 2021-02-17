package com.tinder.backendui.ui.main

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.tinder.backendui.R
import com.tinder.plugins.Message

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class MessagesSection(context: Context): FrameLayout(context) {

    private var sectionTitle: TextView
    private var messageOne: View
    private var messageTwo: View
    private var messageThree: View

    init {
        inflate(context, R.layout.section_messages, this)
        sectionTitle = findViewById(R.id.sectionTitle)
        messageOne = findViewById(R.id.messageOne)
        messageTwo = findViewById(R.id.messageTwo)
        messageThree = findViewById(R.id.messageThree)
    }

    @TextProp
    fun setSectionTitle(title: CharSequence) {
        sectionTitle.text = title
    }

    @ModelProp
    fun setMessages(messages: List<Message>) {

        // I know, don't judge me it's a demo!
        // This is totally contrived
        messages.forEachIndexed { index, message ->
            if (index == 0) {
                messageOne.findViewById<TextView>(R.id.title).text = message.title
                messageOne.findViewById<TextView>(R.id.subtitle).text = message.subtitle
            }

            if (index == 1) {
                messageTwo.findViewById<TextView>(R.id.title).text = message.title
                messageTwo.findViewById<TextView>(R.id.subtitle).text = message.subtitle
            }

            if (index == 2) {
                messageThree.findViewById<TextView>(R.id.title).text = message.title
                messageThree.findViewById<TextView>(R.id.subtitle).text = message.subtitle
            }
        }
    }
}