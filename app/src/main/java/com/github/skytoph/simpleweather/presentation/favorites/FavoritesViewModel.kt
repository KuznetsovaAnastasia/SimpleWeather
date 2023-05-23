package com.github.skytoph.simpleweather.presentation.favorites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import com.github.skytoph.simpleweather.domain.favorites.FavoritesInteractor
import com.github.skytoph.simpleweather.domain.work.UpdateForecastWork
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
    private val worker: UpdateForecastWork.Once,
) : ViewModel() {

    init {
        refresh()
    }

    fun initialize(submitFavorites: (List<String>) -> Unit) {
        submitFavorites(interactor.favoriteIDs())
    }

    fun refreshFavorites(ids: List<String>? = null) {
        val favorites = ids ?: interactor.favoriteIDs()
        val state =
            if (favorites.isEmpty()) FavoritesState.Empty else FavoritesState.Base(favorites)
        stateCommunication.show(state)
    }

    fun refresh(lifecycleOwner: LifecycleOwner? = null, hideRefreshing: () -> Unit = {}) {
        if (interactor.favoriteIDs().isEmpty()) {
            stateCommunication.show(FavoritesState.Progress(true))
            requestPermissions()
            hideRefreshing()
        } else {
            worker.scheduleWork()
            lifecycleOwner?.let { observeUpdatingWork(lifecycleOwner, hideRefreshing) }
        }
    }

    private fun observeUpdatingWork(lifecycleOwner: LifecycleOwner, hideRefreshing: () -> Unit) =
        worker.observeWork(lifecycleOwner) { info ->
            if (info?.state != WorkInfo.State.RUNNING)
                hideRefreshing()
            if (info?.state == WorkInfo.State.FAILED)
                interactor.handleUpdatingError()
            else if (info?.state == WorkInfo.State.SUCCEEDED)
                updateWeatherContent()
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

    private fun requestPermissions() =
        stateCommunication.show(FavoritesState.AddCurrentLocation(addCurrentLocation = {
            stateCommunication.show(FavoritesState.RequestPermission)
        }, cancel = {
            stateCommunication.show(FavoritesState.Progress(false))
        }))

    fun saveCurrentLocation() {
        stateCommunication.show(FavoritesState.Progress(true))
        viewModelScope.launch(Dispatchers.IO) {
            interactor.saveCurrentLocation()
            withContext(Dispatchers.Main) {
                refreshFavorites()
            }
        }
    }

    fun savedPage(): Int = interactor.savedPage()

    fun saveCurrentPage(position: Int) = interactor.savePage(position)
}