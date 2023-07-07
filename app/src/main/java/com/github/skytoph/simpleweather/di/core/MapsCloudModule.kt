package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.BuildConfig
import com.github.skytoph.simpleweather.data.location.cloud.CoordinatesAdapter
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
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMaps

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoshiConverterMaps

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoshiMaps

@Module
@InstallIn(SingletonComponent::class)
object MapsCloudModule {

    @Provides
    @RetrofitMaps
    fun retrofit(@MoshiConverterMaps moshiConverter: MoshiConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .client(
                OkHttpClient.Builder()
                    .apply {
                        if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                    }
                    .build()
            )
            .addConverterFactory(moshiConverter)
            .build()

    @Provides
    @MoshiConverterMaps
    fun moshiConverterFactory(@MoshiMaps moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @MoshiMaps
    fun moshi(): Moshi =
        Moshi.Builder()
            .add(CoordinatesAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
}