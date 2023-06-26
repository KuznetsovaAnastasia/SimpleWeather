package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.exception.*
import com.github.skytoph.simpleweather.core.provider.ResourceProvider
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface Mapper<T> {
    abstract class ToDomain<T> : Mapper<T> {

        protected fun errorType(e: Exception) = when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            is NoCachedDataException -> ErrorType.NO_CACHED_DATA
            is EmptyRequestException -> ErrorType.EMPTY_REQUEST
            is NoResultsException -> ErrorType.NO_RESULTS
            is DataIsNotCachedException -> ErrorType.LOCATION_IS_NOT_CACHED
            else -> ErrorType.GENERIC_ERROR
        }
    }

    abstract class ToUi<T>(private val resources: ResourceProvider) : Mapper<T> {

        protected fun errorMessage(error: ErrorType) = when (error) {
            ErrorType.NO_CACHED_DATA -> R.string.error_no_cached_data
            ErrorType.SERVICE_UNAVAILABLE -> R.string.error_service_unavailable
            ErrorType.NO_CONNECTION -> R.string.error_no_connection
            ErrorType.EMPTY_REQUEST -> R.string.error_empty_request
            ErrorType.NO_RESULTS -> R.string.error_no_results
            ErrorType.LOCATION_IS_NOT_CACHED -> R.string.error_location_is_not_saved
            else -> R.string.error_general
        }
    }

    abstract class UiAbstract<T> : Mapper<T> {

        protected fun messageRes(exception: Exception): Int = when (exception) {
            is NoCachedDataException -> R.string.error_no_cached_data
            is HttpException -> R.string.error_service_unavailable
            is CanNotUpdateLocationException -> R.string.error_can_not_update_location
            is UnknownHostException, is SocketTimeoutException, is ConnectException -> R.string.error_no_connection
            is EmptyRequestException -> R.string.error_empty_request
            is NoResultsException -> R.string.error_no_results
            is FindCurrentLocationException -> R.string.error_unable_to_find_current_location
            is FavoritesLimitException -> R.string.error_favorites_limit
            else -> R.string.error_general
        }
    }
}