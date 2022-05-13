package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage5Binding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.adapters.GoalsAdapter
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.domain_models.Goal
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Data
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page5Dto


private const val TAG = "Page5"
class Page5 : Fragment(R.layout.fragment_page5) {
    private lateinit var binding: FragmentPage5Binding
    private lateinit var recyclerView: RecyclerView
    private val viewModel: MainViewModel by activityViewModels()

    private  lateinit var adapter: GoalsAdapter

    private val goals = mutableListOf<Goal>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage5Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(4)
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
            handleOnClick()
        }
    }

    private fun handleOnClick() {
        viewModel.postPage5(
            page5Dto = Page5Dto(
                goals.map {
                    Data(
                        goal = it.text,
                        priority = it.priority.toString()
                    )
                }
            )
        ){
            if (it){
                view?.findNavController()?.navigate(R.id.action_page5_to_page6)
            } else {
                view?.findNavController()?.navigate(R.id.action_page5_to_page6) //remove it later
                Toast.makeText(requireContext(), "Something went wrong.\nPlease check your internet connection", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}