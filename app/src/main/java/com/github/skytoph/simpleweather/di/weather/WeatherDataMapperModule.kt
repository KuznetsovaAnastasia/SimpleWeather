package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.location.mapper.PlaceToCloudMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.cloud.mapper.WeatherCloudToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.ContentDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.CurrentWeatherDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.*
import com.github.skytoph.simpleweather.data.weather.mapper.content.horizon.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.indicators.IndicatorsDataMapper
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
    abstract fun contentMapper(mapper: ContentDBToDataMapper.Base): ContentDBToDataMapper

    @Binds
    abstract fun currentWeatherMapper(mapper: CurrentWeatherDataMapper.Base): CurrentWeatherDataMapper

    @Binds
    abstract fun indicatorsMapper(mapper: IndicatorsDataMapper.Base): IndicatorsDataMapper

    @Binds
    abstract fun horizonMapper(mapper: HorizonDataMapper.Base): HorizonDataMapper

    @Binds
    abstract fun alertMapper(mapper: WarningDataMapper.Base): WarningDataMapper

    @Binds
    abstract fun alertsMapper(mapper: WarningListDataMapper.Base): WarningListDataMapper

    @Binds
    abstract fun forecastMapper(mapper: ForecastDBToDataMapper.Base): ForecastDBToDataMapper

    @Binds
    abstract fun hourlyListMapper(mapper: HourlyForecastListDataMapper.Base): HourlyForecastListDataMapper

    @Binds
    abstract fun dailyMapper(mapper: DailyForecastDataMapper.Base): DailyForecastDataMapper

    @Binds
    abstract fun dailyListMapper(mapper: DailyForecastListDataMapper.Base): DailyForecastListDataMapper

    @Binds
    abstract fun dbToDataMapper(mapper: WeatherDBToDataMapper.Base): WeatherDBToDataMapper
}