package com.github.skytoph.simpleweather.presentation.main

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.core.presentation.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.Visibility
import com.github.skytoph.simpleweather.domain.search.SearchInteractor
import com.github.skytoph.simpleweather.domain.search.SearchResultsDomainToUiMapper
import com.github.skytoph.simpleweather.presentation.RefreshCommunication
import com.github.skytoph.simpleweather.presentation.search.SearchCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainContentViewModel @Inject constructor(
    private val navigator: MainContentNavigator,
    private val interactor: SearchInteractor,
    private val searchCommunication: SearchCommunication.Update,
    private val progressCommunication: ProgressCommunication.Update,
    private val refreshCommunication: RefreshCommunication.Update,
    private val uiMapper: SearchResultsDomainToUiMapper,
) : ViewModel() {

    fun getPredictions(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.search(query) { predictions ->
                searchCommunication.show(uiMapper.map(predictions))
            }
        }
    }

    fun showSearch(@IdRes container: Int) {
        interactor.startSession()
        getPredictions("")
        navigator.showSearchPredictions(container)
    }

    fun showFavorites(@IdRes container: Int) {
        navigator.showFavorites(container)
    }

    fun goBack() {
        navigator.goBack()
    }

    fun refresh() {
        progressCommunication.show(Visibility.Visible())
        viewModelScope.launch(Dispatchers.IO) {
            interactor.refreshCache()
            withContext(Dispatchers.Main) {
                refreshCommunication.show(true)
                progressCommunication.show(Visibility.Invisible())
            }
        }
    }
}