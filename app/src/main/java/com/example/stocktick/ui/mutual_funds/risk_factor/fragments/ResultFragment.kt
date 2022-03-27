package com.example.stocktick.ui.mutual_funds.risk_factor.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentResultBinding
import com.example.stocktick.ui.customviews.PerformanceMeter
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorViewModel


class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var binding: FragmentResultBinding
    private lateinit var meter : PerformanceMeter
    
    private val viewModel: RiskFactorViewModel by activityViewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        meter = binding.performanceMEter
        
        viewModel.getRangeResult{isSuccessful, getRangeResultDM ->  
            if (isSuccessful){
                val category = getRangeResultDM?.category ?: return@getRangeResult
                meter.swapAngle(category)
                binding.resultDesc.text = getRangeResultDM.description
                binding.score.text = "You have scored ${getRangeResultDM.score} out of 100"


            }
            else {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            binding.btRetry.setOnClickListener{
                view?.findNavController()?.navigate(R.id.action_resultFragment_to_questionsFragment)
            }
        }
        

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}