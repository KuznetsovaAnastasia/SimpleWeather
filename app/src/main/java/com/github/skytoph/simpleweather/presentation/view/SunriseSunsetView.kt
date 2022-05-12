package com.github.skytoph.simpleweather.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.skytoph.simpleweather.R

class SunriseSunsetView : ConstraintLayout {
    private lateinit var horizonView: HorizonView
    private lateinit var dayLengthText: TextView
    private lateinit var daylightText: TextView

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
            .inflate(R.layout.sunrise_sunset, this, true)

        background = resources.getDrawable(R.drawable.rectangle_rounded_11)
        minHeight = resources.getDimensionPixelSize(R.dimen.warning_view_min_height)
        setPadding(0, 0, 0, resources.getDimensionPixelSize(R.dimen.horizon_view_padding_bottom))

        horizonView = findViewById(R.id.horizon_view)
        dayLengthText = findViewById(R.id.day_length_value)
        daylightText = findViewById(R.id.daylight_value)
    }
}