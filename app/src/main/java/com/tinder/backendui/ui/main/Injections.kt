package com.tinder.backendui.ui.main

import com.tinder.plugins.ComponentProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
@InstallIn(ActivityComponent::class)
class InjectionModule {
    @Provides
    @IntoMap
    @StringKey("MessagesSection")
    fun provideMessagesSection() : ComponentProvider {
        return MessagesSectionComponentProvider()
    }
}