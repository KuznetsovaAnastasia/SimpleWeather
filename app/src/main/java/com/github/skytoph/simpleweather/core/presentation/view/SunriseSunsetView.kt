package com.github.skytoph.simpleweather.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.presentation.view.horizon.HorizonView
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent

class SunriseSunsetView : RelativeLayout {
    private var horizonView: HorizonView
    private var dayLengthTextView: TextView
    private var daylightTextView: TextView

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
            .inflate(R.layout.view_sunrise_sunset, this, true)

        gravity = Gravity.START

        minimumHeight = resources.getDimensionPixelSize(R.dimen.warning_view_min_height)
        setPadding(
            resources.getDimensionPixelSize(R.dimen.sunrise_sunset_view_padding_start),
            resources.getDimensionPixelSize(R.dimen.sunrise_sunset_view_padding_top),
            resources.getDimensionPixelSize(R.dimen.sunrise_sunset_view_padding_end),
            resources.getDimensionPixelSize(R.dimen.sunrise_sunset_view_padding_bottom)
        )

        horizonView = findViewById(R.id.horizon_view)
        dayLengthTextView = findViewById(R.id.day_length_value)
        daylightTextView = findViewById(R.id.daylight_value)
    }

    fun show(weather: WeatherUiComponent.Horizon) {
        visibility = View.VISIBLE
        weather.show(horizonView, dayLengthTextView, daylightTextView)
    }
}