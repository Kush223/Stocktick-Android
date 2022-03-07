package com.example.stocktick.ui.loan

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentLoanBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant.LOAN
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

//TODO() -- remove hardcoded tokens use constants instead

class LoanFragment : Fragment() {
    private lateinit var loanViewModel: LoanViewModel

    private lateinit var binding: FragmentLoanBinding

    private var loanList: MutableList<LoanItem> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var loanAdapter: LoanAdapter

    private lateinit var tokenSharedPreference: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = LoanViewModelFactory(requireContext())
        loanViewModel = ViewModelProvider(
            this, viewModelFactory
        )[LoanViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = LOAN


        recyclerView = binding.loanList
        loanAdapter = LoanAdapter(loanList, requireActivity())

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.stackFromEnd = true

        recyclerView.layoutManager = linearLayoutManager

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(USER, Activity.MODE_PRIVATE)
        tokenSharedPreference = sharedPreferences.getString(TOKEN, "a").toString()
        recyclerView.adapter=loanAdapter


        loanList.clear()
        getLoans()
    }

    @DelicateCoroutinesApi
    private fun getLoans() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.getLoans(tokenSharedPreference, "M")
                setAdapter(response)

            } catch (error: Exception) {
                Toast.makeText(requireActivity(), "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun setAdapter(response: Response<List<LoanItem>>) {
        if (response.code() == 200) {
            val loanItemList: List<LoanItem>? = response.body()
            if (loanItemList != null) {
                for (loanItem in loanItemList) {
                    loanList.add(loanItem)
                }
            }
            loanList.add(LoanItem())
            recyclerView.adapter = loanAdapter
        } else {
            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.help_button) {
            // do something here
        }
        return super.onOptionsItemSelected(item)
    }
}