package com.example.stocktick.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R

class HomeAdapter(private val serviceList: List<HomeItem>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.home_services, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = serviceList[position % serviceList.size]
        holder.itemView
        pos.img?.let { holder.imageView.setImageResource(it) }
        holder.textView.text = pos.txt
    }

    override fun getItemCount(): Int {
        return serviceList.size*2
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val imageView: ImageView = itemView.findViewById(R.id.service_image)
        val textView: TextView = itemView.findViewById(R.id.service_text)
    }


}