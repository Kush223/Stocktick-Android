package com.example.stocktick.ui.mutual_funds

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentMutualFundRootBinding
import com.example.stocktick.ui.customviews.MutualFundCard
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorActivity
import com.example.stocktick.utility.Constant.EDUCATION
import com.example.stocktick.utility.Constant.MUTUAL_FUND
import kotlinx.coroutines.runInterruptible


private const val TAG = "MutualFundRootFragment"
class MutualFundRootFragment : Fragment(R.layout.fragment_mutual_fund_root) {
    private lateinit var mutualFundRootBinding: FragmentMutualFundRootBinding
    private lateinit var riskFactorCard : MutualFundCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mutualFundRootBinding = FragmentMutualFundRootBinding.bind(view)
        riskFactorCard = mutualFundRootBinding.riskFactor
        riskFactorCard.onButtonClickedListener{
            Log.d(TAG, "onViewCreated: clicked")
            val intent = Intent(requireActivity(), RiskFactorActivity::class.java)
            startActivity(intent)
        }

        (activity as AppCompatActivity).supportActionBar?.title = MUTUAL_FUND

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.help_button) {
            // do something here
        }
        return super.onOptionsItemSelected(item)

    }

}