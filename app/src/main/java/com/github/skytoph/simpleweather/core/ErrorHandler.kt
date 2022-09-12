package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.exception.EmptyRequestException
import com.github.skytoph.simpleweather.core.exception.NoCachedDataException
import com.github.skytoph.simpleweather.core.exception.NoResultsException
import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.view.horizon.ResourceProvider
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

interface ErrorHandler {
    fun handle(exception: Exception)

    class Ui @Inject constructor(
        private val errorCommunication: MessageCommunication.Update,
        private val resourceProvider: ResourceProvider,
    ) : ErrorHandler {

        override fun handle(exception: Exception) {
            val messageId = when (exception) {
                is NoCachedDataException -> R.string.error_no_cached_data
                is HttpException -> R.string.error_service_unavailable
                is UnknownHostException, is SocketTimeoutException -> R.string.error_no_connection
                is EmptyRequestException -> R.string.error_empty_request
                is NoResultsException -> R.string.error_no_results
                else -> R.string.error_general
            }
            errorCommunication.show(resourceProvider.string(messageId))
        }
    }
}