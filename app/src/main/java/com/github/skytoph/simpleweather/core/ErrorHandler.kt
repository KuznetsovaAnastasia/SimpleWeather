package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.error.UiMessage
import com.github.skytoph.simpleweather.core.provider.ResourceProvider
import javax.inject.Inject

interface ErrorHandler {
    fun handle(exception: Exception)

    class Ui @Inject constructor(
        private val errorCommunication: MessageCommunication.Update,
        resources: ResourceProvider,
    ) : Mapper.UiAbstract<UiMessage>(resources), ErrorHandler {

        override fun handle(exception: Exception) =
            errorCommunication.show(UiMessage.ShowSnackbar(messageText(exception)))
    }
}