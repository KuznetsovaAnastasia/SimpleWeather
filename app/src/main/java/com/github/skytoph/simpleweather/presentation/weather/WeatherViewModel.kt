package com.github.skytoph.simpleweather.presentation.weather

import androidx.lifecycle.*
import com.github.skytoph.simpleweather.domain.weather.WeatherInteractor
import com.github.skytoph.simpleweather.presentation.RefreshCommunication
import com.github.skytoph.simpleweather.presentation.RefreshData
import com.github.skytoph.simpleweather.presentation.addlocation.Loading
import com.github.skytoph.simpleweather.presentation.addlocation.LoadingCommunication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val interactor: WeatherInteractor,
    private val weatherCommunication: WeatherCommunication,
    private val loadingCommunication: LoadingCommunication.Update,
    private val refreshCommunication: RefreshCommunication.Observe,
) : ViewModel() {

    private val placeId = state.get<String>(PLACE_ID_KEY)!!
    private val favorite = state.get<Boolean>(FAVORITE_KEY)!!

    fun getWeather(firstCreated: Boolean) {
        if (!firstCreated) return
        loadingCommunication.show(Loading.INITIAL)

        viewModelScope.launch(Dispatchers.IO) {
            refreshFromCache()
            val cloudWeather = interactor.getCloudWeather(placeId, favorite).also { it.show() }

            withContext(Dispatchers.Main) {
                cloudWeather.show(loadingCommunication)
            }
        }
    }

    fun saveUpdateLocationState() {
        state[UPDATE_LOCATION_KEY] = true
    }

    fun updateLocation() {
        if (state.get<Boolean>(UPDATE_LOCATION_KEY) == true)
            viewModelScope.launch(Dispatchers.IO) {
                interactor.updateLocation(placeId).show()
                withContext(Dispatchers.Main) { state[UPDATE_LOCATION_KEY] = false }
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

    fun observe(owner: LifecycleOwner, observer: Observer<WeatherUi>) =
        weatherCommunication.observe(owner, observer)

    fun observeRefresh(owner: LifecycleOwner, observer: Observer<RefreshData>) =
        refreshCommunication.observe(owner, observer)

    companion object {
        const val PLACE_ID_KEY = "placeId"
        const val FAVORITE_KEY = "favorite"
        const val UPDATE_LOCATION_KEY = "update_location"
    }
}