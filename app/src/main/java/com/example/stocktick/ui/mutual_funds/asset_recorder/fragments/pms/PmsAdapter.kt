package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.pms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.PmsModel

class PmsAdapter
constructor(
    private val context: Context,
    var modList: MutableList<PmsModel> = mutableListOf(),
    private val onClick: (position: Int) -> Unit,
    private val onDelete: (position: Int) -> Unit
): RecyclerView.Adapter<PmsAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvInvestmentAmount: TextView = itemView.findViewById(R.id.tvAmountInvested)
        val tvAmcName: TextView = itemView.findViewById(R.id.tvAmcName)
        val tvAifName: TextView = itemView.findViewById(R.id.tvAifName)
        val delBtn: ImageButton = itemView.findViewById(R.id.imgBtnRemove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pms_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modList[position]
        if (model.invested_value.isNotEmpty()) {
            val str = "${model.invested_value} INR"
            holder.tvInvestmentAmount.text = str
        }
        if (model.amc_name.isNotEmpty())
            holder.tvAmcName.text = model.amc_name
        if (model.pms_aif_name.isNotEmpty())
            holder.tvAifName.text = model.pms_aif_name

        holder.delBtn.setOnClickListener {
            onDelete(holder.layoutPosition)
        }

        holder.itemView.setOnClickListener {
            onClick(holder.layoutPosition)
        }
    }

    override fun getItemCount(): Int {
        return modList.size
    }
}