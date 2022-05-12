package com.example.stocktick.ui.loan

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.databinding.LoanBottomBinding
import com.example.stocktick.databinding.LoanItemBinding
import com.example.stocktick.network.RetrofitClientInstance
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
    private lateinit var dialog: Dialog
    private lateinit var token: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //val v = LayoutInflater.from(parent.context).inflate(R.layout.loan_item, parent, false)
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("USER", Activity.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "").toString()
        return when (viewType) {
            itemBody -> LoanViewHolder(LoanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> FooterHolder(LoanBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//            else -> FooterHolder(LoanBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == loanList.size - 1) {
            return
        }

        val body: LoanViewHolder = holder as LoanViewHolder
        with(loanList[position]) {
            Log.d("ANAM: title ", this.image_url.toString())
            body.binding.loanTitle.text = this.category
            body.binding.loanInterest.text = this.interest
            body.binding.loanCard.setCardBackgroundColor(Color.parseColor(this.color_code))
            body.binding.loanShortDesc.text = this.short_desc
            Glide.with(context)
                    .load(this.image_url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(body.binding.loanImg)

            body.binding.getQuote.setOnClickListener {
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

                val dobCard = dialog.findViewById(R.id.dob_card) as CardView
                val coverLifeCard = dialog.findViewById(R.id.coverLife_card) as CardView
                val coverUptoCard = dialog.findViewById(R.id.coverUpto_card) as CardView
                val amountCard = dialog.findViewById(R.id.amount_card) as CardView
                val manuCard = dialog.findViewById(R.id.manufacture_card) as CardView
                val variantCard = dialog.findViewById(R.id.variant_card) as CardView
                val pucCard = dialog.findViewById(R.id.puc_card) as CardView
                val pucExpCard = dialog.findViewById(R.id.puc_exp_card) as CardView
                val vehicleCard = dialog.findViewById(R.id.vehicle_no_card) as CardView
                val insurerCard = dialog.findViewById(R.id.insurer_card) as CardView
                val policyExpCard = dialog.findViewById(R.id.policy_exp_card) as CardView
                val policyClaimCard = dialog.findViewById(R.id.policy_claim_card) as CardView
                val policyTypeCard = dialog.findViewById(R.id.policy_type_card) as CardView
                val modelCard = dialog.findViewById(R.id.model_card) as CardView
                val manuYearCard = dialog.findViewById(R.id.manufacture_year_card) as CardView
                val fuelCard = dialog.findViewById(R.id.fuel_type_card) as CardView
                val ownershipCard = dialog.findViewById(R.id.ownership_transfer_card) as CardView
                val addressCard = dialog.findViewById(R.id.address_card) as CardView
                val ageCard = dialog.findViewById(R.id.age_card) as CardView
                val familyCard = dialog.findViewById(R.id.select_family_card) as CardView
                val medicalCard = dialog.findViewById(R.id.medical_cond_card) as CardView

                val medical = dialog.findViewById(R.id.medical_cond_title) as TextView
                val family = dialog.findViewById(R.id.select_family_title) as TextView
                val age = dialog.findViewById(R.id.age_title) as TextView
                val address = dialog.findViewById(R.id.address_title) as TextView
                val dob = dialog.findViewById(R.id.dob_title) as TextView
                val coverLife = dialog.findViewById(R.id.coverLife_title) as TextView
                val coverUpto = dialog.findViewById(R.id.coverUpto_title) as TextView
                val amount = dialog.findViewById(R.id.amount_title) as TextView
                val policyType = dialog.findViewById(R.id.policy_type_title) as TextView
                val fuelType = dialog.findViewById(R.id.fuel_type_title) as TextView
                val manuYear = dialog.findViewById(R.id.manufacture_year_title) as TextView
                val manu = dialog.findViewById(R.id.manufacture_title) as TextView
                val model = dialog.findViewById(R.id.model_title) as TextView
                val puc = dialog.findViewById(R.id.puc_title) as TextView
                val pucExp = dialog.findViewById(R.id.puc_exp_title) as TextView
                val vehicle = dialog.findViewById(R.id.vehicle_no_title) as TextView
                val insurer = dialog.findViewById(R.id.insurer_title) as TextView
                val policyExp = dialog.findViewById(R.id.policy_exp_title) as TextView
                val policyClaim = dialog.findViewById(R.id.policy_claim_title) as TextView
                val variant = dialog.findViewById(R.id.variant_title) as TextView
                val ownership = dialog.findViewById(R.id.ownership_transfer_title) as TextView


                medical.visibility = View.GONE
                medicalCard.visibility = View.GONE
                address.visibility = View.GONE
                addressCard.visibility = View.GONE
                policyClaimCard.visibility = View.GONE
                ownership.visibility = View.GONE
                ownershipCard.visibility = View.GONE
                policyType.visibility = View.GONE
                fuelCard.visibility = View.GONE
                fuelType.visibility = View.GONE
                manuYear.visibility = View.GONE
                manuYearCard.visibility = View.GONE
                modelCard.visibility = View.GONE
                manuCard.visibility = View.GONE
                manu.visibility = View.GONE
                variantCard.visibility = View.GONE
                variant.visibility = View.GONE
                pucCard.visibility = View.GONE
                pucExpCard.visibility = View.GONE
                vehicleCard.visibility = View.GONE
                insurerCard.visibility = View.GONE
                policyExpCard.visibility = View.GONE
                policyTypeCard.visibility = View.GONE
                puc.visibility = View.GONE
                pucExp.visibility = View.GONE
                policyClaim.visibility = View.GONE
                policyExp.visibility = View.GONE
                insurer.visibility = View.GONE
                vehicle.visibility = View.GONE
                model.visibility = View.GONE
                dob.visibility = View.GONE
                dobCard.visibility = View.GONE
                coverLife.visibility = View.GONE
                coverLifeCard.visibility = View.GONE
                coverUpto.visibility = View.GONE
                coverUptoCard.visibility = View.GONE
                amount.visibility = View.GONE
                amountCard.visibility = View.GONE
                age.visibility = View.GONE
                ageCard.visibility = View.GONE
                family.visibility = View.GONE
                familyCard.visibility = View.GONE


                if (this.category?.length!! > 8 && this.category.substring(0, 8) == "Business") {
                    businessCard.visibility = View.VISIBLE
                    businessTitle.visibility = View.VISIBLE
                    submitBtn.setOnClickListener {
                        if (name.text.isEmpty()) {
                            name.error = "Please enter your name"
                        } else if (email.text.isEmpty()) {
                            email.error = "Please enter your email id"
                        } else if (!phone.text.matches(phoneREGEXPattern)) {
                            phone.error = "Please enter a correct number"
                        } else if (business.text.isEmpty()) {
                            business.error = "Please enter your organization name"
                        } else {
                            val item = LoanFormItem(name.text.toString(), phone.text.toString().trim(), email.text.toString().trim(), this.category, business.text.toString())
                            //Toast.makeText(context,"DONE",Toast.LENGTH_SHORT).show()
                            submitLoanDetails(item)
                        }
                    }
                } else {
                    businessCard.visibility = View.GONE
                    businessTitle.visibility = View.GONE
                    submitBtn.setOnClickListener {
                        if (name.text.isEmpty()) {
                            name.error = "Please enter your name"
                        } else if (email.text.isEmpty()) {
                            email.error = "Please enter your email id"
                        } else if (!phone.text.matches(phoneREGEXPattern)) {
                            phone.error = "Please enter a correct number"
                        } else {
                            val item = LoanFormItem(name.text.toString(), phone.text.toString().trim(), email.text.toString().trim(), this.category, business.text.toString())
                            //Toast.makeText(context,"DONE",Toast.LENGTH_SHORT).show()
                            submitLoanDetails(item)
                        }
                    }
                }

                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                dialog.show()
                val metrics: DisplayMetrics = context.resources.displayMetrics
                val width = metrics.widthPixels
                val height = metrics.heightPixels
                dialog.window?.setLayout((4 * width) / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
    }

    @DelicateCoroutinesApi
    private fun submitLoanDetails(item: LoanFormItem) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClientInstance.retrofitService.addLoanDetails(token, item)
                setAdapter(response)

            } catch (error: Exception) {
                Toast.makeText(context, "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                        .show()
            }

        }
    }

    private fun setAdapter(response: Response<GetOtpModel>) {
        if (response.code() == 200) {
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
            val mainHandler = Handler(Looper.getMainLooper())
            dia.show()
        } else {
            Toast.makeText(context, "Bad Request", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            //0 -> itemFooter
            loanList.size - 1 -> itemFooter
            else -> itemBody
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int {
        return loanList.size
    }

    class LoanViewHolder(val binding: LoanItemBinding) : RecyclerView.ViewHolder(binding.root)
    class FooterHolder(val binding: LoanBottomBinding) : RecyclerView.ViewHolder(binding.root)
}