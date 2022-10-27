package com.github.skytoph.simpleweather.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.presentation.weather.CurrentWeatherUi

class LocationView : LinearLayout {

    private var weatherImage: ImageView
    private var cityTextView: TextView
    private var degreesTextView: TextView

    //region constructors
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)
    //endregion

    init {
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.view_forecast, this, true)

        weatherImage = findViewById(R.id.location_weather_image)
        cityTextView = findViewById(R.id.location_city)
        degreesTextView = findViewById(R.id.location_degrees)

        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    fun show(weather: CurrentWeatherUi) {
        visibility = View.VISIBLE
        weather.show(weatherImage, cityTextView, degreesTextView)
    }
}