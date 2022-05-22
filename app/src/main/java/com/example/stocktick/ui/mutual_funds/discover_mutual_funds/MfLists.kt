package com.example.stocktick.ui.mutual_funds.discover_mutual_funds

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anychart.scales.Linear
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentMfListsBinding
import com.example.stocktick.ui.mutual_funds.risk_factor.fragments.questions_fragment.MfModel

class MfLists : Fragment(R.layout.fragment_mf_lists) , AdapterView.OnItemSelectedListener{
    private lateinit var binding: FragmentMfListsBinding
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var categorySpinner: Spinner
    private lateinit var mfRecyclerView: RecyclerView
    private lateinit var mfAdapter: MfAdapter


    private val defCategories = listOf(
        "Wealth Conserve",
        "Wealth Protect"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMfListsBinding.bind(view)
        categorySpinner = binding.categorySpinner

        categorySpinner.onItemSelectedListener = this

        val adapter = ArrayAdapter(requireContext(),R.layout.menu_dropdown, defCategories)
        categorySpinner.adapter = adapter

        //recycler view
        mfAdapter = MfAdapter(
            listOf(
                MfModel(),
                MfModel(mfName = "ICICI Prudential private limited"),
                MfModel(mfName = "TATA ki bohot achchi mutual fund"),
                MfModel(mfName = "Reliance ki mutual fund")
            ),
            ReturnType.THREE_YEAR,
            requireContext()
        )

        mfRecyclerView = binding.mfRecyclerView
        mfRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mfRecyclerView.adapter = mfAdapter



    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}