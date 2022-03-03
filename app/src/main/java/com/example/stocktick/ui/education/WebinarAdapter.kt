package com.example.stocktick.ui.education

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.databinding.EduWebinarItemBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.education.model.WebinarItem
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//TODO() --
class WebinarAdapter(
    val context: Context,
    private val webinarList: MutableList<WebinarItem>,
    private val tokenSharedPreference: String
) :
    RecyclerView.Adapter<WebinarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebinarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EduWebinarItemBinding.inflate(inflater, parent, false)
        return WebinarViewHolder(context, binding, tokenSharedPreference)
    }

    override fun onBindViewHolder(holder: WebinarViewHolder, position: Int) {

        val singleItem = webinarList[position]
        holder.bind(singleItem)
        //webinarUrl - if self hosted, then open
    }

    override fun getItemCount(): Int {
        return webinarList.size
    }


}

//ViewHolder
class WebinarViewHolder(
    val context: Context,
    private val binding: EduWebinarItemBinding,
    val tokenSharedPreference: String
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(singleItem: WebinarItem?) {
        val hosting = singleItem?.hosted_by.toString()
        //toggle card colour
        if (hosting == "other") {
            binding.eduWebinarGradientLayout.setBackgroundResource(R.drawable.gradient_webinar_outside)
        }

        binding.webinarTitle.text = singleItem?.title
        binding.webinarHostedBy.text = singleItem?.hosted_by

        Glide.with(context).load(singleItem?.image_url).into(binding.webinarImageUrl)

        binding.eduWebinarCard.setOnClickListener {
            Log.d("eduWebinarCard", "TAPPED ON THIS WEBINAR CARD")
            if (hosting == "self") {
                //show a loading icon here while retorifit call is happening
                binding.progressWebinar.visibility = View.VISIBLE
                binding.eduWebinarGradientLayout.visibility = View.INVISIBLE
                postRequestWebinar()
                binding.progressWebinar.visibility = View.INVISIBLE
                binding.eduWebinarGradientLayout.visibility = View.VISIBLE
            }
        }
    }

    @DelicateCoroutinesApi
    private fun postRequestWebinar() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postRegisterToWebinar(
                        tokenSharedPreference)
                Log.d("TAGpostreq", response.toString() + "\n")


            } catch (error: Exception) {
                Toast.makeText(context, "Request failed Network ERROR", Toast.LENGTH_SHORT)
                    .show()
                Log.d("ERROR", error.toString())
                //Error 404 but why?
            }
        }
    }
}
