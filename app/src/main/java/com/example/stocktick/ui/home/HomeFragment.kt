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
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentHomeBinding
import com.example.stocktick.ui.debit_credit.DebitCreditFragment
import soup.neumorphism.NeumorphCardView

class HomeFragment : Fragment(R.layout.fragment_home) {
//    https://material.io/components/bottom-navigation/android#using-bottom-navigation
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private var serviceList: MutableList<HomeItem> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var serviceAdapter: HomeAdapter
    private lateinit var debitCard : NeumorphCardView
    private lateinit var creditCard : NeumorphCardView
    private lateinit var mutualCard : NeumorphCardView
    private lateinit var insuranceCard : NeumorphCardView

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
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Ftime.com%2F5660278%2Fsmartphone-camera-picture-tips%2F&psig=AOvVaw0DrztCllzeQod2ieZrAM8n&ust=1652876887076000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJCZ27DE5vcCFQAAAAAdAAAAABAD"
                ),
                PhotoModel(
                    "https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.istockphoto.com%2Fphotos%2Fpura-ulun-danu-bratan-temple-in-bali-picture-id675172642%3Fk%3D20%26m%3D675172642%26s%3D612x612%26w%3D0%26h%3D-pka3tFBEpKRZGVXrKbhFcRK5IKB-xn-5MPBWQj24q4%3D&imgrefurl=https%3A%2F%2Fwww.istockphoto.com%2Fphotos%2Fbali&tbnid=O7faNa3V4xeuQM&vet=12ahUKEwjTmPuuxOb3AhVwmtgFHUiqAj8QMyhLegUIARCTAQ..i&docid=bh11IBVvQUxOHM&w=612&h=407&q=photos&ved=2ahUKEwjTmPuuxOb3AhVwmtgFHUiqAj8QMyhLegUIARCTAQ"
                ),
                PhotoModel(
                    "https://www.google.com/url?sa=i&url=https%3A%2F%2Ftime.com%2F5660278%2Fsmartphone-camera-picture-tips%2F&psig=AOvVaw0DrztCllzeQod2ieZrAM8n&ust=1652876887076000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJCZ27DE5vcCFQAAAAAdAAAAABAD"
                ),
                PhotoModel(
                    "https://www.google.com/imgres?imgurl=https%3A%2F%2Flive-production.wcms.abc-cdn.net.au%2F50749be1153e1907d7e1208fc96432f8%3Fimpolicy%3Dwcms_crop_resize%26cropH%3D844%26cropW%3D1500%26xPos%3D0%26yPos%3D0%26width%3D862%26height%3D485&imgrefurl=https%3A%2F%2Fwww.abc.net.au%2Fnews%2Fabcmyphoto%2F2021-01-22%2Fyour-best-photos-from-the-past-week-20210122%2F13081116&tbnid=w_goD_KqH98HgM&vet=10CAUQMyhqahcKEwiQmduwxOb3AhUAAAAAHQAAAAAQCA..i&docid=mxWng-BJE67OcM&w=862&h=485&q=photos&ved=0CAUQMyhqahcKEwiQmduwxOb3AhUAAAAAHQAAAAAQCA"
                ),
            )
        )

        viewPager.adapter = pagerAdapter

     //   debitCard = binding.debitCard
      //  creditCard = binding.creditCard
      //  mutualCard = binding.mutualFundCard
      //  insuranceCard = binding.insurCard
     //   recyclerView = binding.homeServices
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

        binding = FragmentHomeBinding.inflate(inflater,container, false)
        val viewModelFactory = HomeViewModelFactory(requireContext())

        homeViewModel = ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]


        return binding.root
    }

    override fun onStart() {
        super.onStart()
//        debitCard.setOnClickListener{
//            val fragment = DebitCreditFragment()
//            val fragmentManager = activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(R.id.container, fragment)
//            fragmentTransaction?.addToBackStack(null)
//            fragmentTransaction?.commit()
//        }
//        creditCard.setOnClickListener{
//            val fragment = DebitCreditFragment()
//            val fragmentManager = activity?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(R.id.container, fragment)
//            fragmentTransaction?.addToBackStack(null)
//            fragmentTransaction?.commit()
//        }
//        mutualCard.setOnClickListener{
//            view?.findNavController()?.navigate(R.id.action_navigation_home_to_mutualFundRootFragment2)
//            //Issue: if clicked, then we need to press back else it misbehaves
//        }
//        insuranceCard.setOnClickListener{
//            //view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.navigation_insurance) }
//
////            val navHostFragment = childFragmentManager.findFragmentById(R.id.navigation_insurance)
////                    as NavHostFragment
////            val navCom = navHostFragment.navController
////
////            navCom.n()
//
//            view?.findNavController()?.navigate(R.id.navigation_insurance)
//
////            if (!view?.findNavController()?.navigateUp()!!) {
////                // Call finish() on your Activity
////                activity?.finish()
////            }
////            val fragment = InsuranceFragment()
////            val fragmentManager = activity?.supportFragmentManager
////            val fragmentTransaction = fragmentManager?.beginTransaction()
////            fragmentTransaction?.replace(R.id.container, fragment)
////            //fragmentTransaction?.addToBackStack(null)
////            fragmentTransaction?.commit()
//        }
    }
}