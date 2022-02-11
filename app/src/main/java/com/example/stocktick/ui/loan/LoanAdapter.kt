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
import com.example.stocktick.ui.insurance.InsuranceFragment


class LoanAdapter(private val loanList: MutableList<LoanItem>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private val itemHeader = 21
    private val itemBody = 20
    private val itemFooter = 22

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.loan_item, parent, false)
        return when(viewType){
            itemBody ->  LoanViewHolder(LoanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else ->  FooterHolder(LoanBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//            else -> FooterHolder(LoanBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == loanList.size-1){
            return
        }

        val body : LoanViewHolder = holder as LoanViewHolder
        with(loanList[position]){
            body.binding.loanTitle.text = this.category
            body.binding.loanInterest.text = this.interest
            body.binding.loanCard.setCardBackgroundColor(Color.parseColor(this.colorCode))
            body.binding.loanShortDesc.text = this.short_desc
            Glide.with(context).load(this.image_url).into(body.binding.loanImg);
            body.binding.getQuote.setOnClickListener{
//                val i = Intent(Intent.ACTION_VIEW)
//                i.data = Uri.parse(this.link)
//                startActivity(context,i,null)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when (position) {
            //0 -> itemFooter
            loanList.size-1 -> itemFooter
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
    class FooterHolder(val binding : LoanBottomBinding) : RecyclerView.ViewHolder(binding.root)
}