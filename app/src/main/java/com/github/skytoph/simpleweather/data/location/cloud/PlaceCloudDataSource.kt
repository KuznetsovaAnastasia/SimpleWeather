package com.github.skytoph.simpleweather.data.location.cloud

interface PlaceCloudDataSource {

    suspend fun getPlace(coordinates: Pair<Double, Double>): PlaceCloud

    class Base(
        private val service: LocationService,
    ) : PlaceCloudDataSource {

        override suspend fun getPlace(coordinates: Pair<Double, Double>): PlaceCloud =
            service.getLocation(coordinates.first.toString(), coordinates.second.toString())
                .execute().body()!![0]
    }

//    class Old(
//        private val client: PlacesClient,
//        private val mapper: PlaceToCloudMapper,
//    ) : PlaceCloudDataSource {
//
//        override suspend fun getPlace(id: String): PlaceCloud {
//            val fields = listOf(Place.Field.ID, Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG)
//            val request = FetchPlaceRequest.newInstance(id, fields)
//            val response = client.fetchPlace(request).await()
//
//            return mapper.map(response.place)
//        }
//    }
}