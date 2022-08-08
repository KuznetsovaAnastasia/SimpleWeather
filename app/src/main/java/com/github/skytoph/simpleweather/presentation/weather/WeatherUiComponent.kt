package com.github.skytoph.simpleweather.presentation.weather

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.github.skytoph.simpleweather.core.Matcher
import com.github.skytoph.simpleweather.core.presentation.view.horizon.HorizonView

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

    data class Warning(
        private val event: String,
        private val startTime: String,
        private val pop: String,
    ) : WeatherUiComponent, Matcher<Warning> {

        fun show(warningTextView: TextView, popTextView: TextView, startTimeTextView: TextView) {
            warningTextView.text = event
            popTextView.text = pop
            startTimeTextView.text = startTime
        }

        override fun matches(item: Warning): Boolean = event == item.event // todo add id
        override fun contentMatches(item: Warning): Boolean = event == item.event
    }

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

}