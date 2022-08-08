package com.github.skytoph.simpleweather.core.presentation.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View.OnClickListener
import com.github.skytoph.simpleweather.R
import com.google.android.material.button.MaterialButton

class MessageButton : MaterialButton {
    private var listener = OnClickListener {}
    private var clickedDrawable: Drawable? = null
    private lateinit var clickedText: String

    //region constructors
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    //endregion

    private fun init(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.MessageButton, 0, 0).apply {
            try {
                text = getString(R.styleable.MessageButton_buttonText) ?: ""
                icon = getDrawable(R.styleable.MessageButton_buttonDrawableEnd)

                clickedText = getString(R.styleable.MessageButton_clickedText) ?: ""
                clickedDrawable = getDrawable(R.styleable.MessageButton_clickedDrawableEnd)
            } finally {
                recycle()
            }
        }
    }

    init {
        super.setOnClickListener { view ->
            listener.onClick(view)
            setClickedStyle()
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        l?.let { listener = l }
    }

    private fun setClickedStyle() {
        isEnabled = false
        text = clickedText
        icon = clickedDrawable

        val backgroundColor = resources.getColorStateList(R.color.green)
        val textColor = resources.getColor(R.color.white)
        val iconColor = resources.getColorStateList(R.color.white)

        backgroundTintList = backgroundColor
        iconTint = iconColor
        setTextColor(textColor)
    }

}