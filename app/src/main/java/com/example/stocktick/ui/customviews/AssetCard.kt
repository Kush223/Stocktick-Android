package com.example.stocktick.ui.customviews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.stocktick.R
import soup.neumorphism.NeumorphCardView

private const val TAG = "AssetCard"
class AssetCard(
    context: Context?,
    attrs: AttributeSet?)
: ConstraintLayout(context!!, attrs) {
    private val attributes: TypedArray =
        context!!.obtainStyledAttributes(attrs, R.styleable.AssetCard)
    private var imgIcon: ImageView
    private var assetTitle: TextView
    private var button : ConstraintLayout


    private var listener: OnClickListener? = null

    init {
        inflate(context, R.layout.asset_card, this)
        imgIcon = findViewById(R.id.imgIcon)
        assetTitle = findViewById(R.id.tvAssetCategoryName)
       button = findViewById(R.id.assetCardConsLayout)


        try {

            imgIcon.setImageResource(
                attributes.getResourceId(
                    R.styleable.AssetCard_imgsrc,
                    R.color.black
                )
            )
            assetTitle.text = attributes.getString(R.styleable.AssetCard_title)
            button.setOnClickListener{
                listener?.onClick(it)
            }


        } catch (e: Exception) {
            Log.e(TAG, "Error :${e.localizedMessage} ")
        } finally {
            attributes.recycle()
        }
    }

    fun onButtonClickedListener(listener: OnClickListener){
        this.listener = listener
    }
}




