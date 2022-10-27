package com.github.skytoph.simpleweather.core

import android.util.Log
import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.error.UiMessage
import javax.inject.Inject

interface ErrorHandler {
    fun handle(exception: Exception)

    class Ui @Inject constructor(private val errorCommunication: MessageCommunication.Update) :
        Mapper.UiAbstract<UiMessage>(), ErrorHandler {

        override fun handle(e: Exception) =
            errorCommunication.show(UiMessage.ShowSnackbar(messageRes(e)))
                .also { Log.e("ErrorTag", e.toString() + "\n" + e.stackTraceToString()) }
    }
}