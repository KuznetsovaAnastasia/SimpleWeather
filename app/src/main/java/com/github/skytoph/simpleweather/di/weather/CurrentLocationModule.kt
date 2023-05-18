package com.github.skytoph.simpleweather.di.weather

import android.content.Context
import android.location.LocationManager
import com.github.skytoph.simpleweather.data.location.CurrentLocation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object CurrentLocationModule {

    @Provides
    @ActivityScoped
    fun currentLocation(@ActivityContext context: Context): CurrentLocation =
        CurrentLocation.GPS(context.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
}