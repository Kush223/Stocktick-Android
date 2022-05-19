package com.example.stocktick.ui.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentHomeBinding

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

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        viewPager = binding.photosViewPager
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
    }
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//
//////        binding = FragmentHomeBinding.inflate(inflater,container, false)
////        val viewModelFactory = HomeViewModelFactory(requireContext())
////
////        homeViewModel = ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]
//
//
//        return binding.root
//    }

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

}