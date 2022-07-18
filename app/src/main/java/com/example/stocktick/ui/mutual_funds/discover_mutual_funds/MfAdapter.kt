package com.example.stocktick.ui.mutual_funds.discover_mutual_funds

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.domain_models.MfModel


class MfAdapter
constructor(
    var mfList: List<MfModel> = listOf(),
    var returnType: ReturnType,
    private val context: Context,
    val onClick: (url: String, fundId: Int) -> Unit
) : RecyclerView.Adapter<MfAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mfTitle: TextView = itemView.findViewById(R.id.mfTitle)
        val mfReturn: TextView = itemView.findViewById(R.id.tvReturn)
        val mfIcon: ImageView = itemView.findViewById(R.id.mfIcon)
        val mfConsLayout: ConstraintLayout = itemView.findViewById(R.id.mfConsLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mf_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mfModel = mfList[position]


        holder.mfConsLayout.setOnClickListener {
            if (mfModel.lockStatus.lowercase() == "locked") {

                val intent =
                    Intent(Intent.ACTION_VIEW).setData(
                        Uri.parse(
                            "https://fundzbazar.com/Link/8SotIfGShFw"
                        )
                    )
                context.startActivity(intent)
            }
            else
            onClick(mfModel.redirectUrl, mfModel.fundId)
        }

        if (mfModel.lockStatus.lowercase() == "locked") {
            holder.mfTitle.setTextColor(context.resources.getColor(R.color.secondary_text_color))
            holder.mfTitle.text = "It is locked"
            Glide.with(context).load(R.drawable.ic_locked).into(holder.mfIcon)
            holder.mfReturn.visibility = View.GONE

        } else {
            holder.mfTitle.text = mfModel.mfName
            if (mfModel.iconUrl.isNotEmpty())
                Glide.with(context).load(mfModel.iconUrl).error(R.drawable.mutual_fund_img)
                    .placeholder(R.drawable.mutual_fund_img).into(holder.mfIcon)
            when (returnType) {
                ReturnType.ONE_DAY -> holder.mfReturn.text = mfModel.oneDayR
                ReturnType.ONE_YEAR -> holder.mfReturn.text = mfModel.oneYearR
                ReturnType.THREE_YEAR -> holder.mfReturn.text = mfModel.threeYearR
            }

        }


    }

    override fun getItemCount(): Int {
        return mfList.size
    }
}