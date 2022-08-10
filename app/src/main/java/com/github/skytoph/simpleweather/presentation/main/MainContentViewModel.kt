package com.github.skytoph.simpleweather.presentation.main

;

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.data.search.SearchLocationDataSource
import com.github.skytoph.simpleweather.data.search.mapper.SearchResultsToUiMapper
import com.github.skytoph.simpleweather.presentation.search.SearchCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainContentViewModel @Inject constructor(
    private val navigator: MainContentNavigator,
    private val searchCommunication: SearchCommunication.Update,
    private val searchLocationDataSource: SearchLocationDataSource,
    private val uiMapper: SearchResultsToUiMapper,
) : ViewModel() {

    fun getPredictions(query: String) {
//        searchLocationDataSource.getPredictions(query, locationsCommunication)

        viewModelScope.launch(Dispatchers.IO) {
            searchLocationDataSource.getPredictions(query, searchCommunication) { predictions ->
                searchCommunication.show(uiMapper.map(predictions))
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