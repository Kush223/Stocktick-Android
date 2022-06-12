package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentAssetRecorderHomeBinding

class AssetRecorderHome : Fragment(R.layout.fragment_asset_recorder_home){

    private lateinit var binding: FragmentAssetRecorderHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        binding = FragmentAssetRecorderHomeBinding.bind(view)


        binding.fdCard.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.FIXED_DEPOSIT)
            navController.navigate(action)
        }
        binding.aif.onButtonClickedListener{
            //navController.navigate(R.id.action_assetRecorderHome_to_aifList)
        }
        binding.pms.onButtonClickedListener{
           // navController.navigate(R.id.action_assetRecorderHome_to_pmsList)
        }
        binding.bankAccount.onButtonClickedListener{
           // navController.navigate(R.id.action_assetRecorderHome_to_baList)
        }
        binding.bonds.onButtonClickedListener{
            //navController.navigate(R.id.action_assetRecorderHome_to_bondsList)
        }
        binding.offMarket.onButtonClickedListener{
            //navController.navigate(R.id.action_assetRecorderHome_to_offList)
        }
        binding.crypto.onButtonClickedListener{
            //navController.navigate(R.id.action_assetRecorderHome_to_cryptoList)
        }
        binding.indianEquity.onButtonClickedListener{
            //navController.navigate(R.id.action_assetRecorderHome_to_equityList)
        }
        binding.usEquity.onButtonClickedListener{
            //navController.navigate(R.id.action_assetRecorderHome_to_usList)
        }


    }


}