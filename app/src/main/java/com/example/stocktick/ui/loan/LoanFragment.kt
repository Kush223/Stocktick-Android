package com.example.stocktick.ui.loan

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.auth.LoginSignupActivity
import com.example.stocktick.databinding.FragmentLoanBinding
import com.example.stocktick.models.response.HeadlineModel
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import com.example.stocktick.utility.UtilsService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

//TODO() -- remove hardcoded tokens use constants instead

class LoanFragment : Fragment() {
    private lateinit var loanViewModel: LoanViewModel

    private lateinit var binding: FragmentLoanBinding
    private lateinit var mProgressBar: ProgressBar

    private var loanList: MutableList<LoanItem> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var loanAdapter: LoanAdapter

    private lateinit var tokenSharedPreference: String
    private lateinit var utilsService: UtilsService

    private lateinit var loanHeadline: TextView

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.VISIBLE

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = LoanViewModelFactory(requireContext())
        loanViewModel = ViewModelProvider(
            this, viewModelFactory
        )[LoanViewModel::class.java]


        recyclerView = binding.loanList
        loanAdapter = LoanAdapter(loanList, requireActivity())

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.stackFromEnd = true

        recyclerView.layoutManager = linearLayoutManager
        utilsService = UtilsService(requireContext())
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(USER, Activity.MODE_PRIVATE)
        tokenSharedPreference = sharedPreferences.getString(TOKEN, "a").toString()
        recyclerView.adapter = loanAdapter

        loanHeadline=binding.loanTop

        mProgressBar = binding.progressLoan
        mProgressBar.visibility = View.VISIBLE
        loanList.clear()
        getLoans()
    }

    @DelicateCoroutinesApi
    private fun getLoans() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                showViewsAfterReload()
                val response =
                    RetrofitClientInstance.retrofitService.getLoans(tokenSharedPreference, "M")
                setAdapter(response)
                mProgressBar.visibility = View.INVISIBLE

            } catch (error: Exception) {
                Toast.makeText(requireActivity(), "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                    .show()
                showNetworkViews()
            }

        }
    }

    private fun handleHeadline() {
        fetchHeadline()
    }

    @DelicateCoroutinesApi
    private fun fetchHeadline() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                        RetrofitClientInstance.retrofitService.getHeadline("L", "M")
//                setAdapter(response, dialog)

                if (response.code() == 200) {
                    val headline: HeadlineModel? = response.body()
                    loanHeadline.text = headline?.headline
                }


            } catch (error: Exception) {
                Toast.makeText(context, "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                        .show()
//                Log.d("ERROR_INSURANCEFRAGMENT", error.toString())
            }

        }
    }

    private fun showViewsAfterReload() {
        binding.btTryAgain.visibility = View.INVISIBLE
        binding.loanNetworkErrorTv.visibility = View.INVISIBLE
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
            showNetworkViews()
        }
    }

    private fun showNetworkViews() {
        binding.loanNetworkErrorTv.visibility = View.VISIBLE
        binding.btTryAgain.visibility = View.VISIBLE
        binding.btTryAgain.setOnClickListener {
            getLoans()
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
        activity?.menuInflater?.inflate(R.menu.logout, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.logout_button) {
            // do something here
            // Logout
            utilsService.logout()
            val intent = Intent(context, LoginSignupActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}