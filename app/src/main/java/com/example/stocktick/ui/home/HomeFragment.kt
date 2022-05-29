package com.example.stocktick.ui.home

import android.content.Context
import android.content.res.Resources.getSystem
import android.graphics.Color
import android.os.Bundle
import android.provider.Telephony
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.asksira.loopingviewpager.LoopingViewPager
import com.asksira.loopingviewpager.indicator.CustomShapePagerIndicator
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentHomeBinding
import com.example.stocktick.utility.Constant
import com.example.stocktick.utility.SmsReader

class HomeFragment : Fragment(R.layout.fragment_home) {
//    https://material.io/components/bottom-navigation/android#using-bottom-navigation
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var serviceList: List<HomeItem>
    private lateinit var recyclerView : RecyclerView
    private lateinit var serviceAdapter: HomeAdapter
    private lateinit var debitCard : ConstraintLayout
    private lateinit var creditCard : ConstraintLayout
    private lateinit var mutualCard : ConstraintLayout
    private lateinit var insuranceCard : ConstraintLayout
    private lateinit var loanCard : ConstraintLayout

    private lateinit var viewPager: LoopingViewPager
    private lateinit var indicator: CustomShapePagerIndicator
    private lateinit var pagerAdapter: PagerAdapter

    val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        viewPager = binding.photosViewPager
        indicator = binding.indicator
        indicator.highlighterViewDelegate = {
            val highlighter = View(requireContext())
            highlighter.layoutParams = FrameLayout.LayoutParams(20.px, 4.px)
            highlighter.setBackgroundColor(Color.WHITE)
            highlighter
        }
        indicator.unselectedViewDelegate = {
            val unselected = View(requireContext())
            unselected.layoutParams = LinearLayout.LayoutParams(20.px, 4.px)
            unselected.setBackgroundColor(Color.WHITE)
            unselected.alpha = 0.4f
            unselected
        }
        pagerAdapter = PhotosAdapter(
            requireContext(),
            listOf(
                PhotoModel(
                    "https://thumbs.dreamstime.com/b/finance-business-concept-invesment-graph-coins-rows-investment-growth-table-blue-color-tone-111488763.jpg"
                ),
                PhotoModel(
                    "https://static.kent.ac.uk/nexus/ems/1218.jpg"
                ),
                PhotoModel(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgA_cCAt2he6cI_GasVfCuw0qPYYIOyM0Lyg&usqp=CAU"
                ),
            )
        )

        viewPager.adapter = pagerAdapter
        viewPager.onIndicatorProgress = { selectingPosition, progress ->
            indicator.onPageScrolled(selectingPosition, progress)
        }

        indicator.updateIndicatorCounts(viewPager.indicatorCount)


        creditCard = binding.creditSection
        debitCard = binding.debitSection
        mutualCard = binding.directMutualFund
        insuranceCard = binding.insurance
        loanCard = binding.loan

        recyclerView = binding.homeServices
        val linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        serviceList = listOf(
            HomeItem(R.drawable.financial_home,"Financial\n" +
                    "Independence"),
            HomeItem(R.drawable.liquidity_home,"Liquidity\n" +
                    "Management"),
            HomeItem(R.drawable.wealth_home,"Wealth\n" +
                    "Protection")
        )

        serviceAdapter = HomeAdapter(serviceList)
        recyclerView.adapter = serviceAdapter

        updateCreditDebit()
    }

    private fun updateCreditDebit() {
        val sharedPreferences = requireContext().getSharedPreferences(Constant.SMS_SHARED_PREFS, Context.MODE_PRIVATE)
        val credit = sharedPreferences.getFloat(Constant.LAST_MONTH_CREDIT,-1f )
        if (credit == -1f){
            val smsReader = SmsReader.getInstance(requireContext())
            smsReader.readMonthSms(SmsReader.LAST_MONTH){ data ->
                binding.creditAmount.text = "${data.credit}INR"
                binding.debitAmount.text = "${data.debit}INR"
            }
        }
        else {
            val debit = sharedPreferences.getFloat(Constant.LAST_MONTH_DEBIT, 000f)
            binding.creditAmount.text = "${credit}INR"
            binding.debitAmount.text = "${debit}INR"
        }
    }

    override fun onStart() {
        super.onStart()

        val navView = (activity as MainActivity).navView

        val navController = view?.findNavController() ?: return
        debitCard.setOnClickListener{
            navController.navigate(R.id.action_navigation_home_to_debitCreditFragment)
        }
        creditCard.setOnClickListener{
            navController.navigate(R.id.action_navigation_home_to_debitCreditFragment)
        }
        mutualCard.setOnClickListener{
            navView.selectedItemId = R.id.navigation_mutual_funds
        }
        insuranceCard.setOnClickListener{
            navView.selectedItemId = R.id.navigation_insurance
        }
        loanCard.setOnClickListener{
            navView.selectedItemId = R.id.navigation_loan
        }

    }

    override fun onResume() {
        super.onResume()
        viewPager.resumeAutoScroll()

        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.VISIBLE

    }


    override fun onPause() {
        viewPager.pauseAutoScroll()
        super.onPause()
    }

}