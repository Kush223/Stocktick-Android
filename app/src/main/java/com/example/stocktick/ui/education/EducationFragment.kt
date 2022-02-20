package com.example.stocktick.ui.education

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentEducationBinding
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER

//TODO() -- use constants instead in places.
//TODO() -- API integration to webinar and youtube cards
//TODo() -- Change the youtube to integrate this link: <!--https://github.com/PierfrancescoSoffritti/android-youtube-player-->

class EducationFragment : Fragment() {
    private lateinit var eduViewModel: EducationViewModel
    private lateinit var _binding: FragmentEducationBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEducationBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = EducationViewModelFactory(requireContext())
        eduViewModel = ViewModelProvider(this, viewModelFactory)[EducationViewModel::class.java]
        (activity as AppCompatActivity).supportActionBar?.title = "Education"

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(USER, Activity.MODE_PRIVATE)
        val token = sharedPreferences.getString(TOKEN, "a")

//        val call : Call<List<LoanItem>> = RetrofitClientInstance.retrofitService.getEducations(token!!)
//        call.enqueue(object : Callback<List<LoanItem>> {
//            override fun onResponse(call: Call<List<LoanItem>>, response: Response<List<LoanItem>>) {
//                if(response.code()==200){
//                    val eduItemList : List<LoanItem> = response.body()!!
//                    for(eduItem in eduItemList){
//                        eduList.add(LoanItem(eduItem.link,eduItem.short_desc,eduItem.long_desc,eduItem.image_urls,eduItem.category,eduItem.interest))
//                    }
//                    recyclerView.adapter = eduAdapter
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