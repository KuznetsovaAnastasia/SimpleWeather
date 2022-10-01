package com.github.skytoph.simpleweather.presentation.addlocation

import javax.inject.Inject

interface LoadingStateMapper {
    fun map(loading: Loading): State

    class Base @Inject constructor() : LoadingStateMapper {

        override fun map(loading: Loading): State = when (loading) {
            Loading.INITIAL, Loading.IN_PROGRESS -> State.Initial
            Loading.SUCCESS -> State.Success
            Loading.FAIL -> State.Fail
        }
    }
}