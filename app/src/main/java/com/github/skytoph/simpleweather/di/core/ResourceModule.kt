package com.github.skytoph.simpleweather.di.core

import android.content.Context
import com.github.skytoph.simpleweather.core.presentation.view.horizon.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {

    @Provides
    fun resourceProvider(@ApplicationContext context: Context): ResourceProvider =
        ResourceProvider.Base(context.resources)
}