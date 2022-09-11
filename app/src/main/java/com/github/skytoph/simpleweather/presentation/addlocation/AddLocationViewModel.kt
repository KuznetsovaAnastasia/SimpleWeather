package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.domain.addlocation.AddLocationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddLocationViewModel @Inject constructor(
    private val interactor: AddLocationInteractor,
    private val navigator: AddLocationNavigator,
) : ViewModel() {

    fun showWeather(@IdRes container: Int, id: String, favorite: Boolean) {
        navigator.showWeather(container, id, favorite)
    }

    fun saveWeather() = viewModelScope.launch(Dispatchers.IO) {
        interactor.save()
    }
}