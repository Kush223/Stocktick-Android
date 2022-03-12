package com.example.stocktick.ui.education

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.databinding.EduBlogItemBinding
import com.example.stocktick.ui.education.model.BlogItem
//TODO() multiple layout files for youtube and blog item seperately
class BlogAdapter(
    val context: Context, private val blogList: MutableList<BlogItem>, private val educationInterfaceClickListener: EducationInterface
) :
    RecyclerView.Adapter<BlogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EduBlogItemBinding.inflate(inflater, parent, false)
        return BlogViewHolder(context, binding, educationInterfaceClickListener)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val singleItem = blogList[position]
        holder.bind(singleItem)
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

}

class BlogViewHolder(
    val context: Context,
    private val binding: EduBlogItemBinding,
    val educationInterfaceClickListener: EducationInterface
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(singleItem: BlogItem) {
        binding.blogLongDesc.text = singleItem.long_desc
        binding.blogShortDesc.text = singleItem.short_desc
        Glide.with(context).load(singleItem.image_url).into(binding.blogImageView)
        val videoUrl = singleItem.video_link
        if(videoUrl!=null){
            //show YT view
            binding.ytLinearLayoutBlog.visibility = View.VISIBLE
            binding.imageLinearLayoutBlog.visibility = View.INVISIBLE
        }else{
            //image type
            binding.ytLinearLayoutBlog.visibility = View.INVISIBLE
            binding.imageLinearLayoutBlog.visibility = View.VISIBLE
        }

        //so the real reason and magic of the interface is happening here.
        //we only want to click on a particular id and send it back to the fragment to do retrofi
        //we put up the progress bar and other things also here i think?
        //or do we?
        binding.blogOverallLayout.setOnClickListener {
            educationInterfaceClickListener.onBlogClickListener(
                singleItem.video_link,
                singleItem.blog_link
            )
        }
    }
}


//https://stackoverflow.com/questions/44273955/glide-callback-after-success-in-kotlin
//https://stackoverflow.com/questions/33971626/set-background-image-to-relative-layout-using-glide-in-android

