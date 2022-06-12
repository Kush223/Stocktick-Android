package com.example.stocktick.ui.mutual_funds.asset_recorder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.EditDetailsModel

class DataDetailsEditAdapter
    constructor(
        val list : MutableList<EditDetailsModel>,
        val context: Context
    )
    : RecyclerView.Adapter<DataDetailsEditAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvLabel : TextView = itemView.findViewById(R.id.tvLabel)
        val etValue : EditText = itemView.findViewById(R.id.etValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.label_edit_value_recycler_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = list[position]
        holder.tvLabel.text = element.label
        holder.etValue.inputType = element.inputType
        holder.etValue.hint = element.hint
        if (element.text.isNotEmpty()){
            holder.etValue.setText(element.text)
        }

        holder.etValue.doOnTextChanged{ text, start, before, count ->
            element.text = text.toString()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}