package com.github.skytoph.simpleweather.app

import android.app.Application
import com.github.skytoph.simpleweather.BuildConfig
import com.github.skytoph.simpleweather.core.presentation.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationCommunication
import com.github.skytoph.simpleweather.core.presentation.navigation.MainViewModel
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TemperatureFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.data.airquality.AirRepository
import com.github.skytoph.simpleweather.data.location.LocationRepository
import com.github.skytoph.simpleweather.data.weather.WeatherRepository
import com.github.skytoph.simpleweather.data.weather.cloud.*
import com.github.skytoph.simpleweather.data.airquality.AirQualityCloudDataSource
import com.github.skytoph.simpleweather.data.airquality.AirQualityService
import com.github.skytoph.simpleweather.data.weather.mapper.AlertsCloudMapper
import com.github.skytoph.simpleweather.data.airquality.mapper.ToAirQualityDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherServerToDataMapper
import com.github.skytoph.simpleweather.data.location.cloud.LocationCloudDataSource
import com.github.skytoph.simpleweather.data.location.cloud.LocationService
import com.github.skytoph.simpleweather.data.mapper.WeatherDomainMapper
import com.github.skytoph.simpleweather.data.search.SearchLocationDataSource
import com.github.skytoph.simpleweather.data.search.mapper.PredictionListToDataMapper
import com.github.skytoph.simpleweather.data.search.mapper.PredictionToDataMapper
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemDataToUiMapper
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemListToUiMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.*
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import com.github.skytoph.simpleweather.presentation.search.LocationsCommunication
import com.github.skytoph.simpleweather.presentation.search.SearchViewModel
import com.github.skytoph.simpleweather.presentation.search.viewmodel.SearchPredictionViewModel
import com.github.skytoph.simpleweather.presentation.weather.WeatherCommunication
import com.github.skytoph.simpleweather.presentation.weather.WeatherViewModel
import com.google.android.libraries.places.api.Places
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherApp : Application() {

    lateinit var weatherViewModel: WeatherViewModel
    lateinit var searchViewModel: SearchViewModel
    lateinit var searchPredictionViewModel: SearchPredictionViewModel
    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()

        Places.initialize(this, BuildConfig.MAPS_API_KEY)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
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
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
            airRepository,
            locationRepository,
            WeatherDomainMapper.Base()
        )
        val progressCommunication = ProgressCommunication.Base()
        weatherViewModel = WeatherViewModel(
            weatherInteractor,
            progressCommunication,
            WeatherCommunication.Base(),
            WeatherDomainToUiMapper.Base(
                CurrentWeatherDomainToUiMapper.Base(TemperatureFormatter.Base()),
                IndicatorsDomainToUiMapper.Base(timeFormatter, probFormatter),
                HorizonDomainToUiMapper.Base(timeFormatter),
                WarningsDomainToUiMapper.Base(WarningDomainToUiMapper.Base(timeFormatter,
                    probFormatter))
            )
        )
        val locationsCommunication = LocationsCommunication.Base()
        val client = Places.createClient(this)
        val navCommunication = NavigationCommunication.Base()
        searchViewModel = SearchViewModel(
            navCommunication,
            locationsCommunication,
            SearchLocationDataSource.Base(client,
                PredictionListToDataMapper.Base(PredictionToDataMapper.Base()),
                SearchItemListToUiMapper.Base(SearchItemDataToUiMapper.Base())),
        )
        searchPredictionViewModel = SearchPredictionViewModel(locationsCommunication)
        mainViewModel = MainViewModel(navCommunication, progressCommunication)
    }
}