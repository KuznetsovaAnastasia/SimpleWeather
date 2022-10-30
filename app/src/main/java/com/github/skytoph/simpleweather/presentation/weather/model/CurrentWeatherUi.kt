package com.github.skytoph.simpleweather.presentation.weather.model

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes

data class CurrentWeatherUi(
    private val city: String,
    private val degree: String,
    @DrawableRes private val image: Int,
) {

    fun show(weatherImageView: ImageView, cityTextView: TextView, degreeTextView: TextView) {
        weatherImageView.setImageResource(image)
        cityTextView.text = city
        degreeTextView.text = degree
    }
}