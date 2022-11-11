package com.github.skytoph.simpleweather.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.domain.settings.SettingsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val interactor: SettingsInteractor) : ViewModel() {

    fun clearSearchHistory() =
        viewModelScope.launch(Dispatchers.IO) { interactor.clearSearchHistory() }
}
