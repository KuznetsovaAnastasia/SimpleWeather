package com.github.skytoph.simpleweather.core.presentation.view.horizon

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import androidx.annotation.ColorRes

class CurveDrawer(
    private val paint: Paint,
    private val resourceProvider: ResourceProvider,
) {

    fun drawCurve(
        canvas: Canvas,
        point1: PointF,
        point2: PointF,
        point3: PointF,
        @ColorRes colorId: Int,
    ) {
        val path = Path()

        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true // smooth drawing
        paint.color = resourceProvider.color(colorId)

        path.reset()
        path.moveTo(point1.x, point1.y)
        path.quadTo(point2.x, point2.y, point3.x, point3.y)

        canvas.drawPath(path, paint)
    }
}