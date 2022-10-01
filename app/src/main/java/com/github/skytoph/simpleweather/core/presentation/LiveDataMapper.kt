package com.github.skytoph.simpleweather.core.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import javax.inject.Inject

interface LiveDataMapper {
    fun <T, V> map(data: LiveData<T>, mapper: (T) -> V): LiveData<V>

    class Base @Inject constructor() : LiveDataMapper {

        override fun <T, V> map(data: LiveData<T>, mapper: (T) -> V): LiveData<V> =
            Transformations.map(data) { mapper.invoke(it) }
    }
}