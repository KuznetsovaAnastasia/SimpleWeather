package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.presentation.navigation.NavigationCommunication
import com.github.skytoph.simpleweather.core.presentation.navigation.Navigator
import com.github.skytoph.simpleweather.presentation.addlocation.AddLocationNavigator
import com.github.skytoph.simpleweather.presentation.main.MainContentNavigator
import com.github.skytoph.simpleweather.presentation.main.MainNavigator
import com.github.skytoph.simpleweather.presentation.search.SearchNavigator
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
    abstract fun navigationCommunication(communication: NavigationCommunication.Base): NavigationCommunication.Mutable
}