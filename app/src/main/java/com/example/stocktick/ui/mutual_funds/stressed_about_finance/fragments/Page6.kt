package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage6Binding
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.adapters.GoalsAdapter
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.adapters.GoalsAdapter2
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.domain_models.Goal
import java.lang.IndexOutOfBoundsException


private const val TAG = "Page6"
class Page6 : Fragment(R.layout.fragment_page6) {
    private lateinit var binding: FragmentPage6Binding
    private lateinit var recyclerView: RecyclerView
    private  lateinit var adapter: GoalsAdapter2
    private val goals = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage6Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(5)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        goals.add("Plan Capital for Entrepreneur")

        adapter = GoalsAdapter2.getNewInstance(goals,onRemoveClicked = {
            try {
                goals.removeAt(it)
            } catch (e: IndexOutOfBoundsException){
                Log.e(TAG, "onViewCreated: Index out of bound :${e.localizedMessage}", )
            }
            adapter.notifyItemRemoved(it)
        }, requireContext())

        recyclerView.adapter = adapter

        val etGoal = binding.etGoal
        binding.btAdd.setOnClickListener{
            if (etGoal.text?.isEmpty() == true){
                etGoal.requestFocus()
                etGoal.error = "Please enter a goal"
                return@setOnClickListener
            }
            goals.add(etGoal.text.toString() ?: "")
            adapter.notifyItemInserted(goals.size-1)
            etGoal.clearFocus()
            etGoal.text?.clear()
        }

        binding.btNext.setOnClickListener{
            Log.d(TAG, "onViewCreated: Goals :$goals")
            view?.findNavController()?.navigate(R.id.action_page6_to_page7)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}