package com.github.skytoph.simpleweather.presentation.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.withStyledAttributes
import com.github.skytoph.simpleweather.R

class HorizonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private val path = Path()

    private val pNightStart = PointF()
    private val pMidnight = PointF()
    private val pSunrise = PointF()
    private val pMidday = PointF()
    private val pSunset = PointF()
    private val pSunriseTime = PointF()
    private val pSunsetTime = PointF()

    private lateinit var fontFamily: String
    private lateinit var sunsetTimeValue: String
    private lateinit var sunriseTimeValue: String

    private var labelTextSize = 0f
    private var timeTextSize = 0f

    private var t = 0f

    init {
        context.withStyledAttributes(attrs, R.styleable.HorizonView) {
            sunriseTimeValue = getString(R.styleable.HorizonView_sunrise_time).toString()
            sunsetTimeValue = getString(R.styleable.HorizonView_sunset_time).toString()
            fontFamily = getString(R.styleable.HorizonView_fontFamily).toString()
            labelTextSize = getDimension(R.styleable.HorizonView_textLabelSize, 29f)
            timeTextSize = getDimension(R.styleable.HorizonView_textTimeSize, 34f)
            t = getFloat(R.styleable.HorizonView_t, 0f)
        }
        minimumHeight = resources.getDimensionPixelSize(R.dimen.horizon_min_height)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = resources.getDimensionPixelSize(R.dimen.horizon_desired_width)
        val desiredHeight = resources.getDimensionPixelSize(R.dimen.horizon_desired_height)

        val width = measureDimension(desiredWidth, widthMeasureSpec)
        val height = measureDimension(desiredHeight, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int{
        var result = 0

        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        when(specMode){
            MeasureSpec.EXACTLY -> result = specSize
            MeasureSpec.AT_MOST -> result = Math.min(desiredSize, specSize)
            MeasureSpec.UNSPECIFIED -> result = desiredSize
        }

        return result
    }

    private fun computeHorizonPoints() {
        val halfSunSize = resources.getDimension(R.dimen.horizon_sun_size) / 2

        val graphMarginRight = width / 5.6f
        val graphMarginLeft = halfSunSize
        val graphMarginBottom = halfSunSize

        val viewHeight = Math.min(height.toFloat(), width/2.5f)

        val graphMarginTop = (viewHeight / 3.1).toFloat()

        val graphWidth = (width - graphMarginRight - graphMarginLeft)
        val graphHeight = (viewHeight - graphMarginTop - graphMarginBottom)


        val nightWidth = (graphWidth / 4.8f)
        val nightHeight = (graphHeight / 3)
        val dayWidth = (graphWidth - nightWidth)
        val dayHeight = graphHeight - nightHeight

        val nightStepX = nightWidth / 2
        val nightStepY = (nightHeight * 2)
        val dayStepX = dayWidth / 2
        val dayStepY = (dayHeight * 2)

        pNightStart.apply {
            x = graphMarginLeft
            y = dayHeight + graphMarginTop
        }
        pMidnight.apply {
            x = pNightStart.x + nightStepX
            y = pNightStart.y + nightStepY
        }
        pSunrise.apply {
            x = pMidnight.x + nightStepX
            y = pNightStart.y
        }
        pMidday.apply {
            x = pSunrise.x + dayStepX
            y = pNightStart.y - dayStepY
        }
        pSunset.apply {
            x = pMidday.x + dayStepX
            y = pNightStart.y
        }

        val lineHeight = dayHeight * 0.88f
        pSunriseTime.apply {
            x = pSunrise.x
            y = pSunrise.y - lineHeight
        }
        pSunsetTime.apply {
            x = pSunset.x
            y = pSunset.y - lineHeight
        }
    }

    override fun onDraw(canvas: Canvas) {
        computeHorizonPoints()

        drawDayAndNight(canvas)
        drawLines(canvas)
        drawText(canvas)
        drawSun(canvas)
    }

    private fun drawSun(canvas: Canvas) {
        canvas.save()

        val sun = ResourcesCompat.getDrawable(resources, R.drawable.weather_sun, null)
        val sunSize = resources.getDimension(R.dimen.horizon_sun_size)
        sun?.setBounds(0, 0, sunSize.toInt(), sunSize.toInt())

        val pSun = getSunPosition(t)

        canvas.translate(pSun.x - sunSize / 2, pSun.y - sunSize / 2)
        sun?.draw(canvas)

        canvas.restore()
    }

    private fun getSunPosition(t: Float): PointF = if (t < 0) {
        HorizonUtils.getSunPosition(pNightStart, pMidnight, pSunrise, Math.abs(t))
    } else {
        HorizonUtils.getSunPosition(pSunrise, pMidday, pSunset, t)
    }

    private fun drawDayAndNight(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true // smooth drawing

        drawNight(canvas)
        drawDay(canvas)
    }

    private fun drawNight(canvas: Canvas) {
        val nightColor = ResourcesCompat.getColor(resources, R.color.blue, null)
        paint.color = nightColor
        path.moveTo(pNightStart.x, pNightStart.y)
        path.quadTo(pMidnight.x, pMidnight.y, pSunrise.x, pSunrise.y)
        canvas.drawPath(path, paint)
    }

    private fun drawDay(canvas: Canvas) {
        val dayColor = ResourcesCompat.getColor(resources, R.color.light_blue, null)
        paint.color = dayColor
        path.reset()
        path.moveTo(pSunrise.x, pSunrise.y)
        path.quadTo(pMidday.x, pMidday.y, pSunset.x, pSunset.y)
        canvas.drawPath(path, paint)
    }

    private fun drawLines(canvas: Canvas) {
        paint.setupForDashLines()

        canvas.drawLine(
            pNightStart.x, pNightStart.y,
            width.toFloat(), pNightStart.y, paint
        )

        canvas.drawLine(
            pSunrise.x, pSunrise.y,
            pSunriseTime.x, pSunriseTime.y, paint
        )

        canvas.drawLine(
            pSunset.x, pSunset.y,
            pSunsetTime.x, pSunsetTime.y, paint
        )
    }

    private fun Paint.setupForDashLines() {
        reset()
        val dashLineGap = resources.getDimension(R.dimen.horizon_dash_line_gap)
        val dashLineStrokeWidth = resources.getDimension(R.dimen.horizon_dash_line_width)
        val dashLineOffset = dashLineGap / 2
        color = ResourcesCompat.getColor(resources, R.color.light_gray, null)
        style = Paint.Style.STROKE
        isAntiAlias = false
        strokeWidth = dashLineStrokeWidth
        pathEffect = DashPathEffect(floatArrayOf(dashLineGap, dashLineGap), dashLineOffset)
    }

    private fun drawText(canvas: Canvas) {
        val labelTextColor = ResourcesCompat.getColor(resources, R.color.light_gray, null)
        val timeTextColor = ResourcesCompat.getColor(resources, R.color.dark_gray, null)

        paint.setupForText(labelTextColor, labelTextSize)
        drawTextLabels(canvas)
        paint.setupForText(timeTextColor, timeTextSize)
        drawTimeValues(canvas)
    }

    private fun Paint.setupForText(textColor: Int, textSizeValue: Float) {
        reset()
        isAntiAlias = true
        style = Paint.Style.FILL
        color = textColor
        textSize = textSizeValue
        textAlign = Paint.Align.CENTER
        typeface = Typeface.create(fontFamily, Typeface.NORMAL)
    }

    private fun drawTextLabels(canvas: Canvas) {
        drawSunriseSunsetLabels(canvas)
        drawHorizonLabel(canvas)
    }

    private fun drawTimeValues(canvas: Canvas) {
        val marginBottom = resources.getDimension(R.dimen.horizon_time_value_bottom_margin)
        canvas.drawText(sunriseTimeValue, pSunriseTime.x, pSunriseTime.y - marginBottom, paint)
        canvas.drawText(sunsetTimeValue, pSunsetTime.x, pSunsetTime.y - marginBottom, paint)
    }

    private fun drawSunriseSunsetLabels(canvas: Canvas) {
        val labelBottomMargin = resources.getDimension(R.dimen.horizon_label_bottom_margin)
        val labelY = pSunriseTime.y - timeTextSize - labelBottomMargin

        val sunriseLabel = resources.getString(R.string.sunrise)
        canvas.drawText(sunriseLabel, pSunriseTime.x, labelY, paint)

        val sunsetLabel = resources.getString(R.string.sunset)
        canvas.drawText(sunsetLabel, pSunsetTime.x, labelY, paint)
    }

    private fun drawHorizonLabel(canvas: Canvas) {
        val horizonLabel = resources.getString(R.string.horizon)
        val marginRight = resources.getDimension(R.dimen.horizon_right_margin)
        val marginBottom = resources.getDimension(R.dimen.horizon_bottom_margin)
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(horizonLabel, width - marginRight, pSunset.y - marginBottom, paint)
    }

}