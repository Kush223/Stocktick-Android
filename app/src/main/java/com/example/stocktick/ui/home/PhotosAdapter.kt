package com.example.stocktick.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.stocktick.R
import java.util.*

class PhotosAdapter
    constructor(
        private val context: Context,
        private val photos: List<PhotoModel>,
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    ): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater.inflate(R.layout.view_pager_layout,container, false)
        val imageView = itemView.findViewById<ImageView>(R.id.ivPhotoForViewPager)
        Glide.with(context).load(photos[position].uri).into(imageView)
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun getCount(): Int {
        return photos.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}