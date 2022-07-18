package com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds.adapter.CategoryAdapter.ViewHolder
import com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds.models.ExploreMoreMfCategoryModel

class CategoryAdapter
    constructor(
        private val context : Context,
        var list : MutableList<ExploreMoreMfCategoryModel> = mutableListOf(),
        private val onClick : (catg_id : Int) -> Unit
    )
    : RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgIcon : ImageView = itemView.findViewById(R.id.imgIcon)
        val catgName : TextView = itemView.findViewById(R.id.categoryName)
        val layout : ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.explore_more_mf_categories_recycler_view_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        Glide.with(context).load(item.icon).placeholder(R.drawable.mutual_fund_img).error(R.drawable.mutual_fund_img).into(holder.imgIcon)
        holder.catgName.text = item.fundCatgName
        holder.layout.setOnClickListener{
            onClick(item.id ?: 0)
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}