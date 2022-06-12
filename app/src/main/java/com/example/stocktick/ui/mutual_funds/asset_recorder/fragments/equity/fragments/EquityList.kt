package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.equity.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentEquityListBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.equity.EquityAdapter
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.equity.EquityViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG = "EquityList"

class EquityList : Fragment(R.layout.fragment_equity_list) {

    private lateinit var binding: FragmentEquityListBinding
    private lateinit var recyclerView: RecyclerView

    private val viewModel: EquityViewModel by activityViewModels()

    private lateinit var adapter: EquityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEquityListBinding.bind(view)
        recyclerView = binding.recyclerView

        adapter = EquityAdapter(
            context = requireContext(),
            onClick = {
                val action =
                    EquityListDirections.actionEquityListToEquityDetials(it)
                view.findNavController().navigate(action)
            },
            onDelete = {
                adapter.modList.removeAt(it)
                adapter.notifyItemRemoved(it)

                viewModel.deleteElement(it)
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.modList.collect() {
                    if (it.isNotEmpty()) {
                        binding.tvEmpty.visibility = View.GONE
                        adapter.modList = it.toMutableList()
                    }
                }
            }
        }

        binding.fabEdit.setOnClickListener {
            val action = EquityListDirections.actionEquityListToEquityDetailsEdit(-1)
            view.findNavController().navigate(action)
        }

    }
}