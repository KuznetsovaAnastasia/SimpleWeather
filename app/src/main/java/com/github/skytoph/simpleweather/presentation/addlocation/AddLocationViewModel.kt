package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.annotation.IdRes
import androidx.lifecycle.*
import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import com.github.skytoph.simpleweather.domain.addlocation.AddLocationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(
    state: SavedStateHandle,
    private val interactor: AddLocationInteractor,
    private val navigator: AddLocationNavigator,
    private val progressCommunication: ProgressCommunication.Observe,
) : ViewModel() {

    private val placeId: String = state[PLACE_ID_KEY]!!
    private val favorite: Boolean = state[FAVORITE_KEY]!!

    fun showWeather(@IdRes container: Int) = navigator.showWeather(container, placeId, favorite)

    fun saveWeather() = viewModelScope.launch(Dispatchers.IO) {
        interactor.save()
    }

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Boolean>) {
        progressCommunication.observe(owner, observer)
    }

    companion object {
        const val PLACE_ID_KEY = "placeId"
        const val FAVORITE_KEY = "favorite"
    }
}