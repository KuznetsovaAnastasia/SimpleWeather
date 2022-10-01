package com.github.skytoph.simpleweather.core.presentation.communication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

abstract class Communication {

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Update<T> {
        fun show(data: T)
    }

    interface Mutable<T> : Observe<T>, Update<T>

    interface Immutable<T> : Observe<T>

    abstract class Abstract<T>(protected val data: MutableLiveData<T>) : Mutable<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            data.observe(owner, observer)
    }

    abstract class ImmutableUpdate<T>(protected val data: LiveData<T>) : Immutable<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            data.observe(owner, observer)
    }

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
}
