package com.github.skytoph.simpleweather.presentation.addlocation

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy

class AddLocationNavFragment(
    @IdRes container: Int,
    private val id: String,
    private val favorite: Boolean = false,
    strategy: ShowStrategy = ShowStrategy.Replace,
) : NavigationScreen("AddLocationFragment$id",
    AddLocationFragment::class.java,
    container,
    strategy) {

    override fun fragment() = AddLocationFragment.newInstance(id, favorite)
}