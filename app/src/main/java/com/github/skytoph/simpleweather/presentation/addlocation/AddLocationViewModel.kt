package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.github.skytoph.simpleweather.core.presentation.StateMapper
import com.github.skytoph.simpleweather.domain.addlocation.AddLocationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(
    state: SavedStateHandle,
    private val interactor: AddLocationInteractor,
    private val navigator: AddLocationNavigator,
    private val loadingCommunication: WeatherLoadingCommunication.Observe,
    private val stateMapper: StateMapper<Loading, State>,
) : ViewModel() {

    private val placeId: String = state[PLACE_ID_KEY]!!
    private val favorite: Boolean = state[FAVORITE_KEY]!!

    fun showWeather(fragmentManager: FragmentManager, @IdRes container: Int) =
        navigator.showWeather(fragmentManager, container, placeId, favorite)

    fun saveWeather(successCallback: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        interactor.save {
            withContext(Dispatchers.Main) {
                successCallback()
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        if (favorite) return observer.onChanged(State.Favorite)
        else loadingCommunication.observe(owner) {
            observer.onChanged(stateMapper.map(it))
        }
    }

    companion object {
        const val PLACE_ID_KEY = "placeId"
        const val FAVORITE_KEY = "favorite"
    }
}