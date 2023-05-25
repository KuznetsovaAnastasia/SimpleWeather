package com.github.skytoph.simpleweather.presentation.addlocation.nav

import androidx.annotation.IdRes
import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationScreen
import com.github.skytoph.simpleweather.core.presentation.navigation.ShowStrategy
import com.github.skytoph.simpleweather.presentation.addlocation.AddLocationFragment

class AddLocationNavFragment(
    @IdRes container: Int,
    private val id: String,
    private val favorite: Boolean = false,
    strategy: ShowStrategy = ShowStrategy.Replace
) : NavigationScreen(
    TAG,
    AddLocationFragment::class.java,
    container,
    strategy
) {

    override fun fragment() = AddLocationFragment.newInstance(id, favorite)

    companion object {
        const val TAG = "AddLocationFragment"
    }
}