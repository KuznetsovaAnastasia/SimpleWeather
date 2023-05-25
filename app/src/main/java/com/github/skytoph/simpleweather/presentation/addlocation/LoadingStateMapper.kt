package com.github.skytoph.simpleweather.presentation.addlocation

import com.github.skytoph.simpleweather.core.presentation.StateMapper
import com.github.skytoph.simpleweather.presentation.addlocation.communication.Loading
import javax.inject.Inject

interface LoadingStateMapper: StateMapper<Loading, State> {

    class Base @Inject constructor() : LoadingStateMapper {

        override fun map(loading: Loading): State = when (loading) {
            Loading.INITIAL -> State.Initial
            Loading.SUCCESS -> State.Success
            Loading.FAIL -> State.Fail
        }
    }
}