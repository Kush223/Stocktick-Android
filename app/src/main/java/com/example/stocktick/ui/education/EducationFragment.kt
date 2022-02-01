package com.example.stocktick.ui.education

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
import com.example.stocktick.databinding.FragmentEducationBinding
import com.example.stocktick.ui.loan.LoanAdapter
import com.example.stocktick.ui.loan.LoanItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EducationFragment : Fragment() {
    private lateinit var eduViewModel: EducationViewModel
    private lateinit var binding: FragmentEducationBinding
    private val eduList: ArrayList<LoanItem> = ArrayList()
    private lateinit var recyclerView : RecyclerView
    private lateinit var eduAdapter: LoanAdapter
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentEducationBinding.inflate(inflater, container, false)
        val viewModelFactory = EducationViewModelFactory(requireContext())
        eduViewModel = ViewModelProvider(
                this, viewModelFactory
        )[EducationViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = "Loan"
        recyclerView = binding.eduList
        eduAdapter = LoanAdapter(eduList,requireActivity())
        val linearLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
        recyclerView.layoutManager = linearLayoutManager
//        val textView = binding.textDashboard
//        loanViewModel.mText.observe(viewLifecycleOwner, { s -> textView.text = s })
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("USER", Activity.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","a")
        val call : Call<List<LoanItem>> = RetrofitClientInstance.getClient.getEducations(token!!)
        call.enqueue(object : Callback<List<LoanItem>> {
            override fun onResponse(call: Call<List<LoanItem>>, response: Response<List<LoanItem>>) {
                if(response.code()==200){
                    val eduItemList : List<LoanItem> = response.body()!!
                    for(eduItem in eduItemList){
                        eduList.add(LoanItem(eduItem.link,eduItem.short_desc,eduItem.long_desc,eduItem.image_url,eduItem.category,eduItem.interest))
                    }
                    recyclerView.adapter = eduAdapter
                }
                else{
                    Toast.makeText(requireActivity(),"Bad Request", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<LoanItem>>, t: Throwable) {
                Toast.makeText(requireActivity(),"Request failed", Toast.LENGTH_SHORT).show()
            }

        })

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