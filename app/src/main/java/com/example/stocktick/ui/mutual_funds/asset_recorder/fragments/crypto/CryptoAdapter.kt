package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.crypto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.CryptoModel

class CryptoAdapter
constructor(
    private val context: Context,
    var modList: MutableList<CryptoModel> = mutableListOf(),
    private val onClick: (position: Int) -> Unit,
    private val onDelete: (position: Int) -> Unit
): RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPurchasePrice: TextView = itemView.findViewById(R.id.tvPurchasePrice)
        val tvInstrumentName: TextView = itemView.findViewById(R.id.tvInstrumentName)
        val tvBrokerName: TextView = itemView.findViewById(R.id.tvBrokerName)
        val delBtn: ImageButton = itemView.findViewById(R.id.imgBtnRemove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.crypto_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modList[position]
        if (model.purchase_price.isNotEmpty()) {
            val str = "${model.purchase_price} INR"
            holder.tvPurchasePrice.text = str
        }
        if (model.instrument_name.isNotEmpty())
            holder.tvInstrumentName.text = model.instrument_name
        if (model.broker_name.isNotEmpty())
            holder.tvBrokerName.text = model.broker_name

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
