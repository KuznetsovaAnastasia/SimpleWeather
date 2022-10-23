package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.lifecycle.LiveData
import com.github.skytoph.simpleweather.core.presentation.communication.Communication
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface StateCommunication : Communication.Observe<State> {

    @ViewModelScoped
    class Base @Inject constructor(data: LiveData<State>) :
        Communication.ImmutableUpdate<State>(data), StateCommunication
}