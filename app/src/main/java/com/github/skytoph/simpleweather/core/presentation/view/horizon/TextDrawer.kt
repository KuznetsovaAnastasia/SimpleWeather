package com.github.skytoph.simpleweather.core.presentation.view.horizon

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import androidx.annotation.ColorInt
import androidx.annotation.StringRes

class TextDrawer(
    private val paint: Paint,
    private val resourceProvider: ResourceProvider,
) {
    fun prepare() {
        paint.apply {
            reset()
            isAntiAlias = true
            style = Paint.Style.FILL
        }
    }

    fun setup(fontFamily: String, @ColorInt colorInt: Int, textSizeValue: Float) {
        paint.apply {
            textSize = textSizeValue
            color = colorInt
            typeface = Typeface.create(fontFamily, Typeface.NORMAL)
            textAlign = Paint.Align.CENTER
        }
    }

    fun drawText(canvas: Canvas, positionX: Float, positionY: Float, @StringRes id: Int) {
        val text = resourceProvider.string(id)
        drawText(canvas, positionX, positionY, text)
    }

    fun drawText(canvas: Canvas, positionX: Float, positionY: Float, text: String) {
        canvas.drawText(text, positionX, positionY, paint)
    }
}