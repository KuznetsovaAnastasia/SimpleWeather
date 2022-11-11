package com.github.skytoph.simpleweather.domain.settings

import com.github.skytoph.simpleweather.domain.search.LocationsRepository
import javax.inject.Inject

interface SettingsInteractor {
    suspend fun clearSearchHistory()

    class Base @Inject constructor(private val locationsRepository: LocationsRepository) :
        SettingsInteractor {

        override suspend fun clearSearchHistory() =
            locationsRepository.clearSearchHistory()
    }
}
