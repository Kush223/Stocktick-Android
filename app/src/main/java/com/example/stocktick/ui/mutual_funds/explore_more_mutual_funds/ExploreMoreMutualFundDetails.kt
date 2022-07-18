package com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentExploreMoreMutualFundDetailsBinding
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.MfAdapter
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.ReturnType
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.domain_models.MfModel

class ExploreMoreMutualFundDetails : Fragment( R.layout.fragment_explore_more_mutual_fund_details) , View.OnClickListener{

    private lateinit var binding : FragmentExploreMoreMutualFundDetailsBinding

    private val viewModel : ExploreMoreViewModel by activityViewModels()

    private lateinit var adapter : MfAdapter

    private var selectedReturnInterval: ReturnType = ReturnType.THREE_YEAR

    private lateinit var tvReturnInterval : TextView

    private val args : ExploreMoreMutualFundDetailsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreMoreMutualFundDetailsBinding.bind(view)
        tvReturnInterval = binding.tvReturnInterval
        tvReturnInterval.setOnClickListener(this)
        binding.tvReturnHeader.setOnClickListener(this)


        val mfRecyclerView = binding.mfRecyclerView
        //recycler view
        adapter = MfAdapter(
            returnType = ReturnType.THREE_YEAR,
            context = requireContext()
        ){ url , fundId->

            val intent =
                Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
        mfRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mfRecyclerView.adapter = adapter

        viewModel.getMfList(args.id){
            adapter.mfList = it.map {
                MfModel(
                    mfName = it.fundName ?: "",
                    iconUrl = it.icon ?: "",
                    oneDayR = "${it.oneday}",
                    oneYearR = "${it.oneyear}",
                    threeYearR = "${it.threeyear}",
                    shortDescription = it.longDesc ?: "",
                    redirectUrl = it.redirectUrl ?: "www.fundzbazar.com",
                    lockStatus = it.lockStatus ?: "LOCKED",
                    fundId = it.id ?: 0

                )
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.tvReturnHeader -> {
                onReturnIntervalClick()
            }
            R.id.tvReturnInterval ->{
                onReturnIntervalClick()
            }
        }
    }

    private fun onReturnIntervalClick(){
        when (selectedReturnInterval){
            ReturnType.ONE_DAY->{
                selectedReturnInterval = ReturnType.ONE_YEAR
                tvReturnInterval.text = selectedReturnInterval.value

            }
            ReturnType.ONE_YEAR-> {
                selectedReturnInterval = ReturnType.THREE_YEAR
                tvReturnInterval.text = selectedReturnInterval.value
            }
            ReturnType.THREE_YEAR->{
                selectedReturnInterval = ReturnType.ONE_DAY
                tvReturnInterval.text = selectedReturnInterval.value
            }
        }
        adapter.returnType = selectedReturnInterval
        adapter.notifyDataSetChanged()
    }
}