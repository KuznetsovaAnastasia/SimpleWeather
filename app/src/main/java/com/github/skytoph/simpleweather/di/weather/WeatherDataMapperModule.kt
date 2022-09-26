package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.location.mapper.PlaceToCloudMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.AlertsDataMapper
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.WeatherCloudToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.*
import com.github.skytoph.simpleweather.data.weather.update.UpdateWeatherMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherDataMapperModule {

    @Binds
    abstract fun cloudToDataMapper(mapper: WeatherCloudToDataMapper.Base): WeatherCloudToDataMapper

    @Binds
    abstract fun placeToCloudMapper(mapper: PlaceToCloudMapper.Base): PlaceToCloudMapper

    @Binds
    abstract fun updateMapper(mapper: UpdateWeatherMapper.Base): UpdateWeatherMapper

    @Binds
    abstract fun currentWeatherMapper(mapper: CurrentWeatherDataMapper.Base): CurrentWeatherDataMapper

    @Binds
    abstract fun indicatorsMapper(mapper: IndicatorsDataMapper.Base): IndicatorsDataMapper

    @Binds
    abstract fun horizonMapper(mapper: HorizonDataMapper.Base): HorizonDataMapper

    @Binds
    abstract fun alertsMapper(mapper: AlertsDataMapper.Base): AlertsDataMapper

    @Binds
    abstract fun hourlyMapper(mapper: HourlyForecastDataMapper.Base): HourlyForecastDataMapper

    @Binds
    abstract fun hourlyListMapper(mapper: HourlyForecastListDataMapper.Base): HourlyForecastListDataMapper

    @Binds
    abstract fun dailyMapper(mapper: DailyForecastDataMapper.Base): DailyForecastDataMapper

    @Binds
    abstract fun dailyListMapper(mapper: DailyForecastListDataMapper.Base): DailyForecastListDataMapper

    @Binds
    abstract fun dbToDataMapper(mapper: WeatherDBToDataMapper.Base): WeatherDBToDataMapper
}