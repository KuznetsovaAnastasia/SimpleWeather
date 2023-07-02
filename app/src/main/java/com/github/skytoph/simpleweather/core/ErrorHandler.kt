package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.error.UiMessage
import javax.inject.Inject

interface ErrorHandler {
    fun handle(exception: Exception)

    class Ui @Inject constructor(
        private val errorCommunication: MessageCommunication.Update,
        private val logger: Logger
    ) : Mapper.UiAbstract<UiMessage>(), ErrorHandler {

        override fun handle(exception: Exception) {
            errorCommunication.show(UiMessage.SnackbarShort(messageRes(exception)))
            logger.log(ErrorHandler::class.java.simpleName, exception)
        }
    }
}