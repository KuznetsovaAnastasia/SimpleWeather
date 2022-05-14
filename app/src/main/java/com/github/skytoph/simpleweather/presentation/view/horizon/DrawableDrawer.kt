package com.github.skytoph.simpleweather.presentation.view.horizon

import android.graphics.Canvas
import android.graphics.PointF
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes

class DrawableDrawer(
    private val resourceProvider: ResourceProvider
) {
    fun draw(
        canvas: Canvas,
        position: PointF,
        @DrawableRes drawableId: Int,
        @DimenRes sizeId: Int
    ) {
        canvas.save()

        val sun = resourceProvider.getDrawable(drawableId)
        val sunSize = resourceProvider.getDimensionPixel(sizeId)
        sun?.setBounds(0, 0, sunSize, sunSize)

        canvas.translate(position.x - sunSize / 2, position.y - sunSize / 2)
        sun?.draw(canvas)

        canvas.restore()
    }
}