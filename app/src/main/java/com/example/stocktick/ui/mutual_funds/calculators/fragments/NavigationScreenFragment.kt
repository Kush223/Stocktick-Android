package com.example.stocktick.ui.mutual_funds.calculators.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentNavigationScreenBinding


private const val TAG = "NavigationScreenFragmen"
class NavigationScreenFragment : Fragment(R.layout.fragment_navigation_screen) {
    private lateinit var binding: FragmentNavigationScreenBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNavigationScreenBinding.bind(view)


        var navController = view?.findNavController()

        binding.retirementCalculatorCons.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_retirementCalculatorFragment)
        }
        binding.emergencyCalculatorCons.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_emergencyCalculatorFragment)
        }
        binding.sipCalculator.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_SIPCalculatorFragment)
        }
        binding.goalCalculator.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_goalCalculatorFragment)
        }
        binding.childMarriageCalculator.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_childMarriageCalculatorFragment)
        }
        binding.childEducationCalculator.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_childEducationCalculatorFragment)
        }
        binding.emiCalculator.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_emiCalculatorFragment)
        }
        binding.sipTopUpCalculator.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_sipTopUpFragment)
        }
        binding.unknownCalculator.setOnClickListener{
            navController?.navigate(R.id.action_navigationScreenFragment_to_lumpsumCalculator)
        }

    }
}