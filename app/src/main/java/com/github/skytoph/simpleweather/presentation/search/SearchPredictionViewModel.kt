package com.github.skytoph.simpleweather.presentation.search

import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi

class SearchPredictionViewModel(
    private val locationsCommunication: LocationsCommunication.Observe,
    private val navigation: SearchNavigator,
) : ViewModel() {

    fun showDetails(@IdRes container: Int, id: String, favorite: Boolean) {
        navigation.showPredictionDetails(container, id, favorite)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<SearchItemUi>>) =
        locationsCommunication.observe(owner, observer)
}
