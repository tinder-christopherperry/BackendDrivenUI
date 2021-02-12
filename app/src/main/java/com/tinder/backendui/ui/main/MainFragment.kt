package com.tinder.backendui.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.*
import com.tinder.backendui.R
import java.lang.Exception

/**
 * Displays a Component Based Server Driven UI.
 *
 * Server tells us which components to display, in what order, and with what content.
 *
 * The component JSON:
 * {
 *   id: 12345
 *   type: MyComponent
 *   content {
 *      title: "SomeTitle"
 *      subtitle: "SomeSubTitle"
 *   }
 * }
 */
class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: EpoxyRecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = view!!.findViewById(R.id.recyclerView)

        val state = viewModel.getState()
        if (state is State.Success) {
            render(state.data)
        }
    }

    private fun render(response: ApiResponse) {
        recyclerView.withModels {

            response.components.forEachIndexed { index, component ->
                when (component.type) {
                    "Carousel" -> {
                        carousel {
                            id("carousel$index")
                            numViewsToShowOnScreen(1.5f)
                            models(carouselModels(component.content as Content.CarouselContent))
                        }
                    }

                    "TextRow" -> {
                        val textRowContent = component.content as Content.TextRowContent
                        textRow {
                            id("textRow$index")
                            textContent(textRowContent.text)
                        }
                    }

                    "MessagesSection" -> {
                        val messagesSectionContent = component.content as Content.MessagesSectionContent
                        messagesSection {
                            id("messagesSection$index")
                            sectionTitle(messagesSectionContent.sectionTitle)
                            messages(messagesSectionContent.messages)
                        }
                    }
                }
            }
        }
    }

    private fun carouselModels(content: Content.CarouselContent): List<EpoxyModel<out View>> {
        return content.items.mapIndexed { index, item ->
            when (item) {
                is Content.HighlightCardContent -> highlightCardModel(index, item)
                else -> throw Exception("Unsupported view type in carousel")
            }
        }
    }

    private fun highlightCardModel(index: Int, content: Content.HighlightCardContent): HighlightCardModel_ {
        return HighlightCardModel_()
            .id("HighlightCard$index")
            .leftText(content.leftText)
            .topRightText(content.topRightText)
            .midRightText(content.midRightText)
            .buttonText(content.buttonText)
    }
}

