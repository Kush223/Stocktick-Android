package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.DataListCardModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.LabelValue

class CardListAdapter
constructor(
    private val context: Context,
    var cardDataList: MutableList<LabelValue> = mutableListOf(),
    private val onClick: (position: Int) -> Unit,
    private val onDelete: (position: Int) -> Unit,
    private val dataListCardModel: DataListCardModel
) : RecyclerView.Adapter<CardListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitleLabel: TextView = itemView.findViewById(R.id.tvTitleLabel)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvParticular1Label: TextView = itemView.findViewById(R.id.tvParticular1Label)
        val tvParticular2Label: TextView = itemView.findViewById(R.id.tvParticular2Label)
        val tvParticular1: TextView = itemView.findViewById(R.id.tvParticular1)
        val tvParticular2: TextView = itemView.findViewById(R.id.tvParticular2)
        val delBtn: ImageButton = itemView.findViewById(R.id.imgBtnRemove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.asset_recorder_data_card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTitleLabel.text = dataListCardModel.title_label
        holder.tvParticular1Label.text = dataListCardModel.particular1_label
        holder.tvParticular2Label.text = dataListCardModel.particular2_label

        val particulars = cardDataList[position]
        if (particulars.title.isNotEmpty()) {
            if (dataListCardModel.title_label.lowercase().let {
                    it.contains("amount") || it.contains("purchase")
                })
                holder.tvTitle.text = "${particulars.title} INR"
            else
                holder.tvTitle.text = particulars.title
        }
        if (particulars.particular1.isNotEmpty()) {
            holder.tvParticular1.text = particulars.particular1
        }
        if (particulars.particular2.isNotEmpty()) {
            holder.tvParticular2.text = particulars.particular2
        }


        holder.delBtn.setOnClickListener {
            onDelete(holder.layoutPosition)
        }
        holder.itemView.setOnClickListener {
            onClick(holder.layoutPosition)
        }
    }

    override fun getItemCount(): Int {
        return cardDataList.size
    }
}