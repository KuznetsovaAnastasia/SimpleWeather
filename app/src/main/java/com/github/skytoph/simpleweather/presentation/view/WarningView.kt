package com.github.skytoph.simpleweather.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.skytoph.simpleweather.R

class WarningView : ConstraintLayout {
    private lateinit var imageIcon: ImageView
    private lateinit var rainPercentText: TextView

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
        imageIcon = findViewById(R.id.image_icon_warning)
        rainPercentText = findViewById(R.id.rain_percent)

        background = resources.getDrawable(R.drawable.rectangle_rounded_11)
        minHeight = resources.getDimensionPixelSize(R.dimen.warning_view_min_height)
        setPadding(0, 0, 0, resources.getDimensionPixelSize(R.dimen.warning_view_padding_bottom))
    }
}