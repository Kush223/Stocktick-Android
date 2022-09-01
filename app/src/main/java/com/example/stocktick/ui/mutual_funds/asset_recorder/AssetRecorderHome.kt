package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentAssetRecorderHomeBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "AssetRecorderHome"
class AssetRecorderHome : Fragment(R.layout.fragment_asset_recorder_home){

    private lateinit var binding: FragmentAssetRecorderHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE
    }
    
    private fun getLink(result : (isSuccessful : Boolean , url : String) -> Unit)  {
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        val tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderLink(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful && response.body()?.get("url") != null){
                    withContext(Dispatchers.Main){
                        result(true, response.body()?.get("url")!!)
                    }
                }
                else withContext(Dispatchers.Main){
                    Log.d(TAG, "downloadPdf: Error : ${response.body()}")
                    result(false, "")
                }
            } catch (e : Exception){
                Log.d(TAG, "downloadPdf: Error :${e.localizedMessage}")
                withContext(Dispatchers.Main){
                    result(false, "")
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        binding = FragmentAssetRecorderHomeBinding.bind(view)

        binding.profileCard.onButtonClickedListener{
            navController.navigate(R.id.action_assetRecorderHome_to_personalDetailsFragment)
        }
        binding.keyPeopleCard.onButtonClickedListener{
            navController.navigate(R.id.action_assetRecorderHome_to_keyPeopleFragment)
        }

        binding.fabDownload.setOnClickListener {
            getLink{isSuccessful, url ->  
                if (isSuccessful){
                    if (isSuccessful){
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    }
                    else Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
                }
                else Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }


        binding.fdCard.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.FIXED_DEPOSIT, name = "Fixed Deposits")
            navController.navigate(action)
        }
        binding.bankAccount.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.BANK_ACCOUNT, name = "Bank Account")
            navController.navigate(action)
        }
        binding.mutualFunds.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.MUTUAL_FUND, name = "Mutual Funds")
            navController.navigate(action)
        }
        binding.locker.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.LOCKER, name = "Locker")
            navController.navigate(action)
        }
        binding.indianEquity.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.INDIAN_EQUITY, name = "Indian Equity")
            navController.navigate(action)
        }
        binding.usEquity.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.US_EQUITY, name = "Us Equity")
            navController.navigate(action)
        }
        binding.crypto.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.CRYPTO, name = "Crypto")
            navController.navigate(action)
        }
        binding.bonds.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.BONDS, name = "Bonds")
            navController.navigate(action)
        }
        binding.offMarket.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.OFF_MARKET, name = "Off Market")
            navController.navigate(action)
        }
        binding.aif.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.AIF, name = "Aif")
            navController.navigate(action)
        }
        binding.pms.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.PMS, name = "Pms")
            navController.navigate(action)
        }
        binding.epf.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.EPF, name = "Epf")
            navController.navigate(action)
        }
        binding.ppf.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.PPF, name = "Ppf")
            navController.navigate(action)
        }
        binding.realState.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.REAL_ESTATE, name = "Real Estate")
            navController.navigate(action)
        }
        binding.nps.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.NPS_TIER_1, name = "Nps Tier 1")
            navController.navigate(action)
        }
        binding.other.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.OTHERS, name = "Other")
            navController.navigate(action)
        }
        binding.lifeInsurance.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.LIFE_INSURANCE, name = "Life Insurance")
            navController.navigate(action)
        }
        binding.healthInsurance.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.HEALTH_INSURANCE, name = "Health Insurance")
            navController.navigate(action)
        }
        binding.motorInsurance.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.MOTOR_INSURANCE, name = "Motor Insurance")
            navController.navigate(action)
        }
        binding.loan.onButtonClickedListener{
            val action = AssetRecorderHomeDirections.actionAssetRecorderHomeToFixedDeposits(category = AppData.LOAN, name = "Loan")
            navController.navigate(action)
        }




    }


}