package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.core.exception.EmptyRequestException
import com.github.skytoph.simpleweather.core.exception.NoCachedDataException
import com.github.skytoph.simpleweather.core.exception.NoResultsException
import com.github.skytoph.simpleweather.core.presentation.view.horizon.ResourceProvider
import io.realm.RealmObject
import retrofit2.HttpException
import java.net.UnknownHostException

interface Mappable<T, M : Mapper<T>> {
    fun map(mapper: M): T
    interface Base
}

interface MappableTo<T> {
    fun map(): T
}

interface MappableToDB<T : RealmObject, M : Mapper<T>> {
    interface Base<T : RealmObject, M : Mapper<T>> : MappableToDB<T, M> {
        fun map(mapper: M, dataBase: DataBase): T
    }

    interface Embedded<T : RealmObject, M : Mapper<T>> : MappableToDB<T, M> {
        fun map(mapper: M): T
    }
}

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