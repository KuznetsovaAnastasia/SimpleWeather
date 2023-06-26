package com.github.skytoph.simpleweather.core.presentation.communication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class Communication {

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Update<T> {
        fun show(data: T)
    }

    abstract class Abstract<T>(protected val data: MutableLiveData<T>) : Mutable<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            data.observe(owner, observer)
    }

    interface Mutable<T> : Observe<T>, Update<T>

    interface Handle<T> {
        suspend fun handle(block: suspend (T) -> Unit)
    }

    interface MutableEvents<T> : Handle<T>, Update<T>

    abstract class UiUpdate<T>(data: MutableLiveData<T> = MutableLiveData()) : Abstract<T>(data) {

        override fun show(data: T) {
            this.data.value = data
        }
    }

    abstract class PostUpdate<T>(data: MutableLiveData<T> = MutableLiveData()) : Abstract<T>(data) {

        override fun show(data: T) = this.data.postValue(data)
    }

    abstract class SingleUiUpdate<T> : UiUpdate<T>(SingleLiveEvent())

    abstract class SinglePostUpdate<T> : PostUpdate<T>(SingleLiveEvent())

    abstract class MultipleEvents<T>(
        private val flow: MutableSharedFlow<T> =
            MutableSharedFlow(replay = 1, extraBufferCapacity = 5)
    ) : MutableEvents<T> {

        override fun show(data: T) {
            flow.tryEmit(data)
        }

        override suspend fun handle(block: suspend (T) -> Unit) {
            flow.collect { data -> block(data) }
        }
    }

}
