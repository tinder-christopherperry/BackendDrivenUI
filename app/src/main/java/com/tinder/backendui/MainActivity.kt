package com.tinder.backendui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.mvrx.InternalMavericksApi
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.tinder.plugins.Component
import com.tinder.plugins.ComposableBuilder
import com.tinder.plugins.Content
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var componentProviders: Map<String, @JvmSuppressWildcards ComposableBuilder>

    @InternalMavericksApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Content() }
    }

    @InternalMavericksApi
    @Composable
    private fun Content() {
        val viewModel: MainViewModel = mavericksViewModel()
        val state = viewModel.collectState()

        when (val components = state.components) {
            is Loading -> {}
            is Success -> ScreenContent(components())
        }
    }

    @Composable
    private fun ScreenContent(components: List<Component>) {
        LazyColumn(Modifier.background(color = Color.Gray)) {
            items(components) { component ->
                when (component.type) {
                    "Carousel" -> {
                        Carousel(
                            content = component.content as Content.CarouselContent
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
    private fun Carousel(content: Content.CarouselContent) {
        LazyRow {
            items(content.items) { item ->
                Box(Modifier.fillParentMaxWidth(1f / NUM_CAROUSEL_ITEMS_SHOWN)) {
                    componentProviders[item.type]!!.BuildComposable(item)
                }
            }
        }
    }

    companion object {
        private const val NUM_CAROUSEL_ITEMS_SHOWN = 1.25f
    }
}