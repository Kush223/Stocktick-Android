package com.example.stocktick.ui.insurance

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
import com.example.stocktick.databinding.FragmentInsuranceBinding
import com.example.stocktick.ui.loan.LoanAdapter
import com.example.stocktick.ui.loan.LoanItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InsuranceFragment : Fragment() {
    private lateinit var insuranceViewModel: InsuranceViewModel
    private lateinit var binding: FragmentInsuranceBinding
    private val insuranceList: MutableList<LoanItem> = ArrayList()
    private val carList: ArrayList<LoanItem> = ArrayList()
    private val bikeList: ArrayList<LoanItem> = ArrayList()
    private val homeList: ArrayList<LoanItem> = ArrayList()
    private val personalList: ArrayList<LoanItem> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var insuranceAdapter: LoanAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = InsuranceViewModelFactory(requireContext())
        insuranceViewModel = ViewModelProvider(
                this, viewModelFactory
        )[InsuranceViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = "Insurance"
        recyclerView = binding.insuranceList
        insuranceAdapter = LoanAdapter(insuranceList,carList,bikeList,personalList,homeList,requireActivity(),2)
        val linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.reverseLayout = false
        linearLayoutManager.stackFromEnd = false
        recyclerView.layoutManager = linearLayoutManager
//        val textView = binding.textDashboard
//        loanViewModel.mText.observe(viewLifecycleOwner, { s -> textView.text = s })
        insuranceList.add(LoanItem())
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("USER", Activity.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","a")
        val call : Call<List<LoanItem>> = RetrofitClientInstance.getClient.getInsurances(token!!)
        call.enqueue(object : Callback<List<LoanItem>> {
            override fun onResponse(call: Call<List<LoanItem>>, response: Response<List<LoanItem>>) {
                if(response.code()==200){
                    val insuranceItemList : List<LoanItem> = response.body()!!
                    for(insuranceItem in insuranceItemList){
                        if(insuranceItem.category.equals("Bike")){
                            bikeList.add(LoanItem(insuranceItem.link,insuranceItem.short_desc,insuranceItem.long_desc,insuranceItem.image_url,insuranceItem.category,insuranceItem.interest))
                        }
                        if(insuranceItem.category.equals("Car")){
                            carList.add(LoanItem(insuranceItem.link,insuranceItem.short_desc,insuranceItem.long_desc,insuranceItem.image_url,insuranceItem.category,insuranceItem.interest))
                        }
                        if(insuranceItem.category.equals("Home")){
                            homeList.add(LoanItem(insuranceItem.link,insuranceItem.short_desc,insuranceItem.long_desc,insuranceItem.image_url,insuranceItem.category,insuranceItem.interest))
                        }
                        if(insuranceItem.category.equals("Personal")){
                            personalList.add(LoanItem(insuranceItem.link,insuranceItem.short_desc,insuranceItem.long_desc,insuranceItem.image_url,insuranceItem.category,insuranceItem.interest))
                        }
                    }

                    recyclerView.adapter = insuranceAdapter
                }
                else{
                    Toast.makeText(requireActivity(),"Bad Request", Toast.LENGTH_SHORT).show()
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
        binding = FragmentInsuranceBinding.inflate(inflater, container, false)
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