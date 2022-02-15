package com.example.stocktick.ui.insurance

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentInsuranceBinding


class InsuranceFragment : Fragment() {
    private lateinit var insuranceViewModel: InsuranceViewModel
    private lateinit var binding: FragmentInsuranceBinding
    private lateinit var bikeCard: CardView
    private lateinit var carCard: CardView
    private lateinit var homeCard: CardView
    private lateinit var policyClaimYes : RadioButton
    private lateinit var pucYes : RadioButton
    private lateinit var ownYes : RadioButton
    private lateinit var pucRadioGroup : RadioGroup
    private lateinit var policyRadioGroup : RadioGroup
    private lateinit var ownRadioGroup : RadioGroup
    private lateinit var manuYear : EditText
    private lateinit var name : EditText
    private lateinit var phone : EditText
    private lateinit var email : EditText
    private lateinit var vehicleNum : EditText
    private lateinit var variant : EditText
    private lateinit var insurer : EditText
    private lateinit var pucExp : EditText
    private lateinit var policyExp : EditText
    private lateinit var policyType : EditText


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = InsuranceViewModelFactory(requireContext())
        insuranceViewModel = ViewModelProvider(
                this, viewModelFactory
        )[InsuranceViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = "Insurance"
        bikeCard = binding.bikeCard
        carCard = binding.carCard
        homeCard = binding.homeCard



        bikeCard.setOnClickListener{
            val dialog = Dialog(requireActivity())
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.loan_dialog)

            val fuelDropdown = dialog.findViewById(R.id.loan_form_fuel_type) as Spinner
            val fuelItems = arrayOf("CNG", "LPG", "PETROL")
            val fuelAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, fuelItems)
            fuelDropdown.adapter = fuelAdapter

            val manuDropdown = dialog.findViewById(R.id.loan_form_manufacture) as Spinner
            val manuItems = arrayOf("CHEVROLET", "FORD", "HONDA", "HYUNDAI", "MARUTI", "RENAULT", "KIA", "TATA","VOLKSWAGEN")
            val manuAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, manuItems)
            manuDropdown.adapter = manuAdapter

            policyRadioGroup = dialog.findViewById(R.id.policy_claim_radio)
            pucRadioGroup = dialog.findViewById(R.id.puc_radio)
            pucYes = dialog.findViewById(R.id.loan_form_puc_yes)
            policyClaimYes = dialog.findViewById(R.id.loan_form_policy_claim_yes)
            ownRadioGroup = dialog.findViewById(R.id.ownership_radio)
            ownYes = dialog.findViewById(R.id.loan_form_ownership_transfer_yes)
            manuYear=dialog.findViewById(R.id.loan_form_manufacture_year)
            name=dialog.findViewById(R.id.loan_form_name)
            email=dialog.findViewById(R.id.loan_form_email)
            phone=dialog.findViewById(R.id.loan_form_phone)
            variant=dialog.findViewById(R.id.loan_form_variant)
            vehicleNum=dialog.findViewById(R.id.loan_form_vehicle_no)
            insurer=dialog.findViewById(R.id.loan_form_insurer)
            pucExp=dialog.findViewById(R.id.loan_form_puc_exp)
            policyExp=dialog.findViewById(R.id.loan_form_policy_exp)
            policyType=dialog.findViewById(R.id.loan_form_policy_type)

//            policyRadioGroup.setOnCheckedChangeListener(
//                    RadioGroup.OnCheckedChangeListener { group, checkedId ->
//                        val radio: RadioButton = dialog.findViewById(checkedId)
//
//                    }
//            )
            val submitBtn = dialog.findViewById(R.id.loan_form_submit) as CardView
            submitBtn.setOnClickListener {
                val polId: Int = policyRadioGroup.checkedRadioButtonId
                val pucId: Int = pucRadioGroup.checkedRadioButtonId
                val ownId: Int = ownRadioGroup.checkedRadioButtonId
                if (polId == -1) {
                    policyClaimYes.error = "Please select yes or no"
                }
                if(pucId == -1){
                    pucYes.error = "Please select yes or no"
                }
                if(ownId == -1){
                    ownYes.error = "Please select yes or no"
                }
            }

            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.show()
            val metrics: DisplayMetrics = requireActivity().resources.displayMetrics
            val width = metrics.widthPixels
            val height = metrics.heightPixels
            dialog.window?.setLayout((4 * width) / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        carCard.setOnClickListener{

        }
        homeCard.setOnClickListener{

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

//    fun radio_button_click(view: View){
//        // Get the clicked radio button instance
//        val radio: RadioButton = view.findViewById(policyRadioGroup.checkedRadioButtonId)
//    }
}