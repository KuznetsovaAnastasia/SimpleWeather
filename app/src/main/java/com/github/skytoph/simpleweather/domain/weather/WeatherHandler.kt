package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.core.ErrorHandler
import com.github.skytoph.simpleweather.core.domain.FunctionHandler
import com.github.skytoph.simpleweather.presentation.weather.model.WeatherUi
import javax.inject.Inject

class WeatherHandler @Inject constructor(errorHandler: ErrorHandler) :
    FunctionHandler.Abstract<WeatherUi>(errorHandler) {

    override suspend fun errorResult() = WeatherUi.Fail
}