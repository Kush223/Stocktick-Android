package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.fd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.FixedDeposit

class FdAdapter
    constructor(
        private val context: Context,
        var fdList: MutableList<FixedDeposit> = mutableListOf(),
        private val onClick: (position: Int) -> Unit,
        private val onDelete: (position: Int) -> Unit
    ): RecyclerView.Adapter<FdAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvInvestmentAmount: TextView = itemView.findViewById(R.id.tvAmountInvested)
        val tvBankName: TextView = itemView.findViewById(R.id.tvBankName)
        val tvMaturityDate: TextView = itemView.findViewById(R.id.tvBranchName)
        val delBtn : ImageButton = itemView.findViewById(R.id.imgBtnRemove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fd_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fdModel = fdList[position]
        if (fdModel.amount_invested.isNotEmpty()) {
            val str = "${fdModel.amount_invested} INR"
            holder.tvInvestmentAmount.text = str
        }
        if (fdModel.bank_name.isNotEmpty())
        holder.tvBankName.text = fdModel.bank_name
        if (fdModel.maturity_date.isNotEmpty())
        holder.tvMaturityDate.text = fdModel.maturity_date

        holder.delBtn.setOnClickListener{
            onDelete(holder.layoutPosition)
        }

        holder.itemView.setOnClickListener{
            onClick(holder.layoutPosition)
        }
    }

    override fun getItemCount(): Int {
        return fdList.size
    }
}