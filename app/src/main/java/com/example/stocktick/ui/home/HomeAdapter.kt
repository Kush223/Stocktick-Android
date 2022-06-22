package com.example.stocktick.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.example.stocktick.R

class HomeAdapter
constructor(
    private val context: Context,
    private val serviceList: List<HomeItem>,
    val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
): LoopingPagerAdapter<HomeItem>(serviceList,true)  {


    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val imageView: ImageView =  convertView.findViewById(R.id.service_image)
        val textView: TextView = convertView.findViewById(R.id.service_text)
        val pos = serviceList[listPosition % serviceList.size]
        pos.img?.let { imageView.setImageResource(it) }
        textView.text = pos.txt
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return layoutInflater.inflate(R.layout.home_services, container, false)

    }


}