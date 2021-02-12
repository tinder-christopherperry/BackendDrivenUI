package com.tinder.backendui.ui.main

import android.content.Context
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.tinder.backendui.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HighlightCard(context: Context): FrameLayout(context) {

    private var leftText: TextView
    private var topRightText: TextView
    private var midRightText: TextView
    private var button: Button

    init {
        inflate(context, R.layout.card_highlight, this)
        leftText  = findViewById(R.id.leftText)
        topRightText = findViewById(R.id.topRightText)
        midRightText = findViewById(R.id.midRightText)
        button = findViewById(R.id.button)
    }

    @TextProp
    fun setLeftText(text: CharSequence) {
        leftText.text = text
    }

    @TextProp
    fun setTopRightText(text: CharSequence) {
        topRightText.text = text
    }

    @TextProp
    fun setMidRightText(text: CharSequence) {
        midRightText.text = text
    }

    @TextProp
    fun setButtonText(text: CharSequence) {
        button.text = text
    }
}