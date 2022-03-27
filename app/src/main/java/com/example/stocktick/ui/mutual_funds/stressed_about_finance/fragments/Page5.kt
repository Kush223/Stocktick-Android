package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage5Binding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.adapters.GoalsAdapter
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.domain_models.Goal
import java.lang.IndexOutOfBoundsException


private const val TAG = "Page5"
class Page5 : Fragment(R.layout.fragment_page5) {
    private lateinit var binding: FragmentPage5Binding
    private lateinit var recyclerView: RecyclerView

    private  lateinit var adapter: GoalsAdapter

    private val goals = mutableListOf<Goal>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage5Binding.bind(view)
        recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        goals.add(Goal("Plan Capital for Entrepreneur", ))

        adapter = GoalsAdapter.getNewInstance(goals,onRemoveClicked = {
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
            goals.add(Goal(etGoal.text.toString() ?: "",1 ))
            adapter.notifyItemInserted(goals.size-1)
            etGoal.clearFocus()
            etGoal.text?.clear()
        }

        binding.btNext.setOnClickListener{
            Log.d(TAG, "onViewCreated: Goals :$goals")
            view?.findNavController()?.navigate(R.id.action_page5_to_page6)
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