package com.github.skytoph.simpleweather.presentation.weather

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.github.skytoph.simpleweather.core.Matcher
import com.github.skytoph.simpleweather.core.presentation.view.horizon.HorizonView
import com.github.skytoph.simpleweather.core.presentation.view.visibility.Visibility

sealed interface WeatherUiComponent {

    data class Current(
        private val city: String,
        private val degree: String,
        @DrawableRes private val image: Int,
    ) : WeatherUiComponent {

        fun show(weatherImageView: ImageView, cityTextView: TextView, degreeTextView: TextView) {
            weatherImageView.setImageResource(image)
            cityTextView.text = city
            degreeTextView.text = degree
        }
    }

    sealed class Warning(
        private val title: String,
        private val description: String,
        private val startTime: String,
    ) : WeatherUiComponent, Matcher<Warning> {

        fun show(
            titleTextView: TextView,
            descriptionTextView: TextView,
            startTimeTextView: TextView,
        ) {
            titleTextView.text = title
            descriptionTextView.text = description
            startTimeTextView.text = startTime
            Visibility.Visible().apply(descriptionTextView)
        }

        override fun matches(item: Warning): Boolean = title == item.title
        override fun contentMatches(item: Warning): Boolean =
            title == item.title && description == item.description && startTime == item.startTime

        fun isDescribed(): Boolean = description.isNotBlank()
    }

    data class WarningBasic(
        private val event: String,
        private val description: String,
        private val startTime: String,
    ) : Warning(event, description, startTime)

    data class WarningRain(
        private val event: String,
        private val pop: String,
        private val startTime: String,
        private val weatherRain: Int,
    ) : Warning(event, pop, startTime)

    data class Indicator(
        private val time: String,
        private val uv: String,
        private val pop: String,
        private val aq: String,
    ) : WeatherUiComponent {

        fun show(
            timeTextView: TextView,
            uvTextView: TextView,
            popTextView: TextView,
            aqTextView: TextView,
        ) {
            timeTextView.text = time
            uvTextView.text = uv
            popTextView.text = pop
            aqTextView.text = aq
        }
    }

    data class Horizon(
        private val sunrise: String,
        private val sunset: String,
        private val dayLength: String,
        private val remainingDaylight: String,
        private val sunPositionValue: Double,
    ) : WeatherUiComponent {

        fun show(
            horizonView: HorizonView,
            dayLengthTextView: TextView,
            daylightTextView: TextView,
        ) {
            horizonView.setValues(sunrise, sunset, sunPositionValue)
            dayLengthTextView.text = dayLength
            daylightTextView.text = remainingDaylight
        }
    }

    sealed class Forecast(
        private val time: String,
        private val temp: String,
        @DrawableRes private val weatherImage: Int,
        private val precipitationProb: String,
    ) : WeatherUiComponent {
        fun show(
            weatherImageView: ImageView,
            timeTextView: TextView,
            tempTextView: TextView,
            popTextView: TextView,
        ) {
            weatherImageView.setImageResource(weatherImage)
            timeTextView.text = time
            tempTextView.text = temp
            popTextView.text = precipitationProb
        }
    }

    data class HourlyForecast(
        private val time: String,
        private val temp: String,
        private val weatherImage: Int,
        private val precipitationProb: String,
    ) : Forecast(time, temp, weatherImage, precipitationProb), Matcher<HourlyForecast> {

        override fun matches(item: HourlyForecast): Boolean = time == item.time
        override fun contentMatches(item: HourlyForecast): Boolean = equals(item)
    }

    data class DailyForecast(
        private val day: String,
        private val maxTemp: String,
        private val minTemp: String,
        private val weatherImage: Int,
        private val precipitationProb: String,
    ) : Forecast(day, maxTemp, weatherImage, precipitationProb), Matcher<DailyForecast> {

        fun show(tempMinTextView: TextView) {
            tempMinTextView.text = minTemp
        }

        override fun matches(item: DailyForecast): Boolean = day == item.day
        override fun contentMatches(item: DailyForecast): Boolean = equals(item)
    }
}