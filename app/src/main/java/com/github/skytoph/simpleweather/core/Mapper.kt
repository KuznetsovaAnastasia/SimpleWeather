package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.exception.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface Mapper<T> {

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
            is DataIsNotCachedException -> R.string.error_location_is_not_saved
            else -> R.string.error_general
        }
    }
}