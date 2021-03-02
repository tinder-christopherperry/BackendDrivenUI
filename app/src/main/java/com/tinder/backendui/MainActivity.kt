package com.tinder.backendui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tinder.plugins.ComposableBuilder
import com.tinder.plugins.Content
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var componentProviders: Map<String, @JvmSuppressWildcards ComposableBuilder>
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val state = viewModel.getState()
        if (state is State.Success) {
            setContent {
                Content(state.data)
            }
        }
    }

    @Composable
    private fun Content(response: ApiResponse) {
        LazyColumn(Modifier.background(color = Color.Gray)) {
            items(response.components) { component ->
                when (component.type) {
                    "Carousel" -> {
                        Carousel(
                            content = component.content as Content.CarouselContent,
                            numCarouselItemsShown = NUM_CAROUSEL_ITEMS_SHOWN
                        )
                    }
                    else -> {
                        componentProviders[component.type]!!.BuildComposable(component)
                    }
                }
            }
        }
    }

    @Composable
    private fun Carousel(content: Content.CarouselContent, numCarouselItemsShown: Float) {
        require(numCarouselItemsShown > 0f)
        LazyRow {
            items(content.items) { item ->
                Box(Modifier.fillParentMaxWidth(1f / numCarouselItemsShown)) {
                    componentProviders[item.type]!!.BuildComposable(item)
                }
            }
        }
    }

    companion object {
        private const val NUM_CAROUSEL_ITEMS_SHOWN = 1.25f
    }
}