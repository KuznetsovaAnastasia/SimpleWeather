package com.github.skytoph.simpleweather.data.weather

import com.github.skytoph.simpleweather.core.data.BaseCache
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import javax.inject.Inject

class WeatherCache @Inject constructor() : BaseCache<WeatherData, IdentifierData>()