package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.off_market.fragments

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
import com.example.stocktick.databinding.FragmentOffListBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.off_market.OffAdapter
import com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.off_market.OffViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG = "OffList"
class OffList : Fragment(R.layout.fragment_off_list) {

    private lateinit var binding: FragmentOffListBinding
    private lateinit var recyclerView : RecyclerView

    private val viewModel : OffViewModel by activityViewModels()

    private lateinit var adapter: OffAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOffListBinding.bind(view)
        recyclerView = binding.recyclerView

        adapter = OffAdapter(
            context = requireContext(),
            onClick = {
                val action =
                    OffListDirections.actionOffListToOffDetails(it)
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
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.modList.collect(){
                    if (it.isNotEmpty())
                    {
                        binding.tvEmpty.visibility = View.GONE
                        adapter.modList = it.toMutableList()
                    }
                }
            }
        }

        binding.fabEdit.setOnClickListener{
            val action = OffListDirections.actionOffListToOffDetailsEdit(-1)
            view.findNavController().navigate(action)
        }

    }
}