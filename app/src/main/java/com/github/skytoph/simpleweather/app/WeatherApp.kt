package com.github.skytoph.simpleweather.app

import android.app.Application
import com.github.skytoph.simpleweather.BuildConfig
import com.github.skytoph.simpleweather.core.data.RealmProvider
import com.github.skytoph.simpleweather.core.presentation.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.navigation.MainViewModel
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationCommunication
import com.github.skytoph.simpleweather.core.presentation.navigation.Navigator
import com.github.skytoph.simpleweather.core.provider.PreferencesProvider
import com.github.skytoph.simpleweather.core.util.SunCalculator
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TemperatureFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.data.airquality.AirQualityCloudDataSource
import com.github.skytoph.simpleweather.data.airquality.AirQualityService
import com.github.skytoph.simpleweather.data.favorites.FavoritesPrefCache
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.location.cloud.LocationService
import com.github.skytoph.simpleweather.data.location.cloud.PlaceCloudDataSource
import com.github.skytoph.simpleweather.data.location.mapper.LocationDataMapper
import com.github.skytoph.simpleweather.data.search.SearchLocationDataSource
import com.github.skytoph.simpleweather.data.search.geocode.PredictionCloudToDataMapper
import com.github.skytoph.simpleweather.data.search.geocode.PredictionService
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemDataToUiMapper
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemListToUiMapper
import com.github.skytoph.simpleweather.data.weather.WeatherCache
import com.github.skytoph.simpleweather.data.weather.cache.WeatherCacheDataSource
import com.github.skytoph.simpleweather.data.weather.cache.mapper.*
import com.github.skytoph.simpleweather.data.weather.cloud.WeatherCloudDataSource
import com.github.skytoph.simpleweather.data.weather.cloud.WeatherService
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.AlertsDataMapper
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.WeatherCloudToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.*
import com.github.skytoph.simpleweather.domain.favorites.FavoritesInteractor
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import com.github.skytoph.simpleweather.domain.weather.mapper.*
import com.github.skytoph.simpleweather.presentation.addlocation.AddLocationViewModel
import com.github.skytoph.simpleweather.presentation.favorites.FavoritesCommunication
import com.github.skytoph.simpleweather.presentation.favorites.FavoritesViewModel
import com.github.skytoph.simpleweather.presentation.main.MainContentViewModel
import com.github.skytoph.simpleweather.presentation.search.LocationsCommunication
import com.github.skytoph.simpleweather.presentation.search.SearchPredictionViewModel
import com.github.skytoph.simpleweather.presentation.weather.WeatherCommunication
import com.github.skytoph.simpleweather.presentation.weather.WeatherViewModel
import com.google.android.libraries.places.api.Places
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherApp : Application() {

    lateinit var weatherViewModel: WeatherViewModel
    lateinit var mainContentViewModel: MainContentViewModel
    lateinit var searchPredictionViewModel: SearchPredictionViewModel
    lateinit var mainViewModel: MainViewModel
    lateinit var favoritesViewModel: FavoritesViewModel
    lateinit var addLocationViewModel: AddLocationViewModel

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

        val timeFormatter = TimeFormatter.Base()
        val probFormatter = ProbabilityFormatter.Base()
        val locationDataMapper = LocationDataMapper.Base()
        val currentWeatherDataMapper = CurrentWeatherDataMapper.Base()
        val indicatorsDataMapper = IndicatorsDataMapper.Base()
        val horizonDataMapper = HorizonDataMapper.Base()
        val alertsMapper = AlertsDataMapper.Base()

        val client = Places.createClient(this)

//        val latMapper = PlaceCloudMapper.Lat()
//        val lngMapper = PlaceCloudMapper.Lng()
        val weatherRepository = WeatherRepository.Base(
            WeatherCacheDataSource.Base(RealmProvider.Base(this), WeatherDataDBMapper.Base(
                CurrentDBMapper.Base(),
                LocationDBMapper.Base(),
                IndicatorsDBMapper.Base(),
                HorizonDBMapper.Base()
            )),
            WeatherCloudDataSource.Base(weatherService),
            AirQualityCloudDataSource.Base(airService),
            PlaceCloudDataSource.Base(retrofit.create(LocationService::class.java)),
//            PlaceCloudDataSource.Base(client, PlaceToCloudMapper.Base()),
            WeatherCloudToDataMapper.Base(
                locationDataMapper,
                currentWeatherDataMapper,
                indicatorsDataMapper,
                horizonDataMapper,
                alertsMapper
            ),
            WeatherDBToDataMapper.Base(
                locationDataMapper,
                currentWeatherDataMapper,
                indicatorsDataMapper,
                horizonDataMapper),
            IdMapper.Base(),
            WeatherCache()
        )
        val weatherInteractor = WeatherInteractor.Base(
            weatherRepository,
//            WeatherRepository.Mock(),
            WeatherDataToDomainMapper.Base(CurrentWeatherDomainMapper.Base(),
                IndicatorsDataToDomainMapper.Base(),
                HorizonDataToDomainMapper.Base(SunCalculator.Base()),
                WarningsDataToDomainMapper.Base(WarningDataToDomainMapper.Base()))
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
        val navigation = Navigator.Base(NavigationCommunication.Base())
        mainContentViewModel = MainContentViewModel(
            navigation,
            locationsCommunication,
            SearchLocationDataSource.Geocode(retrofit.create(PredictionService::class.java),
                PredictionCloudToDataMapper.Base()),
//            SearchLocationDataSource.Mock(),
            SearchItemListToUiMapper.Base(SearchItemDataToUiMapper.Base())
//            SearchLocationDataSource.Base(client,
//                PredictionListToDataMapper.Base(PredictionToDataMapper.Base()),
//                SearchItemListToUiMapper.Base(SearchItemDataToUiMapper.Base())),
        )
        searchPredictionViewModel = SearchPredictionViewModel(locationsCommunication, navigation)
        mainViewModel = MainViewModel(navigation, progressCommunication)
        val favoritesInteractor =
            FavoritesInteractor.Base(FavoritesPrefCache.Base(PreferencesProvider.Base(this)),
                weatherRepository)
        favoritesViewModel = FavoritesViewModel(favoritesInteractor, FavoritesCommunication.Base())
        addLocationViewModel = AddLocationViewModel(
            favoritesInteractor,
            navigation)
    }
}