package com.github.skytoph.simpleweather.presentation.favorites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.domain.favorites.FavoritesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val interactor: FavoritesInteractor,
    private val communication: FavoritesCommunication,
) : ViewModel() {

    init {
        getFavorites()
    }

    fun getFavorites() {
        communication.show(interactor.getFavoriteIDs())
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<String>>) {
        communication.observe(owner, observer)
    }
}