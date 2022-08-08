package com.github.skytoph.simpleweather.data.search

import com.github.skytoph.simpleweather.core.exception.UnknownException
import com.github.skytoph.simpleweather.data.search.geocode.PredictionCloudToDataMapper
import com.github.skytoph.simpleweather.data.search.geocode.PredictionService
import com.github.skytoph.simpleweather.data.search.mapper.PredictionListToDataMapper
import com.github.skytoph.simpleweather.presentation.search.LocationsCommunication
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse
import com.google.android.libraries.places.api.net.PlacesClient

interface SearchLocationDataSource {
    fun startSession()
    suspend fun getPredictions(
        query: String,
        locationsCommunication: LocationsCommunication.Update,
        showResult: (List<SearchItemData>) -> Unit,
    )

    class Geocode(
        private val service: PredictionService,
        private val dataMapper: PredictionCloudToDataMapper,
    ) : SearchLocationDataSource {

        override suspend fun getPredictions(
            query: String,
            locationsCommunication: LocationsCommunication.Update,
            showResult: (List<SearchItemData>) -> Unit,
        ) {
            if (query.isBlank()) return

            val predictions = service.getPrediction(query).execute().body()!!
                .map { prediction -> prediction.map(dataMapper) }
            showResult.invoke(predictions)
        }

        override fun startSession() = Unit
    }

    class Base(
        private val client: PlacesClient,
        private val dataMapper: PredictionListToDataMapper,
    ) : SearchLocationDataSource {

        private lateinit var token: AutocompleteSessionToken

        override fun startSession() {
            token = AutocompleteSessionToken.newInstance()
        }

        override suspend fun getPredictions(
            query: String,
            locationsCommunication: LocationsCommunication.Update,
            showResult: (List<SearchItemData>) -> Unit,
        ) {
            if (query.isBlank()) return

            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
                .setSessionToken(token)
                .setTypeFilter(TypeFilter.REGIONS)
                .build()

            client.findAutocompletePredictions(request)
                .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                    showResult.invoke(dataMapper.map(response.autocompletePredictions.toList()))
//                    locationsCommunication.show(uiMapper.map(dataMapper.map(response.autocompletePredictions.toList())))
                }
                .addOnFailureListener { exception: Exception? ->
                    val error = exception ?: UnknownException()
                    showResult.invoke(dataMapper.map(error))
//                    locationsCommunication.show(uiMapper.map(dataMapper.map(error)))
                }
        }
    }

    class Mock : SearchLocationDataSource {
        override fun startSession() = Unit

        override suspend fun getPredictions(
            query: String,
            locationsCommunication: LocationsCommunication.Update,
            showResult: (List<SearchItemData>) -> Unit,
        ) {
            showResult.invoke(listOf(SearchItemData.Location("11,22", "Mumbai", "Some country"),
                SearchItemData.Location("55,11", "Hyderabad", "Other country"))
            )
        }
    }
}
