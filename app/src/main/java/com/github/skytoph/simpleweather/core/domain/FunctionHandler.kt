package com.github.skytoph.simpleweather.core.domain

import com.github.skytoph.simpleweather.core.ErrorHandler
import kotlinx.coroutines.CancellationException

interface FunctionHandler<T> {
    suspend fun handle(block: suspend () -> T): T
    suspend fun errorResult(): T

    abstract class Abstract<T>(private val errorHandler: ErrorHandler) : FunctionHandler<T> {

        override suspend fun handle(block: suspend () -> T): T = try {
            block()
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: Exception) {
            errorHandler.handle(exception)
            errorResult()
        }
    }
}