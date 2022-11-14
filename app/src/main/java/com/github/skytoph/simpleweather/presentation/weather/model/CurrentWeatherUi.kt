package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.github.skytoph.simpleweather.R

data class CurrentWeatherUi(
    private val city: String,
    private val degree: String,
    private val updated: String,
    @DrawableRes private val image: Int,
) {

    fun show(
        weatherImageView: ImageView,
        cityTextView: TextView,
        degreeTextView: TextView,
        updatedTextView: TextView,
    ) {
        weatherImageView.setImageResource(image)
        cityTextView.text = city
        degreeTextView.text = degree
        updatedTextView.text = updatedTextView.context.getString(R.string.last_update_time, updated)
    }
}