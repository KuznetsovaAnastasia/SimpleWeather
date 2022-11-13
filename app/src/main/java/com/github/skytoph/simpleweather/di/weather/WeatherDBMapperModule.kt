package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.WeatherDataDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.ContentDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.current.CurrentDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.forecast.*
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.horizon.HorizonDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.content.indicators.IndicatorsDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.identifier.IdentifierDBMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.time.TimeDBMapper
import com.github.skytoph.simpleweather.data.weather.mapper.time.TimeDBToDataMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherDBMapperModule {

    @Binds
    abstract fun weatherMapper(mapper: WeatherDataDBMapper.Base): WeatherDataDBMapper

    @Binds
    abstract fun horizonMapper(mapper: HorizonDBMapper.Base): HorizonDBMapper

    @Binds
    abstract fun forecastMapper(mapper: ForecastDBMapper.Base): ForecastDBMapper

    @Binds
    abstract fun contentMapper(mapper: ContentDBMapper.Base): ContentDBMapper

    @Binds
    abstract fun identifierMapper(mapper: IdentifierDBMapper.Base): IdentifierDBMapper

    @Binds
    abstract fun timeMapper(mapper: TimeDBMapper.Base): TimeDBMapper

    @Binds
    abstract fun timeDataMapper(mapper: TimeDBToDataMapper.Base): TimeDBToDataMapper

    @Binds
    abstract fun warningsMapper(mapper: WarningsDBMapper.Base): WarningsDBMapper

    @Binds
    abstract fun warningMapper(mapper: WarningDBMapper.Base): WarningDBMapper

    @Binds
    abstract fun hourlyForecastMapper(mapper: HourlyForecastDBMapper.Base): HourlyForecastDBMapper

    @Binds
    abstract fun hourlyForecastListMapper(mapper: HourlyForecastListDBMapper.Base): HourlyForecastListDBMapper

    @Binds
    abstract fun dailyForecastMapper(mapper: DailyForecastDBMapper.Base): DailyForecastDBMapper

    @Binds
    abstract fun dailyForecastListMapper(mapper: DailyForecastListDBMapper.Base): DailyForecastListDBMapper

    @Binds
    abstract fun idMapper(mapper: IdMapper.Base): IdMapper
}