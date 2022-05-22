package com.example.stocktick.ui.mutual_funds

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.ContentInfoCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.auth.LoginSignupActivity
import com.example.stocktick.databinding.FragmentMutualFundRootBinding
import com.example.stocktick.ui.customviews.MutualFundCard
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.utility.UtilsService
import soup.neumorphism.NeumorphCardView


private const val TAG = "MutualFundRootFragment"
class MutualFundRootFragment : Fragment(R.layout.fragment_mutual_fund_root) {
    private lateinit var binding: FragmentMutualFundRootBinding
    private lateinit var riskFactorCard : MutualFundCard

    private lateinit var utilsService: UtilsService

    //categories
    private lateinit var category1: NeumorphCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMutualFundRootBinding.bind(view)
        riskFactorCard = binding.riskFactor
        category1 = binding.category1

        val navController = view?.findNavController()

        riskFactorCard.onButtonClickedListener{
            Log.d(TAG, "onViewCreated: clicked")
            val intent = Intent(requireActivity(), RiskFactorActivity::class.java)
            startActivity(intent)
        }
        binding.finance.onButtonClickedListener{
            val intent = Intent(requireActivity(), HostActivity::class.java)  //host activity of stressed about finance section
            startActivity(intent)
        }

        utilsService = UtilsService(requireContext())
        binding.calculators.onButtonClickedListener{
            val intent = Intent(requireActivity(), com.example.stocktick.ui.mutual_funds.calculators.HostActivity::class.java)
            startActivity(intent)
        }
        binding.cardButton.setOnClickListener{
            val intent =
                Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.fundzbazar.com/"))
            startActivity(intent)
        }
        binding.exploreMore.onButtonClickedListener{
            navController.navigate(R.id.action_navigation_mutual_funds_to_mfLists2)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.logout, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.logout_button) {
            // do something here
            utilsService.logout()
            val intent = Intent(context, LoginSignupActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }
        return super.onOptionsItemSelected(item)

    }

}