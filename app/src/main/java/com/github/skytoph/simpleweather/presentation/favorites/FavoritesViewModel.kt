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
    private val communication: FavoritesCommunication,
    private val refreshCommunication: RefreshCommunication.Update,
    private val stateCommunication: FavoritesStateCommunication,
) : ViewModel() {

    fun initialize(firstCreated: Boolean, submitFavorites: (List<String>) -> Unit) {
        val favorites = interactor.favoriteIDs().also { submitFavorites(it) }
        when {
            !firstCreated -> refreshLocations(favorites)
            favorites.isEmpty() ->
                stateCommunication.show(FavoritesState.Empty, addCurrentLocationState())
            else -> refreshAndShow(favorites)
        }
    }

    private fun refreshAndShow(favorites: List<String>) {
        stateCommunication.show(FavoritesState.Progress(true), FavoritesState.Hidden)
        viewModelScope.launch(Dispatchers.IO) {
            interactor.refreshFavorites(true)
            withContext(Dispatchers.Main) {
                communication.show(favorites)
                showProgress(false)
                updateChanges()
            }
        }
    }

    private fun refreshLocations(favorites: List<String>) = viewModelScope.launch(Dispatchers.IO) {
        interactor.refreshLocations(favorites) { withContext(Dispatchers.Main) { updateChanges() } }
    }

    private fun refreshFavorites() = communication.show(interactor.favoriteIDs())

    private fun showProgress(show: Boolean) = stateCommunication.show(FavoritesState.Progress(show))

    fun refresh(isFavoritesEmpty: Boolean, hideProgress: () -> Unit) {
        if (isFavoritesEmpty) requestPermissions().also { hideProgress() }
        else viewModelScope.launch(Dispatchers.IO) {
            interactor.refreshFavorites()
            withContext(Dispatchers.Main) {
                updateChanges()
                hideProgress()
            }
        }
    }

    fun updateChanges() = refreshCommunication.show(true)

    fun delete(id: String) = stateCommunication.show(FavoritesState.Delete {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.removeFavorite(id)
            withContext(Dispatchers.Main) { refreshFavorites() }
        }
    })

    fun observe(owner: LifecycleOwner, observer: Observer<List<String>>) =
        communication.observe(owner, observer)

    fun observeState(owner: LifecycleOwner, observer: Observer<List<FavoritesState>>) =
        stateCommunication.observe(owner, observer)

    fun updateState(isFavoritesEmpty: Boolean) {
        if (isFavoritesEmpty) showEmptyState()
        else stateCommunication.show(FavoritesState.Base)
    }

    private fun requestPermissions() = stateCommunication.show(addCurrentLocationState())

    private fun addCurrentLocationState() = FavoritesState.AddCurrentLocation {
        stateCommunication.show(FavoritesState.RequestPermission)
    }

    fun showEmptyState() {
        stateCommunication.show(FavoritesState.Empty)
    }

    fun saveCurrentLocation() {
        showProgress(true)
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveCurrentLocation()
            withContext(Dispatchers.Main) {
                showProgress(false)
                refreshFavorites()
            }
        }
    }

    fun onHiddenChanged(hidden: Boolean) {
        stateCommunication.show(if (hidden) FavoritesState.Hidden else FavoritesState.Base)
        if (!hidden) refreshFavorites()
    }

    fun updateLocations() = interactor.saveRefreshLocationIntention()

    fun savedPage(): Int = interactor.savedPage()

    fun saveCurrentPage(position: Int) = interactor.savePage(position)
}