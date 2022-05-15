package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage6dot2Binding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity

class Page6dot2 : Fragment(R.layout.fragment_page6dot2) {
    private lateinit var binding: FragmentPage6dot2Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage6dot2Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(7)
        binding.btNext.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot2_to_page6dot3)
        }
        binding.btSkip.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_page6dot2_to_page6dot3)
        }

    }
}