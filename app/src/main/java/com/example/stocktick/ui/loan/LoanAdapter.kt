package com.example.stocktick.ui.loan

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.databinding.LoanItemBinding

class LoanAdapter(private val loanList: List<LoanItem>, private val context: Context) : RecyclerView.Adapter<LoanAdapter.LoanViewHolder>() {
    private lateinit var binding: LoanItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.loan_item, parent, false)
        binding = LoanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        with(loanList[position]){
            binding.loanCategory.text = this.category
            binding.loanInterest.text = this.interest
            binding.loanLongDesc.text = this.long_desc
            binding.loanShortDesc.text = this.short_desc
            Glide.with(context).load(this.image_urls).into(binding.loanImg);
            binding.loanKnowMore.setOnClickListener{
                val url = "http://www.example.com"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(context,i,null)
            }
        }
        //holder.bindItems(loanList[position])
    }

    override fun getItemCount(): Int {
        return loanList.size
    }
    class LoanViewHolder(private val binding: LoanItemBinding) : RecyclerView.ViewHolder(binding.root) {}

}