package com.github.skytoph.simpleweather.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.presentation.WeatherUiComponent

class WarningView : ConstraintLayout {
    private var forecastImage: ImageView
    private var rainPercentText: TextView
    private var expTimeText: TextView
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
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.warning, this, true)

        background = ResourcesCompat.getDrawable(resources, R.drawable.rectangle_rounded_11, null)
        minHeight = resources.getDimensionPixelSize(R.dimen.warning_view_min_height)
        setPadding(0, 0, 0, resources.getDimensionPixelSize(R.dimen.warning_view_padding_bottom))

        forecastImage = findViewById(R.id.warning_forecast_image)
        rainPercentText = findViewById(R.id.warning_rain_percent_value)
        expTimeText = findViewById(R.id.warning_exp_time_value)
        warningText = findViewById(R.id.warning_text)
    }

    fun show(warning: WeatherUiComponent.Warning) {
        visibility = View.VISIBLE
        warning.show(warningText, rainPercentText, expTimeText)
    }
}