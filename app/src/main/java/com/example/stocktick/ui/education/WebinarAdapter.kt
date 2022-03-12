package com.example.stocktick.ui.education

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.databinding.EduWebinarItemBinding
import com.example.stocktick.ui.education.model.WebinarItem

class WebinarAdapter( val context: Context, private val webinarList: MutableList<WebinarItem>,
    var educationInterfaceClickListener: EducationInterface) :
    RecyclerView.Adapter<WebinarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebinarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EduWebinarItemBinding.inflate(inflater, parent, false)
        return WebinarViewHolder(
            context,
            binding,
            educationInterfaceClickListener
        )
    }

    override fun onBindViewHolder(holder: WebinarViewHolder, position: Int) {
        val singleItem = webinarList[position]
        holder.bind(singleItem)
    }

    override fun getItemCount(): Int {
        return webinarList.size
    }
}

//ViewHolder
class WebinarViewHolder(
    val context: Context,
    private val binding: EduWebinarItemBinding,
    private val educationInterfaceClickListener: EducationInterface
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(singleItem: WebinarItem?) {
        var hosting = singleItem?.hosted_by.toString()

        //toggle card colour
        if (hosting == "other") {
            binding.eduWebinarGradientLayout.setBackgroundResource(R.drawable.gradient_webinar_outside)
            hosting = " "+singleItem?.other_host_name.toString()
        }else{
            hosting = " Self"
        }
        //binding attaching
        binding.webinarTitle.text = singleItem?.title
        binding.webinarHostedBy.text = hosting
        binding.webinarDate.text = singleItem?.date
        Glide.with(context).load(singleItem?.image_url).into(binding.webinarImageUrl)

        //so the real reason and magic of the interface is happening here.
        //we only want to click on a particular id and send it back to the fragment to do retrofi
        //we put up the progress bar and other things also here i think?
        //or do we?
        binding.webinarRegisterButton.setOnClickListener {
            educationInterfaceClickListener.onWebinarClickListener(
                singleItem?.id,
                singleItem?.hosted_by,
                singleItem?.webinar_redirect_url
            )
        }

    }
}
