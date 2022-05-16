package com.example.stocktick.ui.customviews

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.example.stocktick.R

private const val TAG = "NeumorphButton"
class
NeumorphEditText(
    context: Context?,
    attrs: AttributeSet?)
    : ConstraintLayout(context!!, attrs) {

    var onChange: (text: String) -> Unit = {

    }
    private val  attributes : TypedArray =
        context!!.obtainStyledAttributes(attrs, R.styleable.NeumorphEdittext)
    private var editText : EditText
    private var tvPrefix: TextView

    init {
        inflate(context, R.layout.neumorph_edit_text, this)
        editText = findViewById(R.id.mainEditText)
        tvPrefix = findViewById(R.id.tvPrefix)
        try {
            val text = attributes.getString(R.styleable.NeumorphEdittext_text) ?: ""
            setText(text)
            val hint = attributes.getString(R.styleable.NeumorphEdittext_hint)
            val isPrefix = attributes.getBoolean(R.styleable.NeumorphEdittext_usePrefix, false)
            if (isPrefix){
                val prefix = attributes.getString(R.styleable.NeumorphEdittext_prefix) ?: ""
                tvPrefix.text = prefix
            }
            else {
                tvPrefix.visibility= View.GONE
            }
            when (attributes.getInt(R.styleable.NeumorphEdittext_inputType, -1)){
                1 -> {
                    editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                2 -> {
                    editText.inputType = InputType.TYPE_CLASS_NUMBER
                }
                3 -> {
                    editText.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL
                }
            }

            editText.addTextChangedListener(
                onTextChanged = { text, start, count, after->
                    onChange(text.toString())
                }
            )


            editText.hint = hint
            editText.setTextColor(Color.WHITE)
        }
        catch (e: Exception){
            Log.e(TAG, "Error :${e.localizedMessage} ", )
        }
        finally {
            attributes.recycle()
        }
    }

    fun onTextChangeListener(onChange: (text: String)->Unit){
        this.onChange = onChange
    }

    fun setText(text : String) {
        editText.setText(text)
    }

    fun getText(): String{
        return editText.text.toString()
    }

    fun setHint(text: String){
        editText.hint = text
    }

    fun getHint(): String{
        return editText.hint.toString()
    }



}