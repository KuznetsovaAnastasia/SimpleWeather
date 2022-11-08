package com.github.skytoph.simpleweather.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.presentation.weather.model.WarningUi

class WarningRainView : WarningView {
    private var forecastImage: ImageView
    private var rainPercentText: TextView
    private var expTimeText: TextView
    private var expTimeLabel: TextView
    private var warningText: TextView

    //region constructors
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)
    //endregion

    init {
        inflate(R.layout.view_warning_rain)

        minHeight = resources.getDimensionPixelSize(R.dimen.warning_view_min_height)
        setPadding(0, 0, 0, resources.getDimensionPixelSize(R.dimen.warning_view_padding_bottom))

        forecastImage = findViewById(R.id.warning_forecast_image)
        rainPercentText = findViewById(R.id.warning_rain_percent_value)
        expTimeText = findViewById(R.id.warning_exp_time_value)
        expTimeLabel = findViewById(R.id.warning_exp_time_title)
        warningText = findViewById(R.id.warning_text)
    }

    override fun show(warning: WarningUi) {
        visibility = View.VISIBLE
        warning.show(warningText, rainPercentText, expTimeText, expTimeLabel)
    }
}