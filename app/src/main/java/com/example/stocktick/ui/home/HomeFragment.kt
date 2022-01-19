package com.example.stocktick.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import com.example.stocktick.databinding.FragmentHomeBinding
import com.example.stocktick.ui.notifications.DashboardViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModelFactory = DashboardViewModelFactory(requireContext())

        homeViewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)

        val textView = binding.textHome
        homeViewModel.mText.observe(viewLifecycleOwner, { s -> textView.text = s })
        return binding.root
    }
}