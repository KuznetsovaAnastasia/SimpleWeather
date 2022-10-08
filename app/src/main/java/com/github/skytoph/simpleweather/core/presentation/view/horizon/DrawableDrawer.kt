package com.github.skytoph.simpleweather.core.presentation.view.horizon

import android.graphics.Canvas
import android.graphics.PointF
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.github.skytoph.simpleweather.core.provider.ResourceProvider

class DrawableDrawer(
    private val resources: ResourceProvider,
) {
    fun draw(
        canvas: Canvas,
        position: PointF,
        @DrawableRes drawableId: Int,
        @DimenRes sizeId: Int,
    ) {
        canvas.save()

        val sun = resources.drawable(drawableId)
        val sunSize = resources.dimensionPixel(sizeId)
        sun?.setBounds(0, 0, sunSize, sunSize)

        canvas.translate(position.x - sunSize / 2, position.y - sunSize / 2)
        sun?.draw(canvas)

        canvas.restore()
    }
}