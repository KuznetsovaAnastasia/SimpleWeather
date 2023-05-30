package com.github.skytoph.simpleweather.presentation.search

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.domain.search.SearchDetailsInteractor
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingState
import com.github.skytoph.simpleweather.presentation.addlocation.communication.WeatherLoadingCommunication
import com.github.skytoph.simpleweather.presentation.search.communication.HistoryCommunication
import com.github.skytoph.simpleweather.presentation.search.communication.SearchCommunication
import com.github.skytoph.simpleweather.presentation.search.communication.SearchLoadingCommunication
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi
import com.github.skytoph.simpleweather.presentation.search.nav.SearchNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCommunication: SearchCommunication.Observe,
    private val loadingCommunication: WeatherLoadingCommunication.Update,
    private val searchLoading: SearchLoadingCommunication.Mutable,
    private val interactor: SearchDetailsInteractor,
    private val searchHistory: HistoryCommunication,
    private val navigation: SearchNavigator,
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val history = interactor.searchHistory()
            withContext(Dispatchers.Main) {
                searchHistory.show(history)
            }
        }
    }

    fun showDetails(
        fragmentManager: FragmentManager,
        @IdRes container: Int,
        id: String,
        title: String
    ) {
        loadingCommunication.show(LoadingState.Initial)
        searchLoading.show(false)
        navigation.showSearchDetails(fragmentManager, container, id)
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveSearchResult(id, title)
            val history = interactor.searchHistory()
            withContext(Dispatchers.Main) {
                searchHistory.show(history)
            }
        }
    }

    fun observeHistory(owner: LifecycleOwner, observer: Observer<List<SearchHistoryUi>>) =
        searchHistory.observe(owner, observer)

    fun observeLoading(owner: LifecycleOwner, observer: Observer<Boolean>) =
        searchLoading.observe(owner, observer)

    fun observe(owner: LifecycleOwner, observer: Observer<List<SearchItemUi>>) =
        searchCommunication.observe(owner, observer)
}