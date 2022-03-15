package com.example.stocktick.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentHomeBinding
import com.example.stocktick.ui.debit_credit.DebitCreditFragment
import soup.neumorphism.NeumorphCardView

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private var serviceList: MutableList<HomeItem> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var serviceAdapter: HomeAdapter
    private lateinit var debitCard : NeumorphCardView
    private lateinit var creditCard : NeumorphCardView
    private lateinit var mutualCard : NeumorphCardView
    private lateinit var insuranceCard : NeumorphCardView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        debitCard = binding.debitCard
        creditCard = binding.creditCard
        mutualCard = binding.mutualFundCard
        insuranceCard = binding.insurCard
        recyclerView = binding.homeServices
        serviceAdapter = HomeAdapter(serviceList,requireActivity())
        val linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager


//        recyclerView.adapter=serviceAdapter
        serviceList.add(HomeItem(R.drawable.financial_home,"Financial\n" +
                "Independence"))
        serviceList.add(HomeItem(R.drawable.liquidity_home,"Liquidity\n" +
                "Management"))
        serviceList.add(HomeItem(R.drawable.wealth_home,"Wealth\n" +
                "Protection"))
        recyclerView.adapter = serviceAdapter
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModelFactory = HomeViewModelFactory(requireContext())

        homeViewModel = ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        debitCard.setOnClickListener{
            val fragment = DebitCreditFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.container, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        creditCard.setOnClickListener{
            val fragment = DebitCreditFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.container, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        mutualCard.setOnClickListener{
            view?.findNavController()?.navigate(R.id.action_navigation_home_to_mutualFundRootFragment2)
            //Issue: if clicked, then we need to press back else it misbehaves
        }
        insuranceCard.setOnClickListener{
            //view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.navigation_insurance) }

//            val navHostFragment = childFragmentManager.findFragmentById(R.id.navigation_insurance)
//                    as NavHostFragment
//            val navCom = navHostFragment.navController
//
//            navCom.n()

            view?.findNavController()?.navigate(R.id.navigation_insurance)

//            if (!view?.findNavController()?.navigateUp()!!) {
//                // Call finish() on your Activity
//                activity?.finish()
//            }
//            val fragment = InsuranceFragment()
//            val fragmentManager = activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(R.id.container, fragment)
//            //fragmentTransaction?.addToBackStack(null)
//            fragmentTransaction?.commit()
        }
    }
}