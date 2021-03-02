package com.tinder.backendui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import com.tinder.plugins.ComposableBuilder
import com.tinder.plugins.Content
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var componentProviders: Map<String, @JvmSuppressWildcards ComposableBuilder>
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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
                        Carousel(component.content as Content.CarouselContent)
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
                componentProviders[item.type]!!.BuildComposable(item)
            }
        }
    }
}