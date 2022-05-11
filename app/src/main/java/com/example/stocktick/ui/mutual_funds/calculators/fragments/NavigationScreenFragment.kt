package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentNavigationScreenBinding


class NavigationScreenFragment : Fragment(R.layout.fragment_navigation_screen) {
    private lateinit var binding: FragmentNavigationScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNavigationScreenBinding.bind(view)
    }
}