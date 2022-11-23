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

    fun initialize(firstCreated: Boolean) {
        if (firstCreated) stateCommunication.show(FavoritesState.Hidden)

        val favorites = getFavorites()
        viewModelScope.launch(Dispatchers.IO) {
            val refreshed = interactor.refreshLocations(favorites)
            withContext(Dispatchers.Main) {
                if (firstCreated) communication.show(favorites)
                if (refreshed) updateChanges()
            }
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

    fun delete(id: String) = stateCommunication.show(FavoritesState.Delete {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.removeFavorite(id)
            withContext(Dispatchers.Main) { communication.show(getFavorites()) }
        }
    })

    fun observe(owner: LifecycleOwner, observer: Observer<List<String>>) =
        communication.observe(owner, observer)

    fun observeState(owner: LifecycleOwner, observer: Observer<FavoritesState>) =
        stateCommunication.observe(owner, observer)

    fun updateState(isFavoritesEmpty: Boolean) {
        stateCommunication.show(if (isFavoritesEmpty) FavoritesState.Error else FavoritesState.Base)
    }

    fun onHiddenChanged(hidden: Boolean) {
        stateCommunication.show(if (hidden) FavoritesState.Hidden else FavoritesState.Base)
        if (!hidden) communication.show(getFavorites())
    }

    fun updateLocations() = interactor.saveRefreshLocationIntention()

    fun savedPage(): Int = interactor.savedPage()

    fun saveCurrentPage(position: Int) = interactor.savePage(position)
}