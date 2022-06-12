package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentFdDetailsBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.AssetDataViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.adapters.DataDetailsViewAdapter

class DataDetails : Fragment(R.layout.fragment_fd_details) {

    private lateinit var binding: FragmentFdDetailsBinding
    private val viewModel: AssetDataViewModel by activityViewModels()

    private val args: DataDetailsArgs by navArgs()

    private lateinit var adapter: DataDetailsViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        binding = FragmentFdDetailsBinding.bind(view)

        val recyclerView: RecyclerView = binding.dataDetailsViewRecycler

        adapter = DataDetailsViewAdapter(
            viewModel.getDataDetailsList(args.category, args.position).filter {
                                                                              it.text.isNotEmpty()
            },
            requireContext()
        )

        recyclerView.adapter = adapter

        binding.fabEdit.setOnClickListener {
            val action =
                DataDetailsDirections.actionFdDetailsToFdDetailsEdit(args.position, args.category)
            navController.navigate(action)
        }

    }
}