package com.example.stocktick.ui.mutual_funds.asset_recorder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.EditDetailsModel

class DataDetailsViewAdapter
    constructor(
        private val labelAndValueList : List<EditDetailsModel> = listOf(),
        private val context : Context
    )
    : RecyclerView.Adapter<DataDetailsViewAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvLabel : TextView = itemView.findViewById(R.id.tvLabel)
        val tvValue : TextView = itemView.findViewById(R.id.tvValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.label_value_recycler_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val labelValue = labelAndValueList[position]
        holder.tvLabel.text = labelValue.label
        holder.tvValue.text = labelValue.text
    }

    override fun getItemCount(): Int {
        return labelAndValueList.size
    }
}