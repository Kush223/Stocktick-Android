package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage6dot4Binding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity


class Page6dot4 : Fragment(R.layout.fragment_page6dot4) {
    private lateinit var binding: FragmentPage6dot4Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage6dot4Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(9)
        binding.btNext.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot4_to_page7)
        }

    }
}