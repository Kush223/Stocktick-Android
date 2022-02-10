package com.example.stocktick.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stocktick.databinding.FragmentCreateAccountBinding

class CreateAccountFragment: Fragment() {
    private lateinit var binding : FragmentCreateAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(layoutInflater)

        return binding.root
    }
}