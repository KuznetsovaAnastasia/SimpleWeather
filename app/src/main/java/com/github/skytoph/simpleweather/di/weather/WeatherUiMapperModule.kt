package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.domain.weather.mapper.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherUiMapperModule {

    @Binds
    abstract fun uiMapper(mapper: WeatherDomainToUiMapper.Base): WeatherDomainToUiMapper

    @Binds
    abstract fun weatherMapper(mapper: CurrentWeatherDomainToUiMapper.Base): CurrentWeatherDomainToUiMapper

    @Binds
    abstract fun indicatorsMapper(mapper: IndicatorsDomainToUiMapper.Base): IndicatorsDomainToUiMapper

    @Binds
    abstract fun horizonMapper(mapper: HorizonDomainToUiMapper.Base): HorizonDomainToUiMapper

    @Binds
    abstract fun warningsMapper(mapper: WarningsDomainToUiMapper.Base): WarningsDomainToUiMapper

    @Binds
    abstract fun warningMapper(mapper: WarningDomainToUiMapper.Base): WarningDomainToUiMapper

    @Binds
    abstract fun hourlyMapper(mapper: HourlyForecastToUiMapper.Base): HourlyForecastToUiMapper

    @Binds
    abstract fun hourlyListMapper(mapper: HourlyForecastListToUiMapper.Base): HourlyForecastListToUiMapper

    @Binds
    abstract fun dailyMapper(mapper: DailyForecastToUiMapper.Base): DailyForecastToUiMapper

    @Binds
    abstract fun dailyListMapper(mapper: DailyForecastListToUiMapper.Base): DailyForecastListToUiMapper
}