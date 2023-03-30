package com.github.skytoph.simpleweather.presentation.favorites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.domain.favorites.FavoritesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val interactor: FavoritesInteractor,
    private val stateCommunication: FavoritesStateCommunication,
    private val refreshCommunication: RefreshCommunication.Update,
) : ViewModel() {

    init {
        if (interactor.favoriteIDs().isEmpty()) requestPermissions()
    }

    fun initialize(firstCreated: Boolean, submitFavorites: (List<String>) -> Unit) {
        val favorites = interactor.favoriteIDs().also { submitFavorites(it) }
        if (!firstCreated) refreshLocations(favorites)
    }

    private fun refreshLocations(favorites: List<String>) = viewModelScope.launch(Dispatchers.IO) {
        interactor.refreshLocations(favorites) { withContext(Dispatchers.Main) { updateWeatherContent() } }
    }

    fun refreshFavorites(ids: List<String>? = null) {
        val favorites = ids ?: interactor.favoriteIDs()
        val state =
            if (favorites.isEmpty()) FavoritesState.Empty else FavoritesState.Base(favorites)
        stateCommunication.show(state)
    }

    fun refresh(isFavoritesEmpty: Boolean, hideRefreshing: () -> Unit) {
        if (isFavoritesEmpty) requestPermissions().also { hideRefreshing() }
        else {
            stateCommunication.show(FavoritesState.Progress(true))
            viewModelScope.launch(Dispatchers.IO) {
                interactor.refreshFavorites()
                withContext(Dispatchers.Main) {
                    stateCommunication.show(FavoritesState.Progress(false))
                    updateWeatherContent()
                    hideRefreshing()
                }
            }
        }
    }

    fun updateWeatherContent() = refreshCommunication.show(true)

    fun delete(id: String) = stateCommunication.show(FavoritesState.Delete {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.removeFavorite(id)
            withContext(Dispatchers.Main) { refreshFavorites() }
        }
    })

    fun observeState(owner: LifecycleOwner, observer: Observer<FavoritesState>) =
        stateCommunication.observe(owner, observer)

    private fun requestPermissions() = stateCommunication.show(FavoritesState.AddCurrentLocation {
        stateCommunication.show(FavoritesState.RequestPermission)
    })

    fun saveCurrentLocation() {
        stateCommunication.show(FavoritesState.Progress(true))
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveCurrentLocation()
            withContext(Dispatchers.Main) {
                refreshFavorites()
            }
        }
    }

    fun updateLocations() = interactor.saveRefreshLocationIntention()

    fun savedPage(): Int = interactor.savedPage()

    fun saveCurrentPage(position: Int) = interactor.savePage(position)
}