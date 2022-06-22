package com.example.stocktick.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.bumptech.glide.Glide
import com.example.stocktick.R

class PhotosAdapter
    constructor(
        private val context: Context,
        private val photos: List<PhotoModel>,
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    ): LoopingPagerAdapter<PhotoModel>(photos,true) {

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val imageView = convertView.findViewById<ImageView>(R.id.ivPhotoForViewPager)
        Glide.with(context).load(photos[listPosition].uri).into(imageView)


    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return layoutInflater.inflate(R.layout.view_pager_layout, container, false)
    }

}