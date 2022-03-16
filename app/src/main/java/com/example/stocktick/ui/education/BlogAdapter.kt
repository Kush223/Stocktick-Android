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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


//https://github.com/PierfrancescoSoffritti/android-youtube-player/blob/master/core-sample-app/src/main/java/com/pierfrancescosoffritti/androidyoutubeplayer/core/sampleapp/examples/recyclerViewExample/RecyclerViewAdapter.java
//https://github.com/PierfrancescoSoffritti/android-youtube-player
class BlogAdapter(
    val lifecycle: Lifecycle,
    val context: Context,
    private val blogList: MutableList<BlogItem>,
    private val educationInterfaceClickListener: EducationInterface
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_IMAGE = 0
    }

//    fun RecyclerViewAdapter(lifecycle: Lifecycle?) {
//        this.lifecycle = lifecycle!!
//    }

    private lateinit var youTubePlayerView: YouTubePlayerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_IMAGE) {
            Log.d("BLOGS","1")
            val binding = EduBlogItemImageBinding.inflate(inflater, parent, false)
            return BlogImageViewHolder(context, binding, educationInterfaceClickListener)
        } else {
            Log.d("BLOGS","2")
            val binding = EduBlogItemVideoBinding.inflate(inflater, parent, false)
            youTubePlayerView = binding.youtubePlayerViewBlog
            //HOW TO ATTATCH LIFECYCLE HERE? or where to attach the life cycle even?
            return BlogVideoViewHolder(context, binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val singleItem = blogList[position]
        if (singleItem.view_type == 0) {
            //attatch to the video_url
            (holder as BlogImageViewHolder).bind(singleItem)
        } else {
            lifecycle.addObserver(youTubePlayerView)
            (holder as BlogVideoViewHolder).bind(singleItem,youTubePlayerView)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return blogList[position].view_type ?: 0
    }

    override fun getItemCount(): Int {
        return blogList.size
    }
}

class BlogVideoViewHolder(context: Context, private var binding: EduBlogItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(singleItem: BlogItem, youTubePlayerView: YouTubePlayerView) {
        //Here code related to the video playing etc.

        val id = singleItem.video_link
        lateinit var youTubePlayer : YouTubePlayer

        Log.d("bindID",id.toString())
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(initializedYouTubePlayer: YouTubePlayer) {
                youTubePlayer = initializedYouTubePlayer
                if (id != null) {
                    youTubePlayer.cueVideo(id, 0F)
                }else{
                    Log.d("bindID3",id.toString())
                }
            }
        })
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

