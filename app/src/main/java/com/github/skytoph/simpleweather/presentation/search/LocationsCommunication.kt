package com.github.skytoph.simpleweather.presentation.search

import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi

interface LocationsCommunication {

    interface Update : Communication.Update<List<SearchItemUi>>

    interface Observe : Communication.Observe<List<SearchItemUi>>

    interface Mutable : Update, Observe

    class Base : Communication.PostUpdate<List<SearchItemUi>>(), Mutable
}