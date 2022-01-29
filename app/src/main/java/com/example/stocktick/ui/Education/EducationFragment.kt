package com.example.stocktick.ui.Education

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.Network.RetrofitClientInstance
import com.example.stocktick.R
import com.example.stocktick.ui.loan.LoanAdapter
import com.example.stocktick.ui.loan.LoanItem
import com.example.stocktick.ui.loan.LoanViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EducationFragment : Fragment() {
//    private lateinit var eduViewModel: LoanViewModel
//    private lateinit var binding: FragmentEducationBinding
//    private val eduList: ArrayList<LoanItem> = ArrayList()
//    private lateinit var recyclerView : RecyclerView
//    private lateinit var eduAdapter: LoanAdapter
//    override fun onCreateView(
//            inflater: LayoutInflater,
//            container: ViewGroup?, savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentEducationBinding.inflate(inflater, container, false)
//        val viewModelFactory = LoanViewModelFactory(requireContext())
//        eduViewModel = ViewModelProvider(
//                this, viewModelFactory
//        )[LoanViewModel::class.java]
//        (activity as AppCompatActivity).supportActionBar?.title = "Loan"
//        recyclerView = binding.
//        loanAdapter = LoanAdapter(loanList,requireActivity())
//        val linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
//        recyclerView.layoutManager = linearLayoutManager
////        val textView = binding.textDashboard
////        loanViewModel.mText.observe(viewLifecycleOwner, { s -> textView.text = s })
//        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("USER", Activity.MODE_PRIVATE)
//        val token = sharedPreferences.getString("token","a")
//        val call : Call<List<LoanItem>> = RetrofitClientInstance.getClient.getLoans(token!!)
//        call.enqueue(object : Callback<List<LoanItem>> {
//            override fun onResponse(call: Call<List<LoanItem>>, response: Response<List<LoanItem>>) {
//                if(response.code()==200){
//                    val loanItemList : List<LoanItem> = response.body()!!
//                    for(loanItem in loanItemList){
//                        loanList.add(LoanItem(loanItem.link,loanItem.short_desc,loanItem.long_desc,loanItem.image_url,loanItem.category,loanItem.interest))
//                    }
//                    recyclerView.adapter = loanAdapter
//                }
//                else{
//                    Toast.makeText(requireActivity(),"Bad Request", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<LoanItem>>, t: Throwable) {
//                Toast.makeText(requireActivity(),"Request failed", Toast.LENGTH_SHORT).show()
//            }
//
//        })
//
//        return binding.root
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        activity?.menuInflater?.inflate(R.menu.help, menu)
//        return super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        if (id == R.id.help_button) {
//            // do something here
//        }
//        return super.onOptionsItemSelected(item)
//    }
}