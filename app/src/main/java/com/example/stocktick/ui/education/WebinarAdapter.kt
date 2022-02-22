package com.example.stocktick.ui.education

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.databinding.EduWebinarItemBinding
import com.example.stocktick.ui.education.model.WebinarItem

class WebinarAdapter( val context: Context,val webinarList: List<WebinarItem>) :
    RecyclerView.Adapter<WebinarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebinarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EduWebinarItemBinding.inflate(inflater,parent,false)
        return WebinarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WebinarViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}
//ViewHolder
class WebinarViewHolder(private val binding: EduWebinarItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(singleItem : WebinarItem?){
    }
}
