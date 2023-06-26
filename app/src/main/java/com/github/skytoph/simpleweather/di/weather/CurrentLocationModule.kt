package com.github.skytoph.simpleweather.di.weather

import android.content.Context
import com.github.skytoph.simpleweather.data.location.CurrentLocationCoordinates
import com.github.skytoph.simpleweather.data.location.CurrentLocationId
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrentLocationModule {

    @Provides
    @Singleton
    fun currentLocation(
        @ApplicationContext context: Context,
        idMapper: IdMapper
    ): CurrentLocationCoordinates =
        CurrentLocationCoordinates.GPS(
            LocationServices.getFusedLocationProviderClient(context), idMapper
        )
}

@Module
@InstallIn(SingletonComponent::class)
interface CurrentLocationIdModule {

    @Binds
    @Singleton
    fun currentLocation(source: CurrentLocationId.Base): CurrentLocationId
}