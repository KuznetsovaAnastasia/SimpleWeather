package com.github.skytoph.simpleweather.presentation.search

import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchPredictionViewModel @Inject constructor(
    private val searchCommunication: SearchCommunication.Observe,
    private val navigation: SearchNavigator,
) : ViewModel() {

    fun showDetails(@IdRes container: Int, id: String, favorite: Boolean) {
        navigation.showPredictionDetails(container, id, favorite)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<SearchItemUi>>) =
        searchCommunication.observe(owner, observer)
}
