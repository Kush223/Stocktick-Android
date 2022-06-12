package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.aif.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentAifDetailsEditBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.aif.AifViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.AifModel

class AifDetailsEdit : Fragment(R.layout.fragment_aif_details_edit) {
    private lateinit var binding: FragmentAifDetailsEditBinding
    private val viewModel: AifViewModel by activityViewModels()
    private val args: AifDetailsEditArgs by navArgs()

    private var position : Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAifDetailsEditBinding.bind(view)
        val navController = view.findNavController()

        binding.fabEdit.setOnClickListener {
            updateList()
            view.findNavController().navigateUp()
        }
        position = args.position
        if (position == -1) return



        val details: AifModel? = try {
            viewModel.getElement(
                position = args.position
            )
        } catch (e: IndexOutOfBoundsException) {
            null
        }
        if (details == null) navController.navigateUp()


        //autogenerated
        if (details!!.amc_name.isNotEmpty()) {
            binding.amcName.setText(details.amc_name)
        }
        if (details!!.pms_aif_name.isNotEmpty()) {
            binding.pmsAifName.setText(details.pms_aif_name)
        }
        if (details!!.invested_value.isNotEmpty()) {
            binding.investedValue.setText(details.invested_value)
        }
        if (details!!.current_amount.isNotEmpty()) {
            binding.currentAmount.setText(details.current_amount)
        }
        if (details!!.account_number.isNotEmpty()) {
            binding.accountNumber.setText(details.account_number)
        }
        if (details!!.invested_date.isNotEmpty()) {
            binding.investedDate.setText(details.invested_date)
        }
        if (details!!.return_absolute.isNotEmpty()) {
            binding.returnAbsolute.setText(details.return_absolute)
        }
        if (details!!.return_cagr.isNotEmpty()) {
            binding.returnCagr.setText(details.return_cagr)
        }
        if (details!!.nominee_name.isNotEmpty()) {
            binding.nomineeName.setText(details.nominee_name)
        }
        if (details!!.relationship.isNotEmpty()) {
            binding.relationship.setText(details.relationship)
        }
        if (details!!.allocation_percent.isNotEmpty()) {
            binding.allocationPercent.setText(details.allocation_percent)
        }
        if (details!!.nominee_name2_.isNotEmpty()) {
            binding.nomineeName2.setText(details.nominee_name2_)
        }
        if (details!!.relationship2.isNotEmpty()) {
            binding.Relationship2.setText(details.relationship2)
        }
        if (details!!.allocation_percent2.isNotEmpty()) {
            binding.allocationPercent2.setText(details.allocation_percent2)
        }

    }

    private fun updateList() {
        val model = AifModel(
            amc_name = binding.amcName.text.toString(),
            pms_aif_name = binding.pmsAifName.text.toString(),
            invested_value = binding.investedValue.text.toString(),
            current_amount = binding.currentAmount.text.toString(),
            account_number = binding.accountNumber.text.toString(),
            invested_date = binding.investedDate.text.toString(),
            return_absolute = binding.returnAbsolute.text.toString(),
            return_cagr = binding.returnCagr.text.toString(),
            nominee_name = binding.nomineeName.text.toString(),
            relationship = binding.relationship.text.toString(),
            allocation_percent = binding.allocationPercent.text.toString(),
            nominee_name2_ = binding.nomineeName2.text.toString(),
            relationship2 = binding.Relationship2.text.toString(),
            allocation_percent2 = binding.allocationPercent2.text.toString(),
        )
        if (position != -1)
            viewModel.setElement(args.position, model)
        else viewModel.addElement(model)
    }

}