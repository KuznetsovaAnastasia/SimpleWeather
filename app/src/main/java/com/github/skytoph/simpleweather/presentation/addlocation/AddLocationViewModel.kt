package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.github.skytoph.simpleweather.core.presentation.StateMapper
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
    private val loadingCommunication: LoadingCommunication.Observe,
    private val progressCommunication: ProgressCommunication.Update,
    private val stateMapper: StateMapper<Loading, State>,
) : ViewModel() {

    private val placeId: String = state[PLACE_ID_KEY]!!
    private val favorite: Boolean = state[FAVORITE_KEY]!!

    fun showWeather(fragmentManager: FragmentManager, @IdRes container: Int) =
        navigator.showWeather(fragmentManager, container, placeId, favorite)

    fun saveWeather() = viewModelScope.launch(Dispatchers.IO) {
        interactor.save()
    }

    fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        if (favorite) return observer.onChanged(State.Favorite)
        else loadingCommunication.observe(owner) {
            observer.onChanged(stateMapper.map(it))
        }
    }

    fun showProgress(show: Boolean) = progressCommunication.show(show)

    companion object {
        const val PLACE_ID_KEY = "placeId"
        const val FAVORITE_KEY = "favorite"
    }
}