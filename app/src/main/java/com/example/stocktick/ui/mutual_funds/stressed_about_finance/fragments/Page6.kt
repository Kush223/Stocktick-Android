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
import com.example.stocktick.databinding.FragmentPage6Binding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.adapters.GoalsAdapter2
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page6Dto


private const val TAG = "Page6"
class Page6 : Fragment(R.layout.fragment_page6) {
    private lateinit var binding: FragmentPage6Binding
    private lateinit var recyclerView: RecyclerView
    private  lateinit var adapter: GoalsAdapter2
    private val goals = mutableListOf<String>()
    private val viewModel: MainViewModel by activityViewModels()

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

        autofill()

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
            handleOnClick()
        }

    }

    private fun handleOnClick() {
        viewModel.postPage6(
            page6Dto = Page6Dto(
                goals = goals
            )
        ){
            if (it){
                view?.findNavController()?.navigate(R.id.action_page6_to_page6dot1)
            } else {
                view?.findNavController()?.navigate(R.id.action_page6_to_page6dot1) //remove it later
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


    private fun autofill(){
        viewModel.getPage6{ isSuccessful, page6 ->
            if (isSuccessful && page6!=null && !page6.goals.isNullOrEmpty()){
                goals.clear()
                for (goal in page6.goals){
                    goals.add(
                        goal
                    )
                }
                adapter.notifyDataSetChanged()
            }


        }
    }


}