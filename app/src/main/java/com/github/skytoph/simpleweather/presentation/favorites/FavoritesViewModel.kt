package com.github.skytoph.simpleweather.presentation.favorites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.domain.favorites.FavoritesInteractor
import com.github.skytoph.simpleweather.presentation.RefreshCommunication
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
) : ViewModel() {

    fun getFavorites(): List<String> = interactor.favoriteIDs()

    fun refreshFavorites() = communication.show(interactor.favoriteIDs())

    fun refresh(hideProgress: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        interactor.refreshFavorites()
        withContext(Dispatchers.Main) {
            refreshCommunication.show(true)
            hideProgress.invoke()
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<String>>) {
        communication.observe(owner, observer)
    }

    fun delete(id: String) = viewModelScope.launch(Dispatchers.IO) {
        interactor.removeFavorite(id)
        refreshFavorites()
    }
}