package com.github.skytoph.simpleweather.presentation.view

import android.graphics.PointF
import com.google.android.material.math.MathUtils
import java.util.concurrent.TimeUnit

class HorizonUtils {
    companion object {
        fun getSunPosition(p0: PointF, p1: PointF, p2: PointF, t: Float): PointF {
            val p01 = lerp(p0, p1, t)
            val p12 = lerp(p1, p2, t)
            return lerp(p01, p12, t)
        }

        private fun lerp(p0: PointF, p1: PointF, t: Float) = PointF().apply {
            x = MathUtils.lerp(p0.x, p1.x, t)
            y = MathUtils.lerp(p0.y, p1.y, t)
        }

        fun sunRaised(currentTimeHours: Int, sunriseTimeHours: Int): Boolean {
            return currentTimeHours > sunriseTimeHours
        }

        fun getT(currentTimeHours: Int, sunriseTimeHours: Int, sunsetTimeHours: Int): Float{
            var t = 0f
            val daylightLength  = sunsetTimeHours-sunriseTimeHours

            if(sunRaised(currentTimeHours, sunriseTimeHours)){
                t = (currentTimeHours - sunriseTimeHours) / daylightLength.toFloat()
            }
            else{
                val nightLength = TimeUnit.DAYS.toHours(1) - daylightLength
                t = (currentTimeHours - sunriseTimeHours) / nightLength.toFloat()
            }

            return t
        }
    }
}