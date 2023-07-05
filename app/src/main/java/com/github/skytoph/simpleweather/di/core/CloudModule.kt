package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.data.location.cloud.CoordinatesAdapter
import com.github.skytoph.simpleweather.data.location.cloud.LocationAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object CloudModule {

    @Provides
    fun retrofit(moshiConverter: MoshiConverterFactory): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
        )
        .addConverterFactory(moshiConverter)
        .build()

    @Provides
    fun moshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    fun moshi(): Moshi =
        Moshi.Builder()
            .add(CoordinatesAdapter())
            .add(LocationAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
}