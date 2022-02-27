package com.example.stocktick.ui.education

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.databinding.EduWebinarItemBinding
import com.example.stocktick.ui.education.model.WebinarItem

//TODO() -- DIffutils?
class WebinarAdapter(val context: Context, val webinarList: MutableList<WebinarItem>) :
    RecyclerView.Adapter<WebinarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebinarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EduWebinarItemBinding.inflate(inflater, parent, false)
        Log.d("onCreateViewHholder", "Create a view holder")
        return WebinarViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: WebinarViewHolder, position: Int) {

        val singleItem = webinarList[position]
        holder.bind(singleItem)
        //webinarUrl - if self hosted, then open
        Log.d("onBindViewHholder", holder.toString())


    }

    override fun getItemCount(): Int {
        return webinarList.size
    }


}

//ViewHolder
class WebinarViewHolder(val context: Context, private val binding: EduWebinarItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(singleItem: WebinarItem?) {
        binding.webinarTitle.text = singleItem?.title
        binding.webinarHostedBy.text = singleItem?.hosted_by

        Glide.with(context).load(singleItem?.image_url).into(binding.webinarImageUrl)
//        binding.webinarRedirectUrl.text = singleItem?.webinar_redirect_url
        //setOnClickListner.
    }
}
