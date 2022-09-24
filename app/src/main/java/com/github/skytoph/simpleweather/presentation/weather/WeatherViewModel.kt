package com.github.skytoph.simpleweather.presentation.weather

import androidx.lifecycle.*
import com.github.skytoph.simpleweather.core.presentation.communication.ProgressCommunication
import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import com.github.skytoph.simpleweather.presentation.RefreshCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    state: SavedStateHandle,
    private val interactor: WeatherInteractor,
    private val progressCommunication: ProgressCommunication.Update,
    private val weatherCommunication: WeatherCommunication,
    private val refreshCommunication: RefreshCommunication.Observe,
) : ViewModel() {

    private val placeId = state.get<String>(PLACE_ID_KEY)!!
    private val favorite = state.get<Boolean>(FAVORITE_KEY)!!

    fun getWeather() {
        progressCommunication.show(true)

        viewModelScope.launch(Dispatchers.IO) {
            if (favorite) interactor.getCachedWeather(placeId).show()
            interactor.getCloudWeather(placeId, favorite).show()

            withContext(Dispatchers.Main) {
                progressCommunication.show(false)
            }
        }
    }

    private suspend fun WeatherUi.show() {
        withContext(Dispatchers.Main) {
            weatherCommunication.show(this@show)
        }
    }

    fun refreshFromCache() = viewModelScope.launch(Dispatchers.IO) {
        if (favorite) interactor.getCachedWeather(placeId).show()
    }


    fun observe(owner: LifecycleOwner, observer: Observer<WeatherUi>) {
        weatherCommunication.observe(owner, observer)
    }

    fun observeRefresh(owner: LifecycleOwner, observer: Observer<Boolean>) {
        refreshCommunication.observe(owner, observer)
    }

    companion object {
        const val PLACE_ID_KEY = "placeId"
        const val FAVORITE_KEY = "favorite"
    }
}