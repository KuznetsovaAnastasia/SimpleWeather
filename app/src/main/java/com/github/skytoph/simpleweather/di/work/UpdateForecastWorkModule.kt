package com.github.skytoph.simpleweather.di.work

import android.content.Context
import androidx.work.WorkManager
import com.github.skytoph.simpleweather.domain.work.UpdateForecastWork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object UpdateForecastWorkModule {

    @Provides
    fun periodically(@ApplicationContext context: Context): UpdateForecastWork.Periodically =
        UpdateForecastWork.Periodically(WorkManager.getInstance(context))

    @Provides
    fun once(@ApplicationContext context: Context): UpdateForecastWork.Once =
        UpdateForecastWork.Once(WorkManager.getInstance(context))
}