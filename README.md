# Simple Weather App
**Simple Weather App** is a simple Android application that fetches 7-day and 24-hour weather forecast data using the [OpenWeatherMap API](https://openweathermap.org/). It provides weather information for the user's current location or allows the user to search for a specific location using a search bar. The app uses The Place Autocomplete service from the [Google Places API](https://developers.google.com/maps/documentation/places/) to fetch location data.

<a href='https://play.google.com/store/apps/details?id=com.github.skytoph.simpleweather&pcampaignid=pcampaignidMKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png' width="280"/></a>

## Screenshots
<img src="https://user-images.githubusercontent.com/44202107/248884070-edef3adb-1073-4cb8-be40-f55d08df9323.jpg" width="250"> <img src="https://user-images.githubusercontent.com/44202107/248884080-b17914f6-b2e5-43ce-9ffe-f4f3d08e4b89.jpg" width="250"> <img src="https://user-images.githubusercontent.com/44202107/248884083-bea3f78e-cebd-44ff-a0ee-3ad22c55f284.jpg" width="250">

## Design
The design of the Simple Weather App is based on [the work by **Pavan Kamal**](https://www.figma.com/community/file/1019097765306863573/Minimal-Weather-App), licensed under the [CC BY 4.0 license](https://creativecommons.org/licenses/by/4.0/). The original design was modified to meet the technical requirements and constraints of the app.

## Features
- detailed weather data including temperature, probability of precipitation, uv index, air quality, and weather conditions
- automatic location detection and ability to search for a location of interest 
- current weather conditions data for the user's location
- 7-day and 24-hour weather forecast

## Technologies
The Weather App is built using the following technologies:

- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) is used to manage and persist UI-related data across configuration changes.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) is used to observe and react to data changes, providing updated weather information to the UI.
- [View Binding](https://developer.android.com/topic/libraries/view-binding) simplifies the process of interacting with UI elements from code by generating binding classes.
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html): Coroutines are used for managing asynchronous programming, making network requests, and updating the UI.
- [Retrofit](https://square.github.io/retrofit) is used as a REST Client for making API calls.
- [Moshi](https://github.com/google/gson) is used for parsing JSON responses received from the server into Java objects.
- [Realm](https://realm.io/) is used as a database for storing and retrieving weather data locally.
- [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager) is used for managing the background job of updating the weather forecast.
- [Dagger Hilt](https://dagger.dev/hilt) is used for dependency injection.
- [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) is used to report any crashes or errors that occur within the app.

## Architecture
Simple Weather App follows the [MVVM (Model-View-ViewModel)](https://developer.android.com/topic/architecture#recommended-app-arch) architecture pattern and uses a single-activity pattern with navigation based on [Fragment Manager](https://developer.android.com/guide/fragments/fragmentmanager).

## License
```
MIT License

Copyright (c) 2023 Anastasiia Kuznetsova

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
