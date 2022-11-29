package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.weather.mapper.*
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.FindForecastedPop
import com.github.skytoph.simpleweather.domain.weather.mapper.HourlyForecastDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.HourlyForecastListDomainMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class WeatherDomainMapperModule {

    @Binds
    abstract fun domainMapper(mapper: WeatherDataToDomainMapper.Base): WeatherDataToDomainMapper

    @Binds
    abstract fun currentMapper(mapper: CurrentWeatherDataToDomainMapper.Base): CurrentWeatherDataToDomainMapper

    @Binds
    abstract fun indicatorsMapper(mapper: IndicatorsDomainMapper.Base): IndicatorsDomainMapper

    @Binds
    abstract fun timezoneMapper(mapper: TimezoneToStringMapper.Base): TimezoneToStringMapper

    @Binds
    abstract fun warningsMapper(mapper: WarningsDomainMapper.Base): WarningsDomainMapper

    @Binds
    abstract fun horizonDataMapper(mapper: HorizonDataToDomainMapper.Base): HorizonDataToDomainMapper

    @Binds
    abstract fun sunPositionMapper(mapper: SunPositionMapper.Base): SunPositionMapper

    @Binds
    abstract fun sunCalculator(calculator: SunPosition): SunPosition

    @Binds
    abstract fun findPopMapper(mapper: FindForecastedPop.Base): FindForecastedPop

    @Binds
    abstract fun hourlyMapper(mapper: HourlyForecastDataToDomainMapper.Base): HourlyForecastDataToDomainMapper

    @Binds
    abstract fun hourlyListMapper(mapper: HourlyForecastListDomainMapper.Base): HourlyForecastListDomainMapper
}