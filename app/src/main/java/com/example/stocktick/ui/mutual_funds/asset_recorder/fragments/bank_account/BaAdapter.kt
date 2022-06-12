package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.bank_account

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.BaModel

class BaAdapter
constructor(
    private val context: Context,
    var modList: MutableList<BaModel> = mutableListOf(),
    private val onClick: (position: Int) -> Unit,
    private val onDelete: (position: Int) -> Unit
): RecyclerView.Adapter<BaAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bankName: TextView = itemView.findViewById(R.id.tvBankName)
        val accType: TextView = itemView.findViewById(R.id.tvAccType)
        val accNo: TextView = itemView.findViewById(R.id.tvAccNo)
        val delBtn: ImageButton = itemView.findViewById(R.id.imgBtnRemove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ba_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = modList[position]
        if (model.bank_name.isNotEmpty()) {
            holder.bankName.text = model.bank_name.uppercase()
        }
        if (model.account_type.isNotEmpty())
            holder.accType.text = model.account_type
        if (model.last_4_digit_of_account_number.isNotEmpty()) {
            holder.accNo.text = model.last_4_digit_of_account_number
        }

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
