package com.github.skytoph.simpleweather.app

import android.app.Application
import com.github.skytoph.simpleweather.BuildConfig
import com.github.skytoph.simpleweather.core.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.TemperatureFormatter
import com.github.skytoph.simpleweather.core.TimeFormatter
import com.github.skytoph.simpleweather.data.AirRepository
import com.github.skytoph.simpleweather.data.LocationRepository
import com.github.skytoph.simpleweather.data.WeatherRepository
import com.github.skytoph.simpleweather.data.cloud.*
import com.github.skytoph.simpleweather.data.cloud.airquality.AirQualityCloudDataSource
import com.github.skytoph.simpleweather.data.cloud.airquality.AirQualityService
import com.github.skytoph.simpleweather.data.cloud.weather.mapper.AlertsCloudMapper
import com.github.skytoph.simpleweather.data.cloud.airquality.mapper.ToAirQualityDataMapper
import com.github.skytoph.simpleweather.data.cloud.weather.mapper.WeatherServerToDataMapper
import com.github.skytoph.simpleweather.data.location.cloud.LocationCloudDataSource
import com.github.skytoph.simpleweather.data.location.cloud.LocationService
import com.github.skytoph.simpleweather.data.mapper.WeatherDomainMapper
import com.github.skytoph.simpleweather.domain.mapper.*
import com.github.skytoph.simpleweather.presentation.StateCommunication
import com.github.skytoph.simpleweather.presentation.WeatherViewModel
import com.google.android.libraries.places.api.Places
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherApp : Application() {
    lateinit var viewModel: WeatherViewModel

    override fun onCreate() {
        super.onCreate()

        Places.initialize(this, BuildConfig.WEATHER_API_KEY)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java)
        val airService = retrofit.create(AirQualityService::class.java)
        val locationService = retrofit.create(LocationService::class.java)

        val weatherRepository = WeatherRepository.Base(
            WeatherCloudDataSource.Base(weatherService),
            WeatherServerToDataMapper.Base(
                WeatherTypeCloudMapper.Base(),
                AlertsCloudMapper.Base()
            )
        )
        val airRepository = AirRepository.Base(
            AirQualityCloudDataSource.Base(airService),
            ToAirQualityDataMapper.Base()
        )
        val locationRepository =
            LocationRepository.Base(LocationCloudDataSource.Base(locationService))
        val timeFormatter = TimeFormatter.Base()
        val probFormatter = ProbabilityFormatter.Base()
        viewModel = WeatherViewModel(
            weatherRepository,
            airRepository,
            locationRepository,
            StateCommunication(),
            WeatherDomainMapper.Base(),
            WeatherDomainToUiMapper.Base(
                CurrentWeatherDomainToUiMapper.Base(TemperatureFormatter.Base()),
                IndicatorsDomainToUiMapper.Base(timeFormatter, probFormatter),
                HorizonDomainToUiMapper.Base(timeFormatter),
                WarningsDomainToUiMapper.Base(WarningDomainToUiMapper.Base(timeFormatter, probFormatter))
            )
        )
    }
}