package com.github.skytoph.simpleweather.di.weather

import com.github.skytoph.simpleweather.data.weather.mapper.CurrentWeatherDataToDomainMapper
import com.github.skytoph.simpleweather.data.weather.mapper.HorizonDataToDomainMapper
import com.github.skytoph.simpleweather.data.weather.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.DailyForecastListDomainMapper
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
    abstract fun horizonMapper(mapper: HorizonDataToDomainMapper.Base): HorizonDataToDomainMapper

    @Binds
    abstract fun hourlyListMapper(mapper: HourlyForecastListDomainMapper.Base): HourlyForecastListDomainMapper

    @Binds
    abstract fun dailyListMapper(mapper: DailyForecastListDomainMapper.Base): DailyForecastListDomainMapper

}