package com.github.skytoph.simpleweather.presentation.favorites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import com.github.skytoph.simpleweather.domain.favorites.FavoritesInteractor
import com.github.skytoph.simpleweather.presentation.RefreshCommunication
import com.github.skytoph.simpleweather.presentation.addlocation.Loading
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val interactor: FavoritesInteractor,
    private val communication: FavoritesCommunication,
    private val refreshCommunication: RefreshCommunication.Update,
    private val stateCommunication: FavoritesStateCommunication,
    private val progressCommunication: ProgressCommunication.Update,
    private val loadingCommunication: LoadingCommunication.Observe,
) : ViewModel() {

    fun initialize(firstCreated: Boolean) {
        if (firstCreated) {
            refreshFavorites()
            progressCommunication.show(true)
        }
    }

    fun getFavorites(): List<String> = interactor.favoriteIDs()

    fun refresh(hideProgress: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        interactor.refreshFavorites()
        withContext(Dispatchers.Main) {
            updateChanges()
            hideProgress()
        }
    }

    fun updateChanges() = refreshCommunication.show(true)

    fun refreshFavorites() = communication.show(getFavorites())

    fun delete(id: String) = stateCommunication.show(FavoritesState.Delete {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.removeFavorite(id)
            withContext(Dispatchers.Main) { refreshFavorites() }
        }
    })

    fun observe(owner: LifecycleOwner, observer: Observer<List<String>>) =
        communication.observe(owner, observer)

    fun observeState(owner: LifecycleOwner, observer: Observer<FavoritesState>) =
        stateCommunication.observe(owner, observer)

    fun updateState(isFavoritesEmpty: Boolean) {
        stateCommunication.show(if (isFavoritesEmpty) FavoritesState.Error else FavoritesState.Base)
        updateChanges()
    }

    fun onHiddenChanged(hidden: Boolean) {
        stateCommunication.show(if (hidden) FavoritesState.Hidden else FavoritesState.Base)
        if (!hidden) refreshFavorites()
    }

    fun updateLocations() = interactor.saveRefreshLocationIntention()

    fun observeWeatherLoading(owner: LifecycleOwner, observer: Observer<Loading>) =
        loadingCommunication.observe(owner, observer)

    fun showProgress(owner: LifecycleOwner, loadingState: Loading) {
        if (loadingState != Loading.INITIAL) {
            progressCommunication.show(false)
            loadingCommunication.removeObserver(owner)
        }
    }
}