package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentFdDetailsEditBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.AssetDataViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.adapters.DataDetailsEditAdapter

class DataDetailsEdit : Fragment(R.layout.fragment_fd_details_edit) {
    private lateinit var binding: FragmentFdDetailsEditBinding
    private val viewModel: AssetDataViewModel by activityViewModels()
    private val args: DataDetailsEditArgs by navArgs()

    private lateinit var adapter: DataDetailsEditAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFdDetailsEditBinding.bind(view)

        binding.fabDone.setOnClickListener {
            updateList()
            view.findNavController().navigateUp()
        }

        val recyclerView  = binding.editDetailsRecyclerView
        adapter = DataDetailsEditAdapter(
            list = viewModel.getDataDetailsList(args.category, args.position).toMutableList(),
            context = requireContext()
        )

        recyclerView.adapter = adapter

    }

    private fun updateList() {
        if (args.position != -1) {
            viewModel.setElement(args.position, adapter.list, args.category) { successful ->
                if (successful) {
                    Toast.makeText(requireContext(), "Edited Successfully", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        else viewModel.addElement(category = args.category , list = adapter.list ){ successful ->
            if (successful){
                Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

}