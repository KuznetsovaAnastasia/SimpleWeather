package com.github.skytoph.simpleweather.presentation.main

;

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.data.search.SearchLocationDataSource
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemListToUiMapper
import com.github.skytoph.simpleweather.presentation.search.LocationsCommunication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainContentViewModel(
    private val navigator: MainContentNavigator,
    private val locationsCommunication: LocationsCommunication.Update,
    private val searchLocationDataSource: SearchLocationDataSource,
    private val uiMapper: SearchItemListToUiMapper,
) : ViewModel() {

    fun getPredictions(query: String) {
//        searchLocationDataSource.getPredictions(query, locationsCommunication)

        viewModelScope.launch(Dispatchers.IO) {
            searchLocationDataSource.getPredictions(query, locationsCommunication) { predictions ->
                locationsCommunication.show(uiMapper.map(predictions))
            }
        }
    }

    fun showSearch(@IdRes container: Int) {
        searchLocationDataSource.startSession()
        navigator.showSearchPredictions(container)
    }

    fun showFavorites(@IdRes container: Int) {
        navigator.showFavorites(container)
    }
}