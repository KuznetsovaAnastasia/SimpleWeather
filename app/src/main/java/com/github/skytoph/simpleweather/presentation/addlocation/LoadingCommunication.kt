package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.github.skytoph.simpleweather.core.presentation.LiveDataMapper
import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import javax.inject.Inject
import javax.inject.Singleton

interface LoadingCommunication {

    interface Update : Communication.Update<Loading>

    interface Observe : Communication.Observe<Loading> {
        fun <T> map(mapper: LiveDataMapper, map: (Loading) -> T): LiveData<T>
        fun removeObserver(owner: LifecycleOwner)
    }

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.UiUpdate<Loading>(), Mutable {

        override fun <T> map(mapper: LiveDataMapper, map: (Loading) -> T): LiveData<T> =
            mapper.map(data, map)

        override fun removeObserver(owner: LifecycleOwner) = data.removeObservers(owner)
    }
}

enum class Loading {
    INITIAL,
    SUCCESS,
    FAIL
}