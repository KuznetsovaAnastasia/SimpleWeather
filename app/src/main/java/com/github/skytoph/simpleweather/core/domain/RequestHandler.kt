package com.github.skytoph.simpleweather.core.domain

import com.github.skytoph.simpleweather.core.ErrorHandler
import javax.inject.Inject

class RequestHandler @Inject constructor(errorHandler: ErrorHandler) :
    FunctionHandler.Abstract<Unit>(errorHandler) {

    override suspend fun errorResult() = Unit
}