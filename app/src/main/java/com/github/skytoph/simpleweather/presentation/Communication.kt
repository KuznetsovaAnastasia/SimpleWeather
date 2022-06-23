package com.github.skytoph.simpleweather.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

abstract class Communication<T> {
    private val data = MutableLiveData<T>()

    fun observe(owner: LifecycleOwner, observer: Observer<T>) = data.observe(owner, observer)

    fun show(data: T) {
        this.data.value = data
    }
}
