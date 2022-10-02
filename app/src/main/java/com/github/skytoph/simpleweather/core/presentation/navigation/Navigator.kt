package com.github.skytoph.simpleweather.core.presentation.navigation

import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.presentation.addlocation.AddLocationNavFragment
import com.github.skytoph.simpleweather.presentation.addlocation.AddLocationNavigator
import com.github.skytoph.simpleweather.presentation.favorites.FavoritesNavFragment
import com.github.skytoph.simpleweather.presentation.main.MainContentNavigator
import com.github.skytoph.simpleweather.presentation.main.MainNavFragment
import com.github.skytoph.simpleweather.presentation.main.MainNavigator
import com.github.skytoph.simpleweather.presentation.search.SearchNavFragment
import com.github.skytoph.simpleweather.presentation.search.SearchNavigator
import com.github.skytoph.simpleweather.presentation.settings.SettingsNavFragment
import com.github.skytoph.simpleweather.presentation.weather.WeatherNavFragment
import javax.inject.Inject
import javax.inject.Singleton

interface Navigator : AddLocationNavigator, SearchNavigator, MainContentNavigator, MainNavigator {

    @Singleton
    class Base @Inject constructor(
        private val communication: NavigationCommunication.Mutable,
    ) : Navigator {

        override fun showMain(@IdRes container: Int) =
            communication.show(MainNavFragment(R.id.fragment_container))

        override fun showWeather(@IdRes container: Int, id: String, favorite: Boolean) =
            communication.show(WeatherNavFragment(container, id, favorite))

        override fun showFavorites(@IdRes container: Int) =
            communication.show(FavoritesNavFragment(container))

        override fun showSettings(@IdRes container: Int) =
            communication.show(SettingsNavFragment(container))

        override fun showSearchPredictions(@IdRes container: Int) =
            communication.show(SearchNavFragment(container))

        override fun showPredictionDetails(@IdRes container: Int, id: String, favorite: Boolean) =
            communication.show(AddLocationNavFragment(container, id, favorite))

        override fun observe(owner: LifecycleOwner, observer: Observer<ShowScreen>) =
            communication.observe(owner, observer)

        override fun goBack() =
            communication.show(Navigate(NavigationAction.POPUP))
    }
}