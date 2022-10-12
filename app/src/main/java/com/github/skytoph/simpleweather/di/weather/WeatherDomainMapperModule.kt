package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.weather.mapper.*
import com.github.skytoph.simpleweather.domain.weather.mapper.DailyForecastDomainMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.DailyForecastListDomainMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.HourlyForecastDomainMapper
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
    abstract fun weatherMapper(mapper: CurrentWeatherDataToDomainMapper.Base): CurrentWeatherDataToDomainMapper

    @Binds
    abstract fun indicatorsMapper(mapper: IndicatorsDataToDomainMapper.Base): IndicatorsDataToDomainMapper

    @Binds
    abstract fun horizonMapper(mapper: HorizonDataToDomainMapper.Base): HorizonDataToDomainMapper

    @Binds
    abstract fun warningsMapper(mapper: WarningsDataToDomainMapper.Base): WarningsDataToDomainMapper

    @Binds
    abstract fun warningMapper(mapper: WarningDataToDomainMapper.Base): WarningDataToDomainMapper

    @Binds
    abstract fun hourlyMapper(mapper: HourlyForecastDomainMapper.Base): HourlyForecastDomainMapper

    @Binds
    abstract fun hourlyListMapper(mapper: HourlyForecastListDomainMapper.Base): HourlyForecastListDomainMapper

    @Binds
    abstract fun dailyMapper(mapper: DailyForecastDomainMapper.Base): DailyForecastDomainMapper

    @Binds
    abstract fun dailyListMapper(mapper: DailyForecastListDomainMapper.Base): DailyForecastListDomainMapper

}