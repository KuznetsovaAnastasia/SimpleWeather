package com.github.skytoph.simpleweather.presentation.search

;

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationCommunication
import com.github.skytoph.simpleweather.data.search.SearchLocationDataSource


class SearchViewModel(
    private val navigationCommunication: NavigationCommunication.Update,
    private val locationsCommunication: LocationsCommunication.Update,
    private val searchLocationDataSource: SearchLocationDataSource
) : ViewModel() {

    fun showSearch(@IdRes container: Int) {
        searchLocationDataSource.init()
        navigationCommunication.show(SearchNavFragment(container))
    }

    fun getPredictions(query: String) {
        searchLocationDataSource.getPredictions(query, locationsCommunication)
    }

    fun showWeather(@IdRes container: Int) {
        navigationCommunication.show(WeatherNavFragment(container))
    }
}
