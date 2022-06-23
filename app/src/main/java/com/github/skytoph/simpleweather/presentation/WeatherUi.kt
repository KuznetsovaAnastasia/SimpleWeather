package com.github.skytoph.simpleweather.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent.*
import com.github.skytoph.simpleweather.presentation.view.horizon.HorizonView

sealed class WeatherUi {

    open fun show(communication: StateCommunication) = Unit

    data class Fail(private val message: String) : WeatherUi() {

        override fun show(communication: StateCommunication) =
            communication.show(State.Fail(message))
    }

    data class Success(
        private val current: Current,
        private val warning: List<Warning>,
        private val indicator: Indicator,
        private val horizon: Horizon
    ) : WeatherUi() {

        override fun show(communication: StateCommunication) =
            communication.show(State.Base(current, warning, indicator, horizon))
    }
}

sealed interface WeatherUiComponent {

    data class Current(
        private val city: String,
        private val degree: String,
        @DrawableRes private val image: Int
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
        private val pop: String
    ) : WeatherUiComponent {

        fun show(warningTextView: TextView, popTextView: TextView, startTimeTextView: TextView) {
            warningTextView.text = event
            popTextView.text = pop
            startTimeTextView.text = startTime
        }

        fun matches(item: Warning): Boolean = event == item.event // todo add id
        fun contentMatches(item: Warning): Boolean = event == item.event
    }

    data class Indicator(
        private val time: String,
        private val uv: String,
        private val pop: String,
        private val aq: String
    ) : WeatherUiComponent {

        fun show(
            timeTextView: TextView,
            uvTextView: TextView,
            popTextView: TextView,
            aqTextView: TextView
        ){
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
        private val sunPositionValue: Double
    ) : WeatherUiComponent {

        fun show(horizonView: HorizonView, dayLengthTextView: TextView, daylightTextView: TextView) {
            horizonView.setValues(sunrise, sunset, sunPositionValue)
            dayLengthTextView.text = dayLength
            daylightTextView.text = remainingDaylight
        }
    }

}