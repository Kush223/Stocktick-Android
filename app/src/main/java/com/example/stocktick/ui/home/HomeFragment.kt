package com.example.stocktick.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private var serviceList: MutableList<HomeItem> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var serviceAdapter: HomeAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModelFactory = HomeViewModelFactory(requireContext())

        homeViewModel = ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]

        recyclerView = binding.homeServices
        serviceAdapter = HomeAdapter(serviceList,requireActivity())
        val linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

//        recyclerView.adapter=serviceAdapter
        serviceList.add(HomeItem(R.drawable.financial_home,"Financial\n" +
                "Independence"))
        serviceList.add(HomeItem(R.drawable.liquidity_home,"Liquidity\n" +
                "Management"))
        serviceList.add(HomeItem(R.drawable.wealth_protection,"Wealth\n" +
                "Protection"))
        recyclerView.adapter = serviceAdapter
        return binding.root
    }
}