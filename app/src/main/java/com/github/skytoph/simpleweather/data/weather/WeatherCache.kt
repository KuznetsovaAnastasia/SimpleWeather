package com.github.skytoph.simpleweather.data.weather

import com.github.skytoph.simpleweather.core.data.BaseCache
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject

class WeatherCache @Inject constructor() : BaseCache<WeatherData>()