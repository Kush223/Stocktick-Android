package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.fd.fragments

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
import com.example.stocktick.databinding.FragmentFixedDepositsBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.fd.FdAdapter
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.fd.FdViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG = "FixedDeposits"
class FixedDeposits : Fragment(R.layout.fragment_fixed_deposits) {

    private lateinit var binding: FragmentFixedDepositsBinding
    private lateinit var fdRecyclerView : RecyclerView

    private val viewModel : FdViewModel by activityViewModels()

    private lateinit var adapter: FdAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFixedDepositsBinding.bind(view)
        fdRecyclerView = binding.fdRecyclerView

        adapter = FdAdapter(
            context = requireContext(),
            onClick = {
                val action =
                    FixedDepositsDirections.actionFixedDepositsToFdDetails(it)
                view.findNavController().navigate(action)
            },
            onDelete = {
                adapter.fdList.removeAt(it)
                adapter.notifyItemRemoved(it)

                viewModel.deleteElement(it)
            }
        )
        fdRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fdRecyclerView.adapter = adapter


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.fdList.collect(){
                    if (it.isNotEmpty())
                    {
                        binding.tvEmpty.visibility = View.GONE
                    }
                    adapter.fdList = it.toMutableList()
                }
            }
        }

        binding.fabEdit.setOnClickListener{
            val action = FixedDepositsDirections.actionFixedDepositsToFdDetailsEdit(-1)
            view.findNavController().navigate(action)
        }

    }
}