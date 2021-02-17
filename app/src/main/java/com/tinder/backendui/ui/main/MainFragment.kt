package com.tinder.backendui.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.epoxy.carousel
import com.tinder.backendui.R
import com.tinder.plugins.ComponentProvider
import com.tinder.plugins.Content
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var componentProviders: Map<String, @JvmSuppressWildcards ComponentProvider>
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: EpoxyRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView = requireView().findViewById(R.id.recyclerView)

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

                    else -> add(componentProviders[component.type]!!.getComponent(component))
                }
            }
        }
    }

    private fun carouselModels(content: Content.CarouselContent): List<EpoxyModel<out View>> {
        return content.items.map {
            componentProviders[it.type]!!.getComponent(it)
        }
    }
}

