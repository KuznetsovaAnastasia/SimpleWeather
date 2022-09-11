package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.domain.addlocation.AddLocationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(
    private val interactor: AddLocationInteractor,
    private val navigator: AddLocationNavigator,
) : ViewModel() {

    fun showWeather(@IdRes container: Int, placeId: String, block: (Boolean) -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            val id = interactor.translateId(placeId)
            val favorite = interactor.isFavorite(id)
            val searchId = if (favorite) id else placeId
            withContext(Dispatchers.Main) {
                navigator.showWeather(container, searchId, favorite)
                block.invoke(favorite)
            }
        }

    fun saveWeather() = viewModelScope.launch(Dispatchers.IO) {
        interactor.save()
    }
}