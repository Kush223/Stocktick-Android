package com.example.stocktick.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.databinding.FragmentDashboardBinding
import com.example.stocktick.ui.notifications.DashboardViewModelFactory

class DashboardFragment : Fragment() {
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val viewModelFactory = DashboardViewModelFactory(requireContext())
        dashboardViewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(DashboardViewModel::class.java)
        val textView = binding.textDashboard
        dashboardViewModel.mText.observe(viewLifecycleOwner, { s -> textView.text = s })
        return binding.root
    }
}