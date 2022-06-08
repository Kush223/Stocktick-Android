package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentAssetRecorderHomeBinding

class AssetRecorderHome : Fragment(R.layout.fragment_asset_recorder_home){

    private lateinit var binding: FragmentAssetRecorderHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        binding = FragmentAssetRecorderHomeBinding.bind(view)


        binding.fdCard.onButtonClickedListener{
            navController.navigate(R.id.action_assetRecorderHome_to_fixedDeposits)
        }

    }


}