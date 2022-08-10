package com.github.skytoph.simpleweather.presentation.search

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi
import javax.inject.Inject
import javax.inject.Singleton

interface SearchCommunication {

    interface Update : Communication.Update<List<SearchItemUi>>

    interface Observe : Communication.Observe<List<SearchItemUi>>

    interface Mutable : Update, Observe

    @Singleton
    class Base @Inject constructor() : Communication.PostUpdate<List<SearchItemUi>>(), Mutable
}