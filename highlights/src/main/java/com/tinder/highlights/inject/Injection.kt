package com.tinder.highlights.inject

import com.tinder.highlights.HighlightCardComposableBuilder
import com.tinder.plugins.ComposableBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(ActivityComponent::class)
class HighlightsModule {

    @Provides
    @IntoMap
    @StringKey("HighlightCard")
    fun provideHighlightCard() : ComposableBuilder {
        return HighlightCardComposableBuilder()
    }
}