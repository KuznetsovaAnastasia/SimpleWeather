package com.github.skytoph.simpleweather.presentation.search

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.domain.search.SearchDetailsInteractor
import com.github.skytoph.simpleweather.presentation.addlocation.Loading
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingCommunication
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCommunication: SearchCommunication.Observe,
    private val loadingCommunication: LoadingCommunication.Update,
    private val interactor: SearchDetailsInteractor,
    private val searchHistory: HistoryCommunication,
    private val navigation: SearchNavigator,
) : ViewModel() {

    init {
        refreshHistory()
    }

    fun showDetails(
        fragmentManager: FragmentManager,
        @IdRes container: Int,
        id: String,
        title: String
    ) {
        loadingCommunication.show(Loading.INITIAL)
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveSearchResult(id, title)
            val validId = interactor.validId(id)
            val favorite = interactor.isFavorite(validId)
            withContext(Dispatchers.Main) {
                navigation.showSearchDetails(fragmentManager, container, validId, favorite)
            }
        }
    }

    fun refreshHistory() = viewModelScope.launch(Dispatchers.IO) {
        val history = interactor.searchHistory()
        withContext(Dispatchers.Main) {
            searchHistory.show(history)
        }
    }

    fun observeHistory(owner: LifecycleOwner, observer: Observer<List<SearchHistoryUi>>) =
        searchHistory.observe(owner, observer)

    fun observe(owner: LifecycleOwner, observer: Observer<List<SearchItemUi>>) =
        searchCommunication.observe(owner, observer)
}