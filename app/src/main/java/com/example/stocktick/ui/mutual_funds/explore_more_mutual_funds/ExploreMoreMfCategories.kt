package com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentExploreMoreMfCategoriesBinding
import com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds.adapter.CategoryAdapter
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "ExploreMoreMfCategories"
class ExploreMoreMfCategories : Fragment(R.layout.fragment_explore_more_mf_categories) {

   private lateinit var binding : FragmentExploreMoreMfCategoriesBinding

   private val viewModel : ExploreMoreViewModel by activityViewModels()

    private lateinit var adapter : CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreMoreMfCategoriesBinding.bind(view)
        adapter = CategoryAdapter(requireContext()){
            val action = ExploreMoreMfCategoriesDirections.actionExploreMoreMfCategoriesToExploreMoreMutualFundDetails(it)
            view.findNavController().navigate(action)
        }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(),3)

        recyclerView.adapter = adapter

        lifecycleScope.launch{
            viewModel.categories.collect {
                adapter.list = it
                adapter.notifyDataSetChanged()
            }
        }

    }
}