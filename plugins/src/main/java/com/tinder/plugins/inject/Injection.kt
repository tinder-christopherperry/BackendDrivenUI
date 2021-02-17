package com.tinder.plugins.inject

import com.tinder.plugins.ComponentProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.Multibinds

@Module
@InstallIn(ActivityComponent::class)
abstract class PluginsModule {

    @Multibinds
    abstract fun componentProviders() : Map<String, @JvmSuppressWildcards ComponentProvider>
}