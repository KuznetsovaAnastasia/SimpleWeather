package com.github.skytoph.simpleweather.presentation.search.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.presentation.search.LocationsCommunication
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi

class SearchPredictionViewModel(private val locationsCommunication: LocationsCommunication.Observe) :
    ViewModel() {

    fun observe(owner: LifecycleOwner, observer: Observer<List<SearchItemUi>>) =
        locationsCommunication.observe(owner, observer)
}
