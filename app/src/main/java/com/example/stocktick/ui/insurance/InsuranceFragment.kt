package com.example.stocktick.ui.insurance

import android.app.Activity
import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.R
import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.databinding.FragmentInsuranceBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.loan.LoanFormItem
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


class InsuranceFragment : Fragment() {
    private lateinit var insuranceViewModel: InsuranceViewModel
    private lateinit var binding: FragmentInsuranceBinding
    private lateinit var motorCard: CardView
    private lateinit var healthCard: CardView
    private lateinit var lifeCard: CardView
    private lateinit var token: String

    private lateinit var policyClaimYes : RadioButton
    private lateinit var pucYes : RadioButton
    private lateinit var ownYes : RadioButton
    private lateinit var pucRadioGroup : RadioGroup
    private lateinit var policyRadioGroup : RadioGroup
    private lateinit var ownRadioGroup : RadioGroup

    private lateinit var name : EditText
    private lateinit var phone : EditText
    private lateinit var email : EditText
    private lateinit var business : EditText
    private lateinit var vehicleNum : EditText
    private lateinit var manuEdit : EditText
    private lateinit var modelEdit : EditText
    private lateinit var variantEdit : EditText
    private lateinit var insurerEdit : EditText
    private lateinit var pucEdit : EditText
    private lateinit var policyTypeEdit : EditText
    private lateinit var policyExpEdit : EditText
    private lateinit var addressEdit : EditText
    private lateinit var amountEdit : EditText
    private lateinit var dobEdit : EditText
    private lateinit var coverUptoEdit : EditText
    private lateinit var coverLifeEdit : EditText

    private lateinit var coverLifeCard : CardView
    private lateinit var familyCard : CardView
    private lateinit var addressCard : CardView
    private lateinit var amountCard : CardView
    private lateinit var dobCard : CardView
    private lateinit var ageCard : CardView
    private lateinit var coverUptoCard : CardView
    private lateinit var businessCard : CardView
    private lateinit var submitBtn : CardView
    private lateinit var pucExpCard : CardView
    private lateinit var pucCard : CardView
    private lateinit var variantCard : CardView
    private lateinit var manuCard : CardView
    private lateinit var vehicleCard : CardView
    private lateinit var insurerCard : CardView
    private lateinit var policyClaimCard : CardView
    private lateinit var policyTypeCard : CardView
    private lateinit var policyExpCard : CardView
    private lateinit var modelCard : CardView
    private lateinit var manuYearCard : CardView
    private lateinit var fuelCard : CardView
    private lateinit var ownershipCard : CardView
    private lateinit var medicalCard : CardView

    private lateinit var family : TextView
    private lateinit var medical : TextView
    private lateinit var age : TextView
    private lateinit var address : TextView
    private lateinit var dob : TextView
    private lateinit var amount : TextView
    private lateinit var coverUpto : TextView
    private lateinit var coverLife : TextView
    private lateinit var manuYear : TextView
    private lateinit var variant : TextView
    private lateinit var insurer : TextView
    private lateinit var pucExp : TextView
    private lateinit var policyExp : TextView
    private lateinit var businessTitle : TextView
    private lateinit var manu : TextView
    private lateinit var fuelType : TextView
    private lateinit var model : TextView
    private lateinit var puc : TextView
    private lateinit var policyClaim : TextView
    private lateinit var vehicle : TextView
    private lateinit var ownership : TextView
    private lateinit var policyType : TextView

    private lateinit var fuelDropdown : Spinner
    private lateinit var manuDropdown : Spinner
    private lateinit var familyDropdown : Spinner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = InsuranceViewModelFactory(requireContext())
        insuranceViewModel = ViewModelProvider(
                this, viewModelFactory
        )[InsuranceViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = "Insurance"
        motorCard = binding.bikeCard
        healthCard = binding.carCard
        lifeCard = binding.homeCard
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("USER", Activity.MODE_PRIVATE)
        token = sharedPreferences.getString("token","a").toString()


        motorCard.setOnClickListener{
            val dialog = Dialog(requireActivity())
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.loan_dialog)

            initializeDialog(dialog)

            medical.visibility= View.GONE
            medicalCard.visibility= View.GONE
            age.visibility= View.GONE
            ageCard.visibility= View.GONE
            family.visibility= View.GONE
            familyCard.visibility= View.GONE
            businessCard.visibility= View.GONE
            businessTitle.visibility= View.GONE
            addressCard.visibility= View.GONE
            address.visibility = View.GONE
            dob.visibility = View.GONE
            dobCard.visibility = View.GONE
            coverLife.visibility = View.GONE
            coverLifeCard.visibility = View.GONE
            coverUpto.visibility = View.GONE
            coverUptoCard.visibility = View.GONE
            amount.visibility = View.GONE
            amountCard.visibility = View.GONE
            val submitBtn = dialog.findViewById(R.id.loan_form_submit) as CardView
            submitBtn.setOnClickListener {
                val polId: Int = policyRadioGroup.checkedRadioButtonId
                val pucId: Int = pucRadioGroup.checkedRadioButtonId
                val ownId: Int = ownRadioGroup.checkedRadioButtonId
                val manu = manuDropdown.selectedItem.toString()
                val fuel = fuelDropdown.selectedItem.toString()
                if (polId == -1) {
                    policyClaimYes.error = "Please select yes or no"
                }
                else if(pucId == -1){
                    pucYes.error = "Please select yes or no"
                }
                else if(ownId == -1){
                    ownYes.error = "Please select yes or no"
                }
                else if(name.text.isEmpty()){
                    name.error = "Please enter your name"
                }
                else if(phone.text.isEmpty()){
                    phone.error = "Please enter your phone"
                }
                else if(email.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(manuEdit.text.isEmpty()){
                    manuEdit.error = "Please enter your manufacturer year"
                }
                else if(vehicleNum.text.isEmpty()){
                    vehicleNum.error = "Please enter your vehicle number"
                }
                else if(variantEdit.text.isEmpty()){
                    variantEdit.error = "Please enter the variant name"
                }
                else if(modelEdit.text.isEmpty()){
                    modelEdit.error = "Please enter the model name"
                }
                else if(insurerEdit.text.isEmpty()){
                    insurerEdit.error = "Please enter the insurer name"
                }
                else if(pucEdit.text.isEmpty()){
                    pucEdit.error = "Please enter your puc expiry date"
                }
                else if(policyExpEdit.text.isEmpty()){
                    policyExpEdit.error = "Please enter the policy expiry date"
                }
                else if(policyTypeEdit.text.isEmpty()){
                    policyTypeEdit.error = "Please enter the policy type"
                }
                else{
                    val pucString: String = if(pucId == R.id.loan_form_puc_yes){
                        "Y"
                    } else{
                        "N"
                    }
                    val polString: String = if(polId == R.id.loan_form_policy_claim_yes){
                        "Y"
                    } else{
                        "N"
                    }
                    val ownString: String = if(ownId == R.id.loan_form_ownership_transfer_yes){
                        "Y"
                    } else{
                        "N"
                    }
                    val item = InsuranceModel("motor",name.text.toString(),email.text.toString(),phone.text.toString(),
                            null,manuEdit.text.toString(),variantEdit.text.toString(),insurerEdit.text.toString(),
                            pucEdit.text.toString(),policyTypeEdit.text.toString(),null,manu,fuel, modelEdit.text.toString(),
                            pucString,polString,vehicleNum.text.toString(),ownString,policyTypeEdit.text.toString())
                    submitInsuranceDetails(item,dialog)
                }
            }

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
            val metrics: DisplayMetrics = requireActivity().resources.displayMetrics
            val width = metrics.widthPixels
            val height = metrics.heightPixels
            dialog.window?.setLayout((4 * width) / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        lifeCard.setOnClickListener{
            val dialog = Dialog(requireActivity())
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.loan_dialog)

            initializeDialog(dialog)

            medical.visibility= View.GONE
            medicalCard.visibility= View.GONE
            age.visibility= View.GONE
            ageCard.visibility= View.GONE
            family.visibility= View.GONE
            familyCard.visibility= View.GONE
            businessCard.visibility= View.GONE
            businessTitle.visibility= View.GONE
            policyClaimCard.visibility=View.GONE
            ownership.visibility=View.GONE
            ownershipCard.visibility=View.GONE
            policyType.visibility=View.GONE
            fuelCard.visibility=View.GONE
            fuelType.visibility=View.GONE
            manuYear.visibility=View.GONE
            manuYearCard.visibility=View.GONE
            modelCard.visibility=View.GONE
            manuCard.visibility=View.GONE
            manu.visibility=View.GONE
            variantCard.visibility=View.GONE
            variant.visibility=View.GONE
            pucCard.visibility=View.GONE
            pucExpCard.visibility=View.GONE
            vehicleCard.visibility=View.GONE
            insurerCard.visibility=View.GONE
            policyExpCard.visibility=View.GONE
            policyTypeCard.visibility=View.GONE
            puc.visibility = View.GONE
            pucExp.visibility = View.GONE
            policyClaim.visibility = View.GONE
            policyExp.visibility = View.GONE
            insurer.visibility = View.GONE
            vehicle.visibility = View.GONE
            model.visibility=View.GONE
            val submitBtn = dialog.findViewById(R.id.loan_form_submit) as CardView
            submitBtn.setOnClickListener {
                if(name.text.isEmpty()){
                    name.error = "Please enter your name"
                }
                else if(phone.text.isEmpty()){
                    phone.error = "Please enter your phone"
                }
                else if(email.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(addressEdit.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(dobEdit.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(coverLifeEdit.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(coverUptoEdit.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(amountEdit.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else{
                    val item = InsuranceModel("life",name.text.toString(),email.text.toString(),phone.text.toString(),
                            addressEdit.text.toString(),null,null,null, null,
                            null,null,null,null, null, null,null,
                            null,null,null,null,null,null,
                            null,null,dobEdit.text.toString(),coverLifeEdit.text.toString(),coverUptoEdit.text.toString(),
                            amountEdit.text.toString()
                    )
                    submitInsuranceDetails(item,dialog)
                }
            }

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
            val metrics: DisplayMetrics = requireActivity().resources.displayMetrics
            val width = metrics.widthPixels
            val height = metrics.heightPixels
            dialog.window?.setLayout((4 * width) / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        healthCard.setOnClickListener{
            val dialog = Dialog(requireActivity())
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.loan_dialog)

            initializeDialog(dialog)

            coverLife.visibility = View.GONE
            coverLifeCard.visibility = View.GONE
            coverUpto.visibility = View.GONE
            coverUptoCard.visibility = View.GONE
            amount.visibility = View.GONE
            amountCard.visibility = View.GONE
            dob.visibility = View.GONE
            dobCard.visibility = View.GONE
            businessCard.visibility= View.GONE
            businessTitle.visibility= View.GONE
            policyClaimCard.visibility=View.GONE
            ownership.visibility=View.GONE
            ownershipCard.visibility=View.GONE
            policyType.visibility=View.GONE
            fuelCard.visibility=View.GONE
            fuelType.visibility=View.GONE
            manuYear.visibility=View.GONE
            manuYearCard.visibility=View.GONE
            modelCard.visibility=View.GONE
            manuCard.visibility=View.GONE
            manu.visibility=View.GONE
            variantCard.visibility=View.GONE
            variant.visibility=View.GONE
            pucCard.visibility=View.GONE
            pucExpCard.visibility=View.GONE
            vehicleCard.visibility=View.GONE
            insurerCard.visibility=View.GONE
            policyExpCard.visibility=View.GONE
            policyTypeCard.visibility=View.GONE
            puc.visibility = View.GONE
            pucExp.visibility = View.GONE
            policyClaim.visibility = View.GONE
            policyExp.visibility = View.GONE
            insurer.visibility = View.GONE
            vehicle.visibility = View.GONE
            model.visibility=View.GONE
            val submitBtn = dialog.findViewById(R.id.loan_form_submit) as CardView
            submitBtn.setOnClickListener {
                val family = familyDropdown.selectedItem.toString()
                if(name.text.isEmpty()){
                    name.error = "Please enter your name"
                }
                else if(phone.text.isEmpty()){
                    phone.error = "Please enter your phone"
                }
                else if(email.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(addressEdit.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(age.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(medical.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else if(insurerEdit.text.isEmpty()){
                    email.error = "Please enter your email"
                }
                else{
                    val item = InsuranceModel("health",name.text.toString(),email.text.toString(),phone.text.toString(),
                            addressEdit.text.toString(),null,null,null, null,
                            null,null,null,null, null, null,null,
                            null,null,null,family,insurerEdit.text.toString(),null,
                            age.text.toString(),medical.text.toString(),null,null,null, null
                    )
                    submitInsuranceDetails(item,dialog)
                }
            }

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
            val metrics: DisplayMetrics = requireActivity().resources.displayMetrics
            val width = metrics.widthPixels
            val height = metrics.heightPixels
            dialog.window?.setLayout((4 * width) / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsuranceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.help_button) {
            // do something here
        }
        return super.onOptionsItemSelected(item)
    }
    @DelicateCoroutinesApi
    private fun submitInsuranceDetails(item: InsuranceModel, dialog: Dialog){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = RetrofitClientInstance.retrofitService.addInsuranceDetails(token,item)
                setAdapter(response,dialog)

            } catch (error: Exception) {
                Toast.makeText(context, "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                        .show()
                Log.d("ERROR_LOGINFRAGMENT", error.toString())
            }

        }
    }
    private fun setAdapter(response: Response<GetOtpModel>, dialog: Dialog){
        if(response.code()==200){
            dialog.dismiss()
            val dia = Dialog(requireActivity())
            //dialog.setTitle("Information")
            dia.setCancelable(true)
            dia.setContentView(R.layout.success_form)
            dia.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val metrics: DisplayMetrics = context!!.resources.displayMetrics
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
    private fun initializeDialog(dialog: Dialog){
        name = dialog.findViewById(R.id.loan_form_name) as EditText
        phone = dialog.findViewById(R.id.loan_form_phone) as EditText
        email = dialog.findViewById(R.id.loan_form_email) as EditText
        business = dialog.findViewById(R.id.loan_form_business) as EditText
        vehicleNum = dialog.findViewById(R.id.loan_form_vehicle_no) as EditText
        manuEdit = dialog.findViewById(R.id.loan_form_manufacture_year) as EditText
        modelEdit = dialog.findViewById(R.id.loan_form_model) as EditText
        variantEdit = dialog.findViewById(R.id.loan_form_variant) as EditText
        insurerEdit = dialog.findViewById(R.id.loan_form_insurer) as EditText
        pucEdit = dialog.findViewById(R.id.loan_form_puc_exp) as EditText
        policyTypeEdit = dialog.findViewById(R.id.loan_form_policy_type) as EditText
        policyExpEdit = dialog.findViewById(R.id.loan_form_policy_exp) as EditText
        addressEdit = dialog.findViewById(R.id.loan_form_address) as EditText
        dobEdit = dialog.findViewById(R.id.loan_form_dob) as EditText
        coverLifeEdit = dialog.findViewById(R.id.loan_form_coverLife) as EditText
        coverUptoEdit = dialog.findViewById(R.id.loan_form_coverUpto) as EditText
        amountEdit = dialog.findViewById(R.id.loan_form_amount) as EditText

        dobCard = dialog.findViewById(R.id.dob_card) as CardView
        medicalCard = dialog.findViewById(R.id.medical_cond_card) as CardView
        ageCard = dialog.findViewById(R.id.age_card) as CardView
        coverLifeCard = dialog.findViewById(R.id.coverLife_card) as CardView
        coverUptoCard = dialog.findViewById(R.id.coverUpto_card) as CardView
        amountCard = dialog.findViewById(R.id.amount_card) as CardView
        addressCard = dialog.findViewById(R.id.address_card) as CardView
        businessCard = dialog.findViewById(R.id.business_card) as CardView
        submitBtn = dialog.findViewById(R.id.loan_form_submit) as CardView
        manuCard = dialog.findViewById(R.id.manufacture_card) as CardView
        variantCard = dialog.findViewById(R.id.variant_card) as CardView
        pucCard = dialog.findViewById(R.id.puc_card) as CardView
        pucExpCard = dialog.findViewById(R.id.puc_exp_card) as CardView
        vehicleCard = dialog.findViewById(R.id.vehicle_no_card) as CardView
        insurerCard = dialog.findViewById(R.id.insurer_card) as CardView
        policyExpCard = dialog.findViewById(R.id.policy_exp_card) as CardView
        policyClaimCard = dialog.findViewById(R.id.policy_claim_card) as CardView
        policyTypeCard = dialog.findViewById(R.id.policy_type_card) as CardView
        modelCard = dialog.findViewById(R.id.model_card) as CardView
        manuYearCard = dialog.findViewById(R.id.manufacture_year_card) as CardView
        fuelCard = dialog.findViewById(R.id.fuel_type_card) as CardView
        ownershipCard = dialog.findViewById(R.id.ownership_transfer_card) as CardView
        familyCard = dialog.findViewById(R.id.select_family_card) as CardView

        medical = dialog.findViewById(R.id.medical_cond_title) as TextView
        family = dialog.findViewById(R.id.select_family_title) as TextView
        dob = dialog.findViewById(R.id.dob_title) as TextView
        age = dialog.findViewById(R.id.age_title) as TextView
        coverLife = dialog.findViewById(R.id.coverLife_title) as TextView
        coverUpto = dialog.findViewById(R.id.coverUpto_title) as TextView
        amount = dialog.findViewById(R.id.amount_title) as TextView
        address = dialog.findViewById(R.id.address_title) as TextView
        policyType = dialog.findViewById(R.id.policy_type_title) as TextView
        fuelType = dialog.findViewById(R.id.fuel_type_title) as TextView
        manuYear = dialog.findViewById(R.id.manufacture_year_title) as TextView
        manu = dialog.findViewById(R.id.manufacture_title) as TextView
        model = dialog.findViewById(R.id.model_title) as TextView
        puc = dialog.findViewById(R.id.puc_title) as TextView
        pucExp = dialog.findViewById(R.id.puc_exp_title) as TextView
        vehicle = dialog.findViewById(R.id.vehicle_no_title) as TextView
        insurer = dialog.findViewById(R.id.insurer_title) as TextView
        policyExp = dialog.findViewById(R.id.policy_exp_title) as TextView
        policyClaim = dialog.findViewById(R.id.policy_claim_title) as TextView
        variant = dialog.findViewById(R.id.variant_title) as TextView
        businessTitle = dialog.findViewById(R.id.business_title) as TextView
        ownership = dialog.findViewById(R.id.ownership_transfer_title) as TextView

        policyRadioGroup = dialog.findViewById(R.id.policy_claim_radio)
        pucRadioGroup = dialog.findViewById(R.id.puc_radio)
        pucYes = dialog.findViewById(R.id.loan_form_puc_yes)
        policyClaimYes = dialog.findViewById(R.id.loan_form_policy_claim_yes)
        ownRadioGroup = dialog.findViewById(R.id.ownership_radio)
        ownYes = dialog.findViewById(R.id.loan_form_ownership_transfer_yes)


        fuelDropdown = dialog.findViewById(R.id.loan_form_fuel_type) as Spinner
        val fuelItems = arrayOf("CNG", "LPG", "PETROL")
        val fuelAdapter = ArrayAdapter(requireActivity(), R.layout.spinner_item, fuelItems)
        fuelDropdown.adapter = fuelAdapter

        manuDropdown = dialog.findViewById(R.id.loan_form_manufacture) as Spinner
        val manuItems = arrayOf("CHEVROLET", "FORD", "HONDA", "HYUNDAI", "MARUTI", "RENAULT", "KIA", "TATA","VOLKSWAGEN")
        val manuAdapter = ArrayAdapter(requireActivity(), R.layout.spinner_item, manuItems)
        manuDropdown.adapter = manuAdapter

        familyDropdown = dialog.findViewById(R.id.loan_form_select_family) as Spinner
        val familyItems = arrayOf("SELF","DAUGHTER", "SON", "SPOUSE")
        val familyAdapter = ArrayAdapter(requireActivity(), R.layout.spinner_item, familyItems)
        familyDropdown.adapter = familyAdapter

    }

//    fun radio_button_click(view: View){
//        // Get the clicked radio button instance
//        val radio: RadioButton = view.findViewById(policyRadioGroup.checkedRadioButtonId)
//    }
}