package com.github.skytoph.simpleweather.core.presentation.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.core.content.res.ResourcesCompat
import com.github.skytoph.simpleweather.R
import com.google.android.material.button.MaterialButton

class MessageButton : MaterialButton {
    private var listener = OnClickListener {}
    private var clickedDrawable: Drawable? = null
    private var clickedText: String = ""
    private var clickedColor: ColorStateList? = null

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
                getColorStateList(R.styleable.MessageButton_contentColor).let { contentColor ->
                    iconTint = contentColor
                    setTextColor(contentColor)
                }

                clickedText = getString(R.styleable.MessageButton_clickedText) ?: ""
                clickedDrawable = getDrawable(R.styleable.MessageButton_clickedDrawableEnd)
                clickedColor = getColorStateList(R.styleable.MessageButton_clickedContentColor)
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

    fun setClickedStyle() {
        isEnabled = false
        text = clickedText
        icon = clickedDrawable

        backgroundTintList = ResourcesCompat.getColorStateList(resources, R.color.green, null)
        iconTint = clickedColor
        setTextColor(clickedColor)
        super.setOnClickListener(null)
    }
}