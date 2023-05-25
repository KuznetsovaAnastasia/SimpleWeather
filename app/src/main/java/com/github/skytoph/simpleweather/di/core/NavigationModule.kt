package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationCommunication
import com.github.skytoph.simpleweather.core.presentation.navigation.Navigator
import com.github.skytoph.simpleweather.presentation.addlocation.nav.AddLocationNavigator
import com.github.skytoph.simpleweather.presentation.main.nav.MainContentNavigator
import com.github.skytoph.simpleweather.presentation.main.nav.MainNavigator
import com.github.skytoph.simpleweather.presentation.search.nav.SearchNavigator
import com.github.skytoph.simpleweather.presentation.settings.nav.SettingsNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun addLocationNav(navigator: Navigator.Base): AddLocationNavigator

    @Binds
    abstract fun searchNav(navigator: Navigator.Base): SearchNavigator

    @Binds
    abstract fun mainContentNav(navigator: Navigator.Base): MainContentNavigator

    @Binds
    abstract fun mainNav(navigator: Navigator.Base): MainNavigator

    @Binds
    abstract fun settingsNav(navigator: Navigator.Base): SettingsNavigator

    @Binds
    abstract fun navigationCommunication(communication: NavigationCommunication.Base): NavigationCommunication.Mutable
}