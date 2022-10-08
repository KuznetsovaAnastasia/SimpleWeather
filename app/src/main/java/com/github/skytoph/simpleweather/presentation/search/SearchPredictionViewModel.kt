package com.github.skytoph.simpleweather.presentation.search

import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.domain.search.ValidIdInteractor
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchPredictionViewModel @Inject constructor(
    private val searchCommunication: SearchCommunication.Observe,
    private val interactor: ValidIdInteractor,
    private val navigation: SearchNavigator,
) : ViewModel() {

    fun showDetails(@IdRes container: Int, id: String) = viewModelScope.launch(Dispatchers.IO) {
        val validId = interactor.validId(id)
        val favorite = interactor.isFavorite(validId)
        withContext(Dispatchers.Main) {
            navigation.showPredictionDetails(container, validId, favorite)
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<SearchItemUi>>) =
        searchCommunication.observe(owner, observer)
}
