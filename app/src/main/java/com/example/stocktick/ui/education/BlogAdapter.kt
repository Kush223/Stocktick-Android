package com.example.stocktick.ui.education

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.databinding.EduBlogItemImageBinding
import com.example.stocktick.databinding.EduBlogItemVideoBinding
import com.example.stocktick.ui.education.model.BlogItem

class BlogAdapter(
    val context: Context, private val blogList: MutableList<BlogItem>, private val educationInterfaceClickListener: EducationInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_IMAGE = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_IMAGE) {
            val binding = EduBlogItemImageBinding.inflate(inflater, parent, false)
            return BlogImageViewHolder(context, binding, educationInterfaceClickListener)
        } else {
            val binding = EduBlogItemVideoBinding.inflate(inflater, parent, false)
            Log.d("OncreateVH","VIEWTYPE code"+viewType.toString())
            return BlogVideoViewHolder(context,binding,educationInterfaceClickListener)
        }

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val singleItem = blogList[position]
        if (singleItem.view_type==0) {
            //attatch to the video_url
            (holder as BlogImageViewHolder).bind(singleItem)
        }
        (holder as BlogVideoViewHolder).bind(singleItem)
    }

    override fun getItemViewType(position: Int): Int {
        return blogList[position].view_type ?: 0
    }

    override fun getItemCount(): Int {
        return blogList.size
    }
}

class BlogVideoViewHolder(
    context: Context,
    private var binding: EduBlogItemVideoBinding,
    educationInterfaceClickListener: EducationInterface
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(singleItem: BlogItem) {
        //Here code related to the video playing etc.


    }
}

class BlogImageViewHolder(
    val context: Context,
    private val binding: EduBlogItemImageBinding,
    val educationInterfaceClickListener: EducationInterface
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(singleItem: BlogItem) {
        binding.blogLongDesc.text = singleItem.long_desc
        binding.blogShortDesc.text = singleItem.short_desc
        Glide.with(context).load(singleItem.image_url).into(binding.blogImageView)

        binding.blogOverallLayout.setOnClickListener {
            educationInterfaceClickListener.onBlogImageClickListener(
                singleItem.blog_link
            )
        }
    }
}

//https://blog.mindorks.com/recyclerview-multiple-view-types-in-android
//https://stackoverflow.com/questions/44273955/glide-callback-after-success-in-kotlin
//https://stackoverflow.com/questions/33971626/set-background-image-to-relative-layout-using-glide-in-android

