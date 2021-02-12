package com.tinder.backendui.ui.main

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TextRow(context: Context): AppCompatTextView(context) {

    @TextProp
    fun setTextContent(text: CharSequence?) {
        this.text = text
    }
}