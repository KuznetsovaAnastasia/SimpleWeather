package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MessageModule {

    @Binds
    abstract fun communication(communication: MessageCommunication.Base): MessageCommunication.Mutable

    @Binds
    abstract fun communicationObservable(communication: MessageCommunication.Base): MessageCommunication.Observe

    @Binds
    abstract fun communicationWritable(communication: MessageCommunication.Base): MessageCommunication.Update

}