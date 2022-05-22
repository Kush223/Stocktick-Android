package com.example.stocktick.ui.mutual_funds.discover_mutual_funds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.domain_models.MfModel


class MfAdapter
    constructor(
        private val mfList: List<MfModel>,
        var returnType: ReturnType,
        private val context: Context
    )
    : RecyclerView.Adapter<MfAdapter.ViewHolder>() {

    companion object {
        fun newInstance(
            mfList: List<MfModel>,
            returnType: ReturnType,
            context: Context
        ) = MfAdapter(
            mfList = mfList,
            returnType = returnType,
            context = context
        )
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mfTitle: TextView = itemView.findViewById(R.id.mfTitle)
        val mfReturn : TextView = itemView.findViewById(R.id.tvReturn)
        val mfIcon : ImageView = itemView.findViewById(R.id.mfIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mf_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mfModel = mfList[position]
        holder.mfTitle.text = mfModel.mfName
        when (returnType){
            ReturnType.ONE_DAY -> holder.mfReturn.text = mfModel.oneDayR
            ReturnType.ONE_YEAR -> holder.mfReturn.text = mfModel.oneYearR
            ReturnType.THREE_YEAR -> holder.mfReturn.text = mfModel.threeYearR
        }
        if (mfModel.iconUrl.isNotEmpty())
        Glide.with(context).load(mfModel.iconUrl).into(holder.mfIcon)

    }

    override fun getItemCount(): Int {
        return mfList.size
    }
}