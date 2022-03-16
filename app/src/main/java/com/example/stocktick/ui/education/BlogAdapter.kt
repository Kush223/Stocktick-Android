package com.example.stocktick.ui.education

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.databinding.EduBlogItemImageBinding
import com.example.stocktick.databinding.EduBlogItemVideoBinding
import com.example.stocktick.ui.education.model.BlogItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import java.util.regex.Matcher
import java.util.regex.Pattern

//https://github.com/PierfrancescoSoffritti/android-youtube-player/blob/master/core-sample-app/src/main/java/com/pierfrancescosoffritti/androidyoutubeplayer/core/sampleapp/examples/recyclerViewExample/RecyclerViewAdapter.java
//https://github.com/PierfrancescoSoffritti/android-youtube-player
class BlogAdapter(
    val context: Context,
    private val blogList: MutableList<BlogItem>,
    private val educationInterfaceClickListener: EducationInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_IMAGE = 0
    }

    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var lifecycle: Lifecycle

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_IMAGE) {
            val binding = EduBlogItemImageBinding.inflate(inflater, parent, false)
            return BlogImageViewHolder(context, binding, educationInterfaceClickListener)
//            Log.d("VIEWTYPES1: ",viewType.toString())
        } else {
            val binding = EduBlogItemVideoBinding.inflate(inflater, parent, false)
//            Log.d("VIEWTYPES2: ",viewType.toString())
            youTubePlayerView = binding.youtubePlayerViewBlog
            lifecycle.addObserver(youTubePlayerView)
            return BlogVideoViewHolder(context, binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val singleItem = blogList[position]
        if (singleItem.view_type == 0) {
            //attatch to the video_url
            (holder as BlogImageViewHolder).bind(singleItem)
//            Log.d("holder1 ",holder.toString())
        } else {
            (holder as BlogVideoViewHolder).bind(singleItem)
        }
    }
//        Log.d("holder2 ",holder.toString())


    override fun getItemViewType(position: Int): Int {
//        Log.d("getitmviewtype",blogList[position].view_type.toString())
        return blogList[position].view_type ?: 0
    }

    override fun getItemCount(): Int {
        return blogList.size
    }
}

class BlogVideoViewHolder(context: Context, private var binding: EduBlogItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(singleItem: BlogItem) {
        //Here code related to the video playing etc.
        Log.d("blogVideoVH", binding.toString())
        Log.d("singleItem", singleItem.toString())
        Log.d("singleITem", singleItem.video_link.toString())
        val id = getYouTubeId(singleItem.video_link.toString())
    }

    private fun getYouTubeId(youTubeUrl: String): String? {
        val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern: Pattern = Pattern.compile(pattern)
        val matcher: Matcher = compiledPattern.matcher(youTubeUrl)
        if (matcher.find()) {
            return matcher.group()
        }
        return null
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

