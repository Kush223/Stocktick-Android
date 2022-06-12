package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.off_market

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.OffModel

class OffAdapter
constructor (
    private val context: Context,
    var modList: MutableList<OffModel> = mutableListOf(),
    private val onClick: (position: Int) -> Unit,
    private val onDelete: (position: Int) -> Unit
): RecyclerView.Adapter<OffAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPurchasePrice: TextView = itemView.findViewById(R.id.tvPurchasePrice)
        val tvBrokerName: TextView = itemView.findViewById(R.id.tvBrokerName)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
        val delBtn: ImageButton = itemView.findViewById(R.id.imgBtnRemove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bonds_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modList[position]
        if (model.purchase_price.isNotEmpty()) {
            val str = "${model.purchase_price} INR"
            holder.tvPurchasePrice.text = str
        }
        if (model.broker_name.isNotEmpty())
            holder.tvBrokerName.text = model.broker_name
        if (model.qty.isNotEmpty())
            holder.tvQuantity.text = model.qty

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