package com.example.stocktick.ui.customviews

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlinx.coroutines.*
import java.lang.Exception


enum class Animators() {
    LINEAR_ANIMATOR(),
    EXPONENTIAL_ANIMATOR()
}

private const val TAG = "CustomTracker"
class CustomTracker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var width = 0f
    private var height = 0f
    private var thickness = 20f
    private var pointerRadius = 20f
    private var currentPosition = 0 //It starts from zero




    private var pointerX = 0f
    private var pointerY = 0f
    private var rectLeft = 0f
    private var rectTop = 0f
    private var rectRight = 0f
    private var rectBottom =0f

    private var divs: Int = 0

    fun setDivisions(divs : Int){
        this.divs = divs
    }

    /**
     * Moves to next division if no argument is provided
     * Parameter:
     * animators: Exponential and linear for corresponding animation effect
     */
    fun move(animator: Animators = Animators.LINEAR_ANIMATOR){
        val finalPosition = pointerX + (width-2*pointerRadius)/divs
        when (animator){
            Animators.EXPONENTIAL_ANIMATOR->{
                expMoveAnimator(finalPosition)
            }
            Animators.LINEAR_ANIMATOR ->{
                linearMoveAnimator(finalPosition)
            }
        }
    }
    /**
     * Moves to the mentioned div. It will work even backwards*/
    fun move(div: Int,animator: Animators = Animators.LINEAR_ANIMATOR){

        val finalPosition = pointerRadius + (width-2*pointerRadius)*div.toFloat()/divs.toFloat()
        when (animator){
            Animators.EXPONENTIAL_ANIMATOR->{
                expMoveAnimator(finalPosition)
            }
            Animators.LINEAR_ANIMATOR ->{
                linearMoveAnimator(finalPosition)
            }
        }
    }
    fun setPosition(div: Int){
        pointerX = pointerRadius + (width-2*pointerRadius)*div.toFloat()/divs.toFloat()

    }

    private fun expMoveAnimator(finalPosition: Float) { // specify the angle, like for vertical tick, it should be -90
        GlobalScope.launch(Dispatchers.Default) {
            val swapAngle = finalPosition - pointerX
            for (i in 0..200) {
                pointerX = (-swapAngle * kotlin.math.exp(-3.0 * i / 100.0) + finalPosition).toFloat()
                delay(10)
                withContext(Dispatchers.Main) {
                    postInvalidate()
                }
            }
        }
    }
    private fun linearMoveAnimator(finalPosition: Float){
        GlobalScope.launch(Dispatchers.Default){
            val swapLength = finalPosition - pointerX
            val delLength = swapLength/30.0f
            Log.d(TAG, "linearMoveAnimator: Del len :$delLength")
            for (i in 0..30){
                pointerX+= delLength
                Log.d(TAG, "linearMoveAnimator: pointer X :$pointerX and i :$i")
                withContext(Dispatchers.Main) {
                    postInvalidate()
                }
                delay(9)
            }
        }
    }



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w.toFloat()
        height = h.toFloat()
        setup()
    }

    private fun setup() {
        pointerX = pointerRadius
        pointerY = pointerRadius
        rectLeft = pointerRadius
        rectRight = width - pointerRadius
        rectTop = pointerRadius-thickness/2
        rectBottom = rectTop + thickness
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //Done part
        canvas?.drawRoundRect(
            rectLeft, rectTop, pointerX, rectBottom,10f, 10f,green
        )
        //Remaining part
        canvas?.drawRoundRect(pointerX, rectTop, rectRight, rectBottom,10f, 10f,gray )
        canvas?.drawCircle(
            pointerX,
            pointerY,
            pointerRadius,
            green
        )


    }

    private val green = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FF7666")
        style = Paint.Style.FILL

    }
    private val gray = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#ffffff")
        style = Paint.Style.FILL

    }
}

