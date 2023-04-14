package com.github.skytoph.simpleweather.core.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.transition.TransitionManager
import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.presentation.weather.model.WarningUi


class WarningInfoView : WarningView {

    //region constructors
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs)
    //endregion

    private var expTimeText: TextView
    private var expTimeLabel: TextView
    private var warningText: TextView
    private var descriptionText: TextView

    init {
        inflate(R.layout.view_warning)

        setPadding(0, 0, 0, resources.getDimensionPixelSize(R.dimen.warning_view_padding_bottom))

        expTimeText = findViewById(R.id.warning_exp_time_value)
        expTimeLabel = findViewById(R.id.warning_exp_time_title)
        warningText = findViewById(R.id.warning_text)
        descriptionText = findViewById(R.id.warning_description)

        descriptionText.maxLines = DESCRIPTION_MAX_LINES

        setOnClickListener {
            descriptionText.maxLines =
                if (descriptionText.maxLines == DESCRIPTION_MAX_LINES) Integer.MAX_VALUE else DESCRIPTION_MAX_LINES
            TransitionManager.beginDelayedTransition(this as ViewGroup)
        }
    }

    override fun show(warning: WarningUi) {
        warning.show(warningText, descriptionText, expTimeText, expTimeLabel)
        descriptionText.maxLines = DESCRIPTION_MAX_LINES
    }

    private companion object {
        const val DESCRIPTION_MAX_LINES = 2
    }
}