package com.github.skytoph.simpleweather.presentation.about

import androidx.lifecycle.ViewModel
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigateBack
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(private val navigator: NavigateBack) : ViewModel() {

    fun goBack() = navigator.goBack()
}
