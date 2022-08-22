package com.github.skytoph.simpleweather.core

import android.util.Log
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.exception.EmptyRequestException
import com.github.skytoph.simpleweather.core.exception.NoCachedDataException
import com.github.skytoph.simpleweather.core.exception.NoResultsException
import com.github.skytoph.simpleweather.core.presentation.view.horizon.ResourceProvider
import retrofit2.HttpException
import java.net.UnknownHostException

interface Mapper<T> {
    abstract class ToDomain<T> : Mapper<T> {

        protected fun errorType(e: Exception) = when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            is NoCachedDataException -> ErrorType.NO_CACHED_DATA
            is EmptyRequestException -> ErrorType.EMPTY_REQUEST
            is NoResultsException -> ErrorType.NO_RESULTS
            else -> ErrorType.GENERIC_ERROR
        }
            .also { Log.e("ErrorTag", e.stackTraceToString()) }
    }

    abstract class ToUi<T>(private val resourceProvider: ResourceProvider) : Mapper<T> {

        protected fun errorMessage(error: ErrorType) = resourceProvider.string(
            when (error) {
                ErrorType.NO_CACHED_DATA -> R.string.error_no_cached_data
                ErrorType.SERVICE_UNAVAILABLE -> R.string.error_service_unavailable
                ErrorType.NO_CONNECTION -> R.string.error_no_connection
                ErrorType.EMPTY_REQUEST -> R.string.error_empty_request
                ErrorType.NO_RESULTS -> R.string.error_no_results
                else -> R.string.error_general
            }
        )
    }
}