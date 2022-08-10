package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.weather.mapper.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherDomainMapperModule {

    @Binds
    abstract fun domainMapper(mapper: WeatherDataToDomainMapper.Base): WeatherDataToDomainMapper

    @Binds
    abstract fun weatherMapper(mapper: CurrentWeatherDomainMapper.Base): CurrentWeatherDomainMapper

    @Binds
    abstract fun indicatorsMapper(mapper: IndicatorsDataToDomainMapper.Base): IndicatorsDataToDomainMapper

    @Binds
    abstract fun horizonMapper(mapper: HorizonDataToDomainMapper.Base): HorizonDataToDomainMapper

    @Binds
    abstract fun warningsMapper(mapper: WarningsDataToDomainMapper.Base): WarningsDataToDomainMapper

    @Binds
    abstract fun warningMapper(mapper: WarningDataToDomainMapper.Base): WarningDataToDomainMapper

}