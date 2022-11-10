package com.github.skytoph.simpleweather.presentation.search

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface HistoryCommunication: Communication.Mutable<List<SearchHistoryUi>> {

    @ViewModelScoped
    class Base @Inject constructor() : Communication.UiUpdate<List<SearchHistoryUi>>(), HistoryCommunication
}