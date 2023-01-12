package com.github.skytoph.simpleweather.core.presentation.view.horizon

import android.graphics.PointF
import com.google.android.material.math.MathUtils
import java.util.concurrent.TimeUnit
import kotlin.math.min

class HorizonCurveCalculator(private val points: List<PointF>) {

    fun getSunPosition(t: Float): PointF = if (t < 0) {
        getSunPosition(points[0], points[1], points[2], Math.abs(t))
    } else {
        getSunPosition(points[2], points[3], points[4], t)
    }

    private fun getSunPosition(p0: PointF, p1: PointF, p2: PointF, t: Float): PointF {
        val p01 = lerp(p0, p1, t)
        val p12 = lerp(p1, p2, t)
        return lerp(p01, p12, t)
    }

    private fun lerp(p0: PointF, p1: PointF, t: Float) = PointF().apply {
        x = MathUtils.lerp(p0.x, p1.x, t)
        y = MathUtils.lerp(p0.y, p1.y, t)
    }

    private fun sunRaised(currentTimeHours: Int, sunriseTimeHours: Int): Boolean =
        currentTimeHours > sunriseTimeHours

    fun getT(currentTimeHours: Int, sunriseTimeHours: Int, sunsetTimeHours: Int): Float {
        val daylightLength = sunsetTimeHours - sunriseTimeHours

        return if (sunRaised(currentTimeHours, sunriseTimeHours)) {
            (currentTimeHours - sunriseTimeHours) / daylightLength.toFloat()
        } else {
            val nightLength = TimeUnit.DAYS.toHours(1) - daylightLength
            (currentTimeHours - sunriseTimeHours) / nightLength.toFloat()
        }
    }

    fun calculatePositions(width: Float, height: Float, halfSunSize: Float) {
        val graphMarginRight = width / 5.6f

        val viewHeight = min(height, width / 2.5f)

        val graphMarginTop = (viewHeight / 3.1).toFloat()

        val graphWidth = (width - graphMarginRight - halfSunSize)
        val graphHeight = (viewHeight - graphMarginTop - halfSunSize)


        val nightWidth = (graphWidth / 4.8f)
        val nightHeight = (graphHeight / 3)
        val dayWidth = (graphWidth - nightWidth)
        val dayHeight = graphHeight - nightHeight

        val nightStepX = nightWidth / 2
        val nightStepY = (nightHeight * 2)
        val dayStepX = dayWidth / 2
        val dayStepY = (dayHeight * 2)

        points[0].apply { //pNightStart
            x = halfSunSize
            y = dayHeight + graphMarginTop
        }
        points[1].apply { //pMidnight
            x = points[0].x + nightStepX
            y = points[0].y + nightStepY
        }
        points[2].apply { //pSunrise
            x = points[1].x + nightStepX
            y = points[0].y
        }
        points[3].apply { //pMidday
            x = points[2].x + dayStepX
            y = points[0].y - dayStepY
        }
        points[4].apply { //pSunset
            x = points[3].x + dayStepX
            y = points[0].y
        }

        val lineHeight = dayHeight * 0.88f
        points[5].apply { //pSunriseTime
            x = points[2].x
            y = points[2].y - lineHeight
        }
        points[6].apply { //pSunsetTime
            x = points[4].x
            y = points[4].y - lineHeight
        }
        points[7].apply { x = width; y = points[0].y }
    }
}