package com.github.skytoph.simpleweather.data.search

import com.github.skytoph.simpleweather.core.exception.UnknownException
import com.github.skytoph.simpleweather.data.search.mapper.PredictionListToDataMapper
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemListToUiMapper
import com.github.skytoph.simpleweather.presentation.search.LocationsCommunication
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient

interface SearchLocationDataSource {
    fun init()
    fun getPredictions(query: String, locationsCommunication: LocationsCommunication.Update)

    class Base(
        private val client: PlacesClient,
        private val dataMapper: PredictionListToDataMapper,
        private val uiMapper: SearchItemListToUiMapper
    ) : SearchLocationDataSource {

        private lateinit var token: AutocompleteSessionToken

        override fun init() {
            token = AutocompleteSessionToken.newInstance()
        }

        override fun getPredictions(
            query: String,
            locationsCommunication: LocationsCommunication.Update,
        ) {
            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
                .setSessionToken(token)
                .setTypeFilter(TypeFilter.REGIONS)
                .build()

            client.findAutocompletePredictions(request)
                .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                    locationsCommunication.show(uiMapper.map(dataMapper.map(response.autocompletePredictions.toList())))
                }
                .addOnFailureListener { exception: Exception? ->
                    locationsCommunication.show(uiMapper.map(dataMapper.map(exception
                        ?: UnknownException())))
                }
        }
    }
}
