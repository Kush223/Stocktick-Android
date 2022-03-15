package com.example.stocktick.ui.customviews

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.stocktick.R
import soup.neumorphism.NeumorphCardView
import soup.neumorphism.ShapeType

private const val TAG = "NeumorphButton"
class
NeumorphEditText(
    context: Context?,
    attrs: AttributeSet?)
    : ConstraintLayout(context!!, attrs) {

    private val  attributes : TypedArray =
        context!!.obtainStyledAttributes(attrs, R.styleable.NeumorphEdittext)
    private var editText : EditText

    init {
        inflate(context, R.layout.neumorph_edit_text, this)
        editText = findViewById(R.id.mainEditText)
        try {
            val text = attributes.getString(R.styleable.NeumorphEdittext_text) ?: ""
            setText(text)
            val hint = attributes.getString(R.styleable.NeumorphEdittext_hint)
            editText.hint = hint
        }
        catch (e: Exception){
            Log.e(TAG, "Error :${e.localizedMessage} ", )
        }
        finally {
            attributes.recycle()
        }
    }

    fun setText(text : String) {
        editText.setText(text)
    }

    fun getText(): String{
        return editText.text.toString()
    }



}