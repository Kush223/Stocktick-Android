package com.example.stocktick.ui.customviews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.stocktick.R
import soup.neumorphism.NeumorphButton
import soup.neumorphism.NeumorphCardView
import soup.neumorphism.ShapeType


private const val TAG = "MutualFundCardT"
class
MutualFundCard(
    context: Context?,
    attrs: AttributeSet?)
    : ConstraintLayout(context!!, attrs) {
    private val  attributes : TypedArray =
        context!!.obtainStyledAttributes(attrs, R.styleable.MutualFundCard)
    private  var imageView : ImageView
    private  var  let1 : TextView
    private  var let2 : TextView
    private  var button : NeumorphButton

    private var listener: OnClickListener? = null
//    private var mainLayout: ConstraintLayout
//    private var mfCardView: NeumorphCardView

    init {
        inflate(context, R.layout.mutual_fund_card, this)
        imageView = findViewById(R.id.mainImage)
        let1 = findViewById(R.id.alet1)
        let2 = findViewById(R.id.alet2)
        button = findViewById(R.id.mainButton)
//        mainLayout = findViewById(R.id.constraintMainLayout)
//        mfCardView = findViewById(R.id.mfCardView)
        try {
            let1.text = attributes.getString(R.styleable.MutualFundCard_let1)
            let2.text = attributes.getString(R.styleable.MutualFundCard_let2)
            button.text = attributes.getString(R.styleable.MutualFundCard_buttonText)
            imageView.setImageResource(attributes.getResourceId(R.styleable.MutualFundCard_image,R.color.black))
            button.setOnClickListener{
                listener?.onClick(it)
            }


        }
        catch (e: Exception){
            Log.e(TAG, "Error :${e.localizedMessage} ")
        }
        finally {
            attributes.recycle()
        }
    }

    fun onButtonClickedListener(listener: OnClickListener){
        this.listener = listener
    }






}
