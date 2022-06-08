package com.example.stocktick.ui.mutual_funds

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.stocktick.R
import com.example.stocktick.auth.LoginSignupActivity
import com.example.stocktick.databinding.FragmentMutualFundRootBinding
import com.example.stocktick.ui.customviews.MutualFundCard
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.network_models.GetCategories
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.utility.UtilsService


private const val TAG = "MutualFundRootFragment"

class MutualFundRootFragment : Fragment(R.layout.fragment_mutual_fund_root), View.OnClickListener {
    private lateinit var binding: FragmentMutualFundRootBinding
    private lateinit var riskFactorCard: MutualFundCard

    private lateinit var utilsService: UtilsService

    private val viewModel: MainViewModel by activityViewModels()

    //categories
    //1. Titles
    private lateinit var tvCategory1: TextView
    private lateinit var tvCategory2: TextView
    private lateinit var tvCategory3: TextView
    private lateinit var tvCategory4: TextView
    private lateinit var tvCategory5: TextView
    private lateinit var tvCategory6: TextView

    //2. Icons
    private lateinit var iconCategory1: ImageView
    private lateinit var iconCategory2: ImageView
    private lateinit var iconCategory3: ImageView
    private lateinit var iconCategory4: ImageView
    private lateinit var iconCategory5: ImageView
    private lateinit var iconCategory6: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMutualFundRootBinding.bind(view)
        riskFactorCard = binding.riskFactor
        //Initializing the views
        tvCategory1 = binding.tvCategory1
        tvCategory2 = binding.tvCategory2
        tvCategory3 = binding.tvCategory3
        tvCategory4 = binding.tvCategory4
        tvCategory5 = binding.tvCategory5
        tvCategory6 = binding.tvCategory6

        iconCategory1 = binding.img1
        iconCategory2 = binding.img2
        iconCategory3 = binding.img3
        iconCategory4 = binding.img4
        iconCategory5 = binding.img5
        iconCategory6 = binding.img6

        //setting on click listeners
        binding.category1.setOnClickListener(this)
        binding.category2.setOnClickListener(this)
        binding.category3.setOnClickListener(this)
        binding.category4.setOnClickListener(this)
        binding.category5.setOnClickListener(this)
        binding.category6.setOnClickListener(this)


        val navController = view.findNavController()

        riskFactorCard.onButtonClickedListener {
            Log.d(TAG, "onViewCreated: clicked")
            val intent = Intent(requireActivity(), RiskFactorActivity::class.java)
            startActivity(intent)
        }
        binding.finance.onButtonClickedListener {
            val intent = Intent(
                requireActivity(),
                HostActivity::class.java
            )  //host activity of stressed about finance section
            startActivity(intent)
        }

        utilsService = UtilsService(requireContext())
        binding.calculators.onButtonClickedListener {
            val intent = Intent(
                requireActivity(),
                com.example.stocktick.ui.mutual_funds.calculators.HostActivity::class.java
            )
            startActivity(intent)
        }
        binding.cardButton.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.fundzbazar.com/"))
            startActivity(intent)
        }
        binding.exploreMore.onButtonClickedListener {
            val action =
                MutualFundRootFragmentDirections.actionNavigationMutualFundsToMfLists2(0)
            navController.navigate(action)
        }
        binding.assetRecorder.onButtonClickedListener{
            navController.navigate(R.id.action_navigation_mutual_funds_to_assetRecorderHome)
        }


        val categoryObserver = Observer<List<GetCategories>> { categoryList ->
            Log.d(TAG, "onViewCreated: Observer called $categoryList")
            if (!categoryList.isNullOrEmpty())
                try {
                    updateCategoryWidgets(categoryList)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "onViewCreated: Index out of bound")
                }
        }
        viewModel.mfCategories.observe(requireActivity(), categoryObserver)


    }

    @Throws(IndexOutOfBoundsException::class)
    private fun updateCategoryWidgets(categoryList: List<GetCategories>) {
        if (categoryList.size > 6) throw IndexOutOfBoundsException()
        populateCategoryCard(tvCategory1, iconCategory1, categoryList[0])
        populateCategoryCard(tvCategory2, iconCategory2, categoryList[1])
        populateCategoryCard(tvCategory3, iconCategory3, categoryList[2])
        populateCategoryCard(tvCategory4, iconCategory4, categoryList[3])
        populateCategoryCard(tvCategory5, iconCategory5, categoryList[4])
        populateCategoryCard(tvCategory6, iconCategory6, categoryList[5])

    }

    private fun populateCategoryCard(
        tvTitle: TextView,
        imgIcon: ImageView,
        category: GetCategories
    ) {
        if (category.lock_status == "LOCKED") {
            tvTitle.text = "Locked"
            imgIcon.setImageDrawable(AppCompatResources.getDrawable(requireContext(),R.drawable.ic_locked))
        } else {
            tvTitle.text = category.catg_name
            Glide.with(requireContext()).load(category.icon).into(imgIcon)
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

    //https://developer.android.com/guide/navigation/navigation-pass-data I used safe args for sending arguments to MfList fragment

    override fun onClick(v: View?) {
        if (v == null) return
        val categoryList = viewModel.mfCategories.value
        if (categoryList == null) {
            Toast.makeText(
                requireContext(),
                "Something went wrong.\nPlease check your internet connection",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        when (v.id) {
            R.id.category1 -> {
                navigate(
                    categoryList[0],
                    0
                )
            }
            R.id.category2 -> {
                navigate(
                    categoryList[1],
                    1
                )
            }
            R.id.category3 -> {
                navigate(
                    categoryList[2],
                    2
                )
            }
            R.id.category4 -> {
                navigate(
                    categoryList[3],
                    3
                )
            }
            R.id.category5 -> {
                navigate(
                    categoryList[4],
                    4
                )
            }
            R.id.category6 -> {
                navigate(
                    categoryList[5],
                    5
                )
            }

        }
    }

    private fun navigate(
        category: GetCategories,
        categoryIndex: Int,
        navController: NavController = findNavController()
    ) {
        if (category.lock_status != "LOCKED") {
            val action =
                MutualFundRootFragmentDirections.actionNavigationMutualFundsToMfLists2(
                    categoryIndex = categoryIndex
                )
            navController.navigate(action)
        } else Toast.makeText(
            requireContext(),
            "It is locked.\nPlease create account in fundzbazar.com to access",
            Toast.LENGTH_SHORT
        ).show()
    }

}