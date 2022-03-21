package com.example.stocktick.ui.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlinx.coroutines.*

enum class PerformanceLabel(val label: String, val angle: Float) {
    WEALTH_PROTECT("Protect", 1f),
    WEALTH_CONSERVE("Conserve", 35f),
    WEALTH_STEADY("Steady", 76f),
    WEALTH_BUILD("Build", 104f),
    WEALTH_GROW("Grow", 145f),
    WEALTH_MULTIPLY("Multiply", 179f);
}


private const val TAG = "PerformanceMeter"

class PerformanceMeter @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var radius = 0.0f
    private var width = 0f
    private var height = 0f
    private var left = 0f
    private var right = 0f
    private var bottom = 0f
    private var twoPercent = 0f
    private var fifteenPercent = 0f
    private var innerTickRad = 0f //All these zero values will be set after the measuring is done
    private var outerTickRad = 0f
    private var tickLeft = 0f
    private var tickRight = 0f
    private var tickTop = 0f
    private var tickBottom = 0f
    private var tickAngle = -180f//degree
    private var circle = Path()
    private var textAngles = mutableListOf<Float>()

    private var selectedCategory: PerformanceLabel? = null
    private var targetAngle: Float = 0f


    // division values
    private var divLeft = 0f
    private var divRight = 0f
    private var divTop = 0f
    private var divBottom = 0f
    // green div values
    private var gDivLeft = 0f
    private var gDivRight = 0f
    private var gDivTop = 0f
    private var gDivBottom = 0f



    private fun calculatePoints(w: Int, h: Int) {
        left = 0f
        right = left + 2.0f * radius
        bottom = 2 * radius
        twoPercent = 2f.fromPercentage()
        fifteenPercent = 15f.fromPercentage()
        innerTickRad = 3f.fromPercentage()
        outerTickRad = 5f.fromPercentage()
        tickLeft = radius - innerTickRad
        tickRight = 2 * radius - fifteenPercent * 1.6f
        tickTop = radius - 1.5f.fromPercentage() - outerTickRad
        tickBottom = radius + 1.5f.fromPercentage() - outerTickRad
        circle.addCircle(radius, radius, radius - twoPercent*1.8f, Path.Direction.CW)
        for (i in 0..5){
            textAngles.add((30f*i+15f).toCircumference())
        }
        //setting divs values
        divLeft = 5f.fromPercentage()
        divRight = width - divLeft
        divTop = divLeft
        divBottom = width - divLeft
        //setting greed div values
        gDivLeft = 4f.fromPercentage()
        gDivRight = width - gDivLeft
        gDivTop = gDivLeft
        gDivBottom = width - gDivLeft

    }

    private fun tickAngle(theta: Float) { // specify the angle, like for vertical tick, it should be -90
        GlobalScope.launch(Dispatchers.Default) {
            val swapAngle = 180 + theta
            for (i in 0..200) {
                tickAngle = (-swapAngle * kotlin.math.exp(-3.0 * i / 100.0) + theta).toFloat()
                delay(10)
                withContext(Dispatchers.Main) {
                    postInvalidate()
                }
            }
        }
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = w*0.6f
        super.setMinimumHeight(height.toInt())
        Log.d(TAG, "onSizeChanged: Width $w and height :$h")
        radius = (w / 2).toFloat()
        calculatePoints(w, h)

    }


    fun swapAngle(category: PerformanceLabel) {
        selectedCategory = category
        targetAngle = -180f + category.angle
        Log.d(TAG, "swapAngle: targetAngle :$targetAngle")
        tickAngle(targetAngle)
    }

    //Time time complexity of this method should be as least as possible for better ui performance
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: $left $right $bottom")

        canvas?.drawArc(left, 0f, right, 2 * radius, 0f, -180f, true, grayPaint) //white circular background

        val labels = PerformanceLabel.values()

        val isTouched = selectedCategory != null && (-tickAngle + targetAngle) < 1

        for (i in 1..6) {
            val label = labels[i - 1]
            if (isTouched  && label == selectedCategory ) {
                Log.d(TAG, "onDraw: green parameters tick angle :$tickAngle ")

                canvas?.drawArc(  //green piece
                    gDivLeft,
                    gDivTop,
                    gDivRight,
                    gDivBottom,
                    30f * i - 209.5f,
                    29f,
                    true,
                    green
                )
                canvas?.drawTextOnPath( //drawing texts
                    label.label,
                    circle,
                    textAngles[i - 1],
                    0f,
                    highlightedTextColor
                )


            }
            else {
                canvas?.drawArc(
                    divLeft,
                    divTop,
                    divRight,
                    divBottom,
                    30f * i - 209.5f,
                    29f,
                    true,
                    whitePaint
                )
                canvas?.drawTextOnPath( //drawing texts
                    label.label,
                    circle,
                    textAngles[i - 1],
                    0f,
                    blackTextPaint
                )
            }

        }

        canvas?.drawArc(
            radius - fifteenPercent,
            radius - fifteenPercent,
            radius+  fifteenPercent,
            radius + fifteenPercent,
            0f,
            -180f,
            true,
            grayPaint
        ) //inner white gray circlecircle


        //bottom text bar
        canvas?.drawRect(0f, radius, width, radius + 18f.fromPercentage(), grayPaint)
        canvas?.drawRect(
            twoPercent,
            radius + twoPercent,
            width - twoPercent,
            radius + 16f.fromPercentage(),
            green
        )

        if (isTouched) {

            canvas?.drawText("Category : WEALTH ${selectedCategory?.label?.uppercase()}", radius, radius+11.5f.fromPercentage(),largeTextPaint )
        }

        //making tick
        canvas?.drawCircle(radius, radius - outerTickRad, outerTickRad, outerTickPaint)

        canvas?.rotate(tickAngle, radius, radius - outerTickRad)
        canvas?.drawRoundRect(
            tickLeft,
            tickTop,
            tickRight,
            tickBottom,
            20f,
            20f,
            outerTickPaint
        )

        canvas?.drawCircle(radius, radius - outerTickRad, innerTickRad, innerTickPaint)
    }

    private val whitePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }


    private val blackTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 35.0f
        typeface = Typeface.create("", Typeface.BOLD)
        color = Color.BLACK
    }
    private val highlightedTextColor = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 40.0f
        textScaleX = 1.1f
        typeface = Typeface.create("", Typeface.BOLD)
        color = Color.BLACK
    }
    private val largeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 70.0f
        typeface = Typeface.create("", Typeface.BOLD)
        textScaleX = 0.7f
        color = Color.WHITE
    }
    private val green = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#39b54a")
        style = Paint.Style.FILL
    }
    private val grayPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#e7e8e9")
        style = Paint.Style.FILL
    }

    private val outerTickPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#424242")
        style = Paint.Style.FILL
    }
    private val innerTickPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#616161")
        style = Paint.Style.FILL
    }

    private fun Float.fromPercentage(): Float {
        return (this / 100) * width
    }

    private fun Float.toCircumference(): Float {
        return (3.1416 * (radius - twoPercent*1.8) * this / 180.0).toFloat()
    }
}