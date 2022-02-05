package com.example.stocktick.ui.loan

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.databinding.LoanBottomBinding
import com.example.stocktick.databinding.LoanItemBinding
import com.example.stocktick.databinding.LoanTopBinding
import com.example.stocktick.ui.insurance.InsuranceFragment


class LoanAdapter(private val loanList: MutableList<LoanItem>,private val carList: List<LoanItem>,
                  private val bikeList: List<LoanItem>,private val personalList: List<LoanItem>,
                  private val homeList: List<LoanItem>, private val context: Context, private val num: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemHeader = 21
    private val itemBody = 20
    private val itemFooter = 22
    private var flag=0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.loan_item, parent, false)
        return when(viewType){
            itemBody ->  LoanViewHolder(LoanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else ->  HeaderHolder(LoanTopBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//            else -> FooterHolder(LoanBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.adapterPosition == 0){
            val header : HeaderHolder = holder as HeaderHolder
            holder.binding.bikeCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
            holder.binding.homeCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
            holder.binding.personalCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
            holder.binding.carCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
            if(num==2){
                holder.binding.loanTitle.text = context.getString(R.string.insurance_title)
            }
            if(flag==1){
                holder.binding.bikeCard.setCardBackgroundColor(Color.parseColor("#2666CF"))
                holder.binding.homeCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.personalCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.carCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.topInsurancePolicies.visibility = View.VISIBLE
            }
            else if(flag==2){
                holder.binding.homeCard.setCardBackgroundColor(Color.parseColor("#2666CF"))
                holder.binding.bikeCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.personalCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.carCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.topInsurancePolicies.visibility = View.VISIBLE
            }
            else if(flag==3){
                holder.binding.carCard.setCardBackgroundColor(Color.parseColor("#2666CF"))
                holder.binding.homeCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.personalCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.bikeCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.topInsurancePolicies.visibility = View.VISIBLE
            }
            else if(flag==4){
                holder.binding.personalCard.setCardBackgroundColor(Color.parseColor("#2666CF"))
                holder.binding.homeCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.bikeCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.carCard.setCardBackgroundColor(Color.parseColor("#2C3333"))
                holder.binding.topInsurancePolicies.visibility = View.VISIBLE
            }
            else{
                holder.binding.topInsurancePolicies.visibility = View.GONE
            }
            header.binding.bikeCard.setOnClickListener{
                flag=1
                loanList.clear()
                loanList.add(LoanItem())
                for(bike in bikeList){
                    loanList.add(LoanItem(bike.link,bike.short_desc,bike.long_desc,bike.image_url,bike.category,bike.interest))
                }
                notifyDataSetChanged()
            }
            header.binding.homeCard.setOnClickListener{
                flag=2
                loanList.clear()
                loanList.add(LoanItem())
                for(home in homeList){
                    loanList.add(LoanItem(home.link,home.short_desc,home.long_desc,home.image_url,home.category,home.interest))
                }
                notifyDataSetChanged()
            }
            header.binding.carCard.setOnClickListener{
                flag=3
                loanList.clear()
                loanList.add(LoanItem())
                for(car in carList){
                    loanList.add(LoanItem(car.link,car.short_desc,car.long_desc,car.image_url,car.category,car.interest))
                }
                notifyDataSetChanged()
            }
            header.binding.personalCard.setOnClickListener{
                flag=4
                loanList.clear()
                loanList.add(LoanItem())
                for(personal in personalList){
                    loanList.add(LoanItem(personal.link,personal.short_desc,personal.long_desc,personal.image_url,personal.category,personal.interest))
                }
                notifyDataSetChanged()
            }

            return
        }
        //notifyDataSetChanged()

        val body : LoanViewHolder = holder as LoanViewHolder
        with(loanList[holder.adapterPosition]){
            body.binding.loanCategory.text = this.category
            body.binding.loanInterest.text = this.interest
            body.binding.loanLongDesc.text = this.long_desc
            body.binding.loanShortDesc.text = this.short_desc
            Glide.with(context).load(this.image_url).into(body.binding.loanImg);
            body.binding.loanKnowMore.setOnClickListener{
//                val url = "http://www.example.com"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(this.link)
                startActivity(context,i,null)
            }
        }
        //holder.bindItems(loanList[position])
    }
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            //0 -> itemFooter
            0 -> itemHeader
            else ->itemBody
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return loanList.size
    }
    class LoanViewHolder(val binding: LoanItemBinding) : RecyclerView.ViewHolder(binding.root)
    class HeaderHolder(val binding : LoanTopBinding) : RecyclerView.ViewHolder(binding.root)
    class FooterHolder(val binding : LoanBottomBinding) : RecyclerView.ViewHolder(binding.root)
}