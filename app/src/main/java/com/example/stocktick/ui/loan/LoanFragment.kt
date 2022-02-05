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
import com.example.stocktick.Network.RetrofitClientInstance
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentLoanBinding
import com.example.stocktick.ui.loan.LoanViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoanFragment : Fragment() {
    private lateinit var loanViewModel: LoanViewModel
    private lateinit var binding: FragmentLoanBinding
    private val loanList: MutableList<LoanItem> = ArrayList()
    private val carList: ArrayList<LoanItem> = ArrayList()
    private val bikeList: ArrayList<LoanItem> = ArrayList()
    private val homeList: ArrayList<LoanItem> = ArrayList()
    private val personalList: ArrayList<LoanItem> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var loanAdapter: LoanAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = LoanViewModelFactory(requireContext())
        loanViewModel = ViewModelProvider(
                this, viewModelFactory
        )[LoanViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = "Loan"
        recyclerView = binding.loanList
        loanAdapter = LoanAdapter(loanList,carList,bikeList,personalList,homeList,requireActivity(),1)
        val linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.stackFromEnd = false

        //recyclerView.isNestedScrollingEnabled = false;
        //object : LinearLayoutManager(activity){ override fun canScrollVertically(): Boolean { return false } }
        recyclerView.layoutManager = linearLayoutManager
//        val textView = binding.textDashboard
//        loanViewModel.mText.observe(viewLifecycleOwner, { s -> textView.text = s })

        //recyclerView.adapter=loanAdapter
        loanList.add(LoanItem())
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("USER", Activity.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","a")
        val call : Call<List<LoanItem>> = RetrofitClientInstance.getClient.getLoans(token!!)
        call.enqueue(object : Callback<List<LoanItem>> {
            override fun onResponse(call: Call<List<LoanItem>>, response: Response<List<LoanItem>>) {
                if(response.code()==200){
                    val loanItemList : List<LoanItem> = response.body()!!
                    for(loanItem in loanItemList){
                        if(loanItem.category.equals("Bike")){
                            bikeList.add(LoanItem(loanItem.link,loanItem.short_desc,loanItem.long_desc,loanItem.image_url,loanItem.category,loanItem.interest))
                        }
                        if(loanItem.category.equals("Car")){
                            carList.add(LoanItem(loanItem.link,loanItem.short_desc,loanItem.long_desc,loanItem.image_url,loanItem.category,loanItem.interest))
                        }
                        if(loanItem.category.equals("Home")){
                            homeList.add(LoanItem(loanItem.link,loanItem.short_desc,loanItem.long_desc,loanItem.image_url,loanItem.category,loanItem.interest))
                        }
                        if(loanItem.category.equals("Personal")){
                            personalList.add(LoanItem(loanItem.link,loanItem.short_desc,loanItem.long_desc,loanItem.image_url,loanItem.category,loanItem.interest))
                        }
                        //loanList.add(LoanItem(loanItem.link,loanItem.short_desc,loanItem.long_desc,loanItem.image_url,loanItem.category,loanItem.interest))
                    }
                    //Log.d("ds"," " +personalList.size + " "+ homeList.size+ " "+ carList.size+ " "+ bikeList.size)

                    recyclerView.adapter = loanAdapter
                }
                else{
                    Toast.makeText(requireActivity(),"Bad Request",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<LoanItem>>, t: Throwable) {
                Toast.makeText(requireActivity(),"Request failed", Toast.LENGTH_SHORT).show()
            }

        })



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