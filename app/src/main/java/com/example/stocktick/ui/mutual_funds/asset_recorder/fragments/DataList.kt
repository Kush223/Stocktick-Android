package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentFixedDepositsBinding
import com.example.stocktick.ui.mutual_funds.asset_recorder.AppData
import com.example.stocktick.ui.mutual_funds.asset_recorder.AssetDataViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.CardListAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private const val TAG = "FixedDeposits"
class DataList : Fragment(R.layout.fragment_fixed_deposits) {

    private lateinit var binding: FragmentFixedDepositsBinding
    private lateinit var fdRecyclerView : RecyclerView

    private val viewModel : AssetDataViewModel by activityViewModels()

    private lateinit var adapter: CardListAdapter

    private val args : DataListArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE
        (activity as MainActivity).binding.topAppBar.title = args.name
    }

    override fun onResume() {
        super.onResume()
        viewModel.populateCardDataList(args.category)
    }

    override fun onPause() {
        viewModel.cardDataList.value.clear()
        super.onPause()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFixedDepositsBinding.bind(view)
        fdRecyclerView = binding.fdRecyclerView

        adapter = CardListAdapter(
            context = requireContext(),
            onClick = {
                val action =
                    DataListDirections.actionFixedDepositsToFdDetails(it, args.category)
                view.findNavController().navigate(action)
            },
            onDelete = {
                viewModel.deleteElement(position = it, category = args.category){ successful ->
                 if (successful){
                     adapter.cardDataList.removeAt(it)
                     adapter.notifyItemRemoved(it)
                     Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
                 } else {
                     Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                 }
                }
            },
            dataListCardModel = AppData.getDataListCardModels((args.category))
        )
        fdRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fdRecyclerView.adapter = adapter


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.cardDataList.collect(){
                    if (it.isNotEmpty())
                    {
                        binding.tvEmpty.visibility = View.GONE
                    }
                    adapter.cardDataList = it.toMutableList()
                }
            }
        }

        binding.fabEdit.setOnClickListener{
            val action = DataListDirections.actionFixedDepositsToFdDetailsEdit(-1, args.category)
            view.findNavController().navigate(action)
        }

    }
}