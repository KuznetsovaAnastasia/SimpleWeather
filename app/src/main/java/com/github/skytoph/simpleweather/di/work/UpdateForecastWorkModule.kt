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
    fun work(@ApplicationContext context: Context): UpdateForecastWork =
        UpdateForecastWork.Base(WorkManager.getInstance(context))
}