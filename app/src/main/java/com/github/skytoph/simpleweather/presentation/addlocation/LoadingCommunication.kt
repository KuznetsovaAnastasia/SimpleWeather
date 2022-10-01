package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.lifecycle.LiveData
import com.github.skytoph.simpleweather.core.presentation.LiveDataMapper
import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import javax.inject.Inject
import javax.inject.Singleton

interface LoadingCommunication {

    interface Update : Communication.Update<Loading>

    interface Observe : Communication.Observe<Loading> {
        fun <T> map(mapper: LiveDataMapper, map: (Loading) -> T): LiveData<T>
    }

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.UiUpdate<Loading>(), Mutable {

        override fun <T> map(mapper: LiveDataMapper, map: (Loading) -> T): LiveData<T> =
            mapper.map(data, map)
    }
}

enum class Loading {
    INITIAL,
    IN_PROGRESS,
    SUCCESS,
    FAIL
}