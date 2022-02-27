package com.example.stocktick.ui.education

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.databinding.EduBlogItemBinding
import com.example.stocktick.ui.education.model.BlogItem

//TODO() -- Debug the adapter file in blogAdapter
class BlogAdapter(val context: Context, val blogList: MutableList<BlogItem>) :
    RecyclerView.Adapter<BlogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        Log.d("onCreateViewHolderBLOGw", "Create a view holder22")
        val inflater = LayoutInflater.from(parent.context)
        val binding = EduBlogItemBinding.inflate(inflater, parent, false)
        Log.d("onCreateViewHolderBLOG", "Create a view holder2")
        return BlogViewHolder(context,binding)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val singleItem = blogList[position]
        holder.bind(singleItem)
        Log.d("onBindViewHholder", holder.toString())
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

}

class BlogViewHolder(val context: Context,private val binding: EduBlogItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(singleItem: BlogItem) {
        binding.blogLongDesc.text = singleItem.long_desc
        binding.blogShortDesc.text = singleItem.short_desc
        Glide.with(context).load(singleItem.image_url).into(binding.thumbnail)
        //set_on_click_listener for
    }

}
