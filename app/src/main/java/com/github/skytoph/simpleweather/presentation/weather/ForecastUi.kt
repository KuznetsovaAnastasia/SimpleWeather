package com.github.skytoph.simpleweather.presentation.weather

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.github.skytoph.simpleweather.core.Matcher

data class ListUi(
    private val hourly: List<ForecastUi.Hourly>,
    private val daily: List<ForecastUi.Daily>,
    private val warnings: List<WarningUi>,
) {

    fun show(submitLists: (List<WarningUi>, List<ForecastUi.Hourly>, List<ForecastUi.Daily>) -> Unit) =
        submitLists(warnings, hourly, daily)
}

sealed class ForecastUi(
    private val time: String,
    private val temp: String,
    @DrawableRes private val weatherImage: Int,
    private val precipitationProb: String,
) {

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

    data class Hourly(
        private val time: String,
        private val temp: String,
        private val weatherImage: Int,
        private val precipitationProb: String,
    ) : ForecastUi(time, temp, weatherImage, precipitationProb), Matcher<Hourly> {

        override fun matches(item: Hourly): Boolean = time == item.time
        override fun contentMatches(item: Hourly): Boolean = equals(item)
    }

    data class Daily(
        private val day: String,
        private val maxTemp: String,
        private val minTemp: String,
        private val weatherImage: Int,
        private val precipitationProb: String,
    ) : ForecastUi(day, maxTemp, weatherImage, precipitationProb), Matcher<Daily> {

        fun show(tempMinTextView: TextView) {
            tempMinTextView.text = minTemp
        }

        override fun matches(item: Daily): Boolean = day == item.day
        override fun contentMatches(item: Daily): Boolean = equals(item)
    }
}