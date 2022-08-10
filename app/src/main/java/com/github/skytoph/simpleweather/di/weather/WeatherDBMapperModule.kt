package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.weather.cache.mapper.*
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
    abstract fun currentMapper(mapper: CurrentDBMapper.Base): CurrentDBMapper

    @Binds
    abstract fun locationMapper(mapper: LocationDBMapper.Base): LocationDBMapper

    @Binds
    abstract fun indicatorsMapper(mapper: IndicatorsDBMapper.Base): IndicatorsDBMapper

    @Binds
    abstract fun horizonMapper(mapper: HorizonDBMapper.Base): HorizonDBMapper

    @Binds
    abstract fun idMapper(mapper: IdMapper.Base): IdMapper
}