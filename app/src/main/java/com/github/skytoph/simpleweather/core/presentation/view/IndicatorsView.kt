package com.github.skytoph.simpleweather.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextClock
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.presentation.weather.model.IndicatorsUi


class IndicatorsView : ConstraintLayout {
    private var timeView: TextClock
    private var uvTextView: TextView
    private var popTextView: TextView
    private var aqTextView: TextView

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
            .inflate(R.layout.view_indicators, this, true)

        setPadding(
            0,
            resources.getDimensionPixelSize(R.dimen.indicators_view_padding_top),
            0,
            resources.getDimensionPixelSize(R.dimen.indicators_view_padding_bottom)
        )

        timeView = findViewById(R.id.indicator_time_value)
        uvTextView = findViewById(R.id.indicator_uv_value)
        popTextView = findViewById(R.id.indicator_rain_percent_value)
        aqTextView = findViewById(R.id.indicator_aq_value)
    }

    fun show(weather: IndicatorsUi) {
        visibility = View.VISIBLE
        weather.show(timeView, uvTextView, popTextView, aqTextView)
    }
}