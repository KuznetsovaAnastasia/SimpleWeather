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

    interface MultipleMutable<T> : Observe<List<T>>, UpdateMultiple<T>

    abstract class Abstract<T>(protected val data: MutableLiveData<T>) : Mutable<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            data.observe(owner, observer)
    }

    interface Mutable<T> : Observe<T>, Update<T>

    interface UpdateMultiple<T> {
        fun show(vararg data: T)
    }

    abstract class MultipleUiUpdates<T>(protected val data: MutableLiveData<List<T>> = MutableLiveData()) :
        MultipleMutable<T> {

        override fun observe(owner: LifecycleOwner, observer: Observer<List<T>>) =
            data.observe(owner, observer)

        override fun show(vararg data: T) {
            this.data.value = data.asList()
        }
    }

    abstract class ImmutableUpdate<T>(protected val data: LiveData<T>) : Observe<T> {

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
