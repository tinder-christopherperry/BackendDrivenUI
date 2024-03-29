package com.tinder.messages.inject

import com.tinder.messages.MessagesSectionComposableBuilder
import com.tinder.plugins.ComposableBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(ActivityComponent::class)
class MessagesModule {

    @Provides
    @IntoMap
    @StringKey("MessagesSection")
    fun provideMessagesSection() : ComposableBuilder {
        return MessagesSectionComposableBuilder()
    }
}