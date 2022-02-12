package com.example.stocktick.ui.loan

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.databinding.LoanBottomBinding
import com.example.stocktick.databinding.LoanItemBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.insurance.InsuranceFragment
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


class LoanAdapter(private val loanList: MutableList<LoanItem>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private val itemHeader = 21
    private val itemBody = 20
    private val itemFooter = 22
    private val phoneREGEXPattern = Regex("^[0-9]{9,12}$")
    private lateinit var dialog : Dialog

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
            body.binding.loanCard.setCardBackgroundColor(Color.parseColor(this.color_code))
            body.binding.loanShortDesc.text = this.short_desc
            Glide.with(context).load(this.image_urls).into(body.binding.loanImg);
            body.binding.getQuote.setOnClickListener{
                dialog = Dialog(context)
                //dialog.setTitle("Information")
                dialog.setCancelable(true)
                dialog.setContentView(R.layout.loan_dialog)
                val name = dialog.findViewById(R.id.loan_form_name) as EditText
                val phone = dialog.findViewById(R.id.loan_form_phone) as EditText
                val email = dialog.findViewById(R.id.loan_form_email) as EditText
                val business = dialog.findViewById(R.id.loan_form_business) as EditText
                val businessTitle = dialog.findViewById(R.id.business_title) as TextView
                val businessCard = dialog.findViewById(R.id.business_card) as CardView
                val submitBtn = dialog.findViewById(R.id.loan_form_submit) as CardView

                if(this.category?.length!! >8 && this.category.substring(0,8) == "Business"){
                    businessCard.visibility= View.VISIBLE
                    businessTitle.visibility= View.VISIBLE
                    submitBtn.setOnClickListener {
                        if (name.text.isEmpty()) {
                            name.error = "Please enter your name"
                        } else if (email.text.isEmpty()) {
                            email.error = "Please enter your email id"
                        }
                        else if(!phone.text.matches(phoneREGEXPattern)){
                            phone.error = "Please enter a correct number"
                        }
                        else if(business.text.isEmpty()){
                            business.error = "Please enter your organization name"
                        }
                        else{
                            val item = LoanFormItem(name.text.toString(), phone.text.toString().trim(),email.text.toString().trim(),this.category,business.text.toString())
                            //Toast.makeText(context,"DONE",Toast.LENGTH_SHORT).show()
                            submitLoanDetails(item)
                        }
                    }
                }
                else{
                    businessCard.visibility= View.GONE
                    businessTitle.visibility= View.GONE
                    submitBtn.setOnClickListener {
                        if (name.text.isEmpty()) {
                            name.error = "Please enter your name"
                        } else if (email.text.isEmpty()) {
                            email.error = "Please enter your email id"
                        }
                        else if(!phone.text.matches(phoneREGEXPattern)){
                            phone.error = "Please enter a correct number"
                        }
                        else{
                            val item = LoanFormItem(name.text.toString(), phone.text.toString().trim(),email.text.toString().trim(),this.category,business.text.toString())
                            //Toast.makeText(context,"DONE",Toast.LENGTH_SHORT).show()
                            submitLoanDetails(item)
                        }
                    }
                }

                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
//                val lp = WindowManager.LayoutParams()
//                lp.copyFrom(dialog.window?.attributes)
//                lp.width = WindowManager.LayoutParams.MATCH_PARENT
//                lp.height = WindowManager.LayoutParams.WRAP_CONTENT

                dialog.show()
//                dialog.window?.attributes = lp
                val metrics: DisplayMetrics = context.resources.displayMetrics
                val width = metrics.widthPixels
                val height = metrics.heightPixels
                //yourDialog.getWindow().setLayout((6 * width)/7, )
                dialog.window?.setLayout((4 * width) / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
    }
    @DelicateCoroutinesApi
    private fun submitLoanDetails(item: LoanFormItem){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClientInstance.retrofitService.addLoanDetails("b6ceeaf9-ee67-4b40-906e-97125eae5bff",item)
                setAdapter(response)

            } catch (error: Exception) {
                Toast.makeText(context, "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                        .show()
                Log.d("ERROR_LOGINFRAGMENT", error.toString())
            }

        }
    }
    private fun setAdapter(response: Response<GetOtpModel>){
        if(response.code()==200){
            dialog.dismiss()
            val dia = Dialog(context)
            //dialog.setTitle("Information")
            dia.setCancelable(true)
            dia.setContentView(R.layout.success_form)
            dia.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val metrics: DisplayMetrics = context.resources.displayMetrics
            val width = metrics.widthPixels
            val height = metrics.heightPixels
            //yourDialog.getWindow().setLayout((6 * width)/7, )
            dia.window?.setLayout((4 * width) / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
            val mainHandler =  Handler(Looper.getMainLooper())
            dia.show()
        }
        else{
            Toast.makeText(context,"Bad Request",Toast.LENGTH_SHORT).show()
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