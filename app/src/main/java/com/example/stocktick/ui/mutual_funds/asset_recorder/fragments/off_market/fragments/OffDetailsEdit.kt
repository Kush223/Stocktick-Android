package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.off_market.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentOffDetailsEditBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.off_market.OffViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.OffModel

class OffDetailsEdit : Fragment(R.layout.fragment_off_details_edit) {
    private lateinit var binding: FragmentOffDetailsEditBinding
    private val viewModel: OffViewModel by activityViewModels()
    private val args: OffDetailsEditArgs by navArgs()

    private var position : Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOffDetailsEditBinding.bind(view)
        val navController = view.findNavController()

        binding.fabEdit.setOnClickListener {
            updateList()
            view.findNavController().navigateUp()
        }
        position = args.position
        if (position == -1) return



        val details: OffModel? = try {
            viewModel.getElement(
                position = args.position
            )
        } catch (e: IndexOutOfBoundsException) {
            null
        }
        if (details == null) navController.navigateUp()


        //autogenerated
        if (details!!.broker_name.isNotEmpty()) {
            binding.brokerName.setText(details.broker_name)
        }
        if (details!!.account_number.isNotEmpty()) {
            binding.accountNumber.setText(details.account_number)
        }
        if (details!!.instrument_name.isNotEmpty()) {
            binding.instrumentName.setText(details.instrument_name)
        }
        if (details!!.qty.isNotEmpty()) {
            binding.qty.setText(details.qty)
        }
        if (details!!.purchase_price.isNotEmpty()) {
            binding.purchasePrice.setText(details.purchase_price)
        }
        if (details!!.purchase_value.isNotEmpty()) {
            binding.purchaseValue.setText(details.purchase_value)
        }
        if (details!!.market_price.isNotEmpty()) {
            binding.marketPrice.setText(details.market_price)
        }
        if (details!!.market_value.isNotEmpty()) {
            binding.marketValue.setText(details.market_value)
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
        val model = OffModel(
            broker_name = binding.brokerName.text.toString(),
            account_number = binding.accountNumber.text.toString(),
            instrument_name = binding.instrumentName.text.toString(),
            qty = binding.qty.text.toString(),
            purchase_price = binding.purchasePrice.text.toString(),
            purchase_value = binding.purchaseValue.text.toString(),
            market_price = binding.marketPrice.text.toString(),
            market_value = binding.marketValue.text.toString(),
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