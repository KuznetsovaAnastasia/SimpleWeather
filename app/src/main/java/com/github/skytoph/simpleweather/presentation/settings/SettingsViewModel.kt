package com.github.skytoph.simpleweather.presentation.settings

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.communication.MessageCommunication
import com.github.skytoph.simpleweather.core.presentation.error.UiMessage
import com.github.skytoph.simpleweather.domain.settings.SettingsInteractor
import com.github.skytoph.simpleweather.presentation.settings.nav.SettingsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val interactor: SettingsInteractor,
    private val navigation: SettingsNavigator,
    private val messages: MessageCommunication.Update,
) : ViewModel() {

    fun clearSearchHistory() =
        viewModelScope.launch(Dispatchers.IO) {
            interactor.clearSearchHistory()
            withContext(Dispatchers.Main) { messages.show(UiMessage.SnackbarShort(R.string.search_history_cleared)) }
        }

    fun showAbout(@IdRes container: Int) = navigation.showAbout(container)

    fun goBack() = navigation.goBack()
}