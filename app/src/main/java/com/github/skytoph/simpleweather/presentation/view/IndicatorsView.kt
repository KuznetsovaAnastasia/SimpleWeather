package com.github.skytoph.simpleweather.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.skytoph.simpleweather.R


class IndicatorsView : ConstraintLayout {
    private lateinit var timeTextView: TextView
    private lateinit var uvTextView: TextView
    private lateinit var rainPercentTextView: TextView
    private lateinit var aqTextView: TextView

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
            .inflate(R.layout.indicators, this, true)

        background = resources.getDrawable(R.drawable.rectangle_rounded_11)

        setPadding(
            resources.getDimensionPixelSize(R.dimen.indicators_view_padding_start),
            resources.getDimensionPixelSize(R.dimen.indicators_view_padding_top),
            resources.getDimensionPixelSize(R.dimen.indicators_view_padding_end),
            resources.getDimensionPixelSize(R.dimen.indicators_view_padding_bottom)
        )

        timeTextView = findViewById(R.id.indicator_time_value)
        uvTextView = findViewById(R.id.indicator_uv_value)
        rainPercentTextView = findViewById(R.id.indicator_rain_percent_value)
        aqTextView = findViewById(R.id.indicator_aq_value)
    }
}