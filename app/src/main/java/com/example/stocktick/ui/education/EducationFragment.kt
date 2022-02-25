package com.example.stocktick.ui.education

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
import com.example.stocktick.databinding.FragmentEducationBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.education.model.BlogItem
import com.example.stocktick.ui.education.model.WebinarItem
import com.example.stocktick.utility.Constant.EDUCATION
import com.example.stocktick.utility.Constant.SHAREDPREFERENCES_TOKEN_A
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


//immediately


//TODO() -- launch the app and make both the webinar and then the blog end to end functional


//TODO() -- adapters attatch ?? need hai? why do we need adapters?

//TODo() -- Change the youtube to integrate this link: <!--https://github.com/PierfrancescoSoffritti/android-youtube-player-->

class EducationFragment : Fragment() {

    private var webinarMutableList: MutableList<WebinarItem> = ArrayList()

    //taken from kush's code in loanfragment
    private lateinit var eduViewModel: EducationViewModel
    private lateinit var _binding: FragmentEducationBinding

    private lateinit var mRecyclerViewWebinar: RecyclerView
    private lateinit var mRecyclerViewBlogs: RecyclerView
    //    private lateinit var mWebinarList : List<WebinarItem>

    private lateinit var webinarAdapter: WebinarAdapter


    private lateinit var tokenSharedPreference: String
    //    private lateinit var mEducationAdapterWebinar: EducationAdapter
    //private lateinit var mEducationAdapterBlogs: EducationAdapter

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
        (activity as AppCompatActivity).supportActionBar?.title = EDUCATION

        mRecyclerViewWebinar = _binding.eduWebinarList
//        mRecyclerViewBlogs = _binding.eduBlogList

        webinarAdapter = WebinarAdapter(requireContext(), webinarMutableList)

        val linearLayoutManagerWebinar =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
//        val linearLayoutManagerBlogs =
//            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)

        mRecyclerViewWebinar.layoutManager = linearLayoutManagerWebinar
//        mRecyclerViewBlogs.layoutManager = linearLayoutManagerBlogs


        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(TOKEN, SHAREDPREFERENCES_TOKEN_A).toString()

        mRecyclerViewWebinar.adapter = webinarAdapter
        getWebinarList()
//        getBlogList()
    }

    private fun getBlogList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.getBlogs(
                        tokenSharedPreference,
                        "Eb"
                    )

//                setAdapterBlog(response) - logic wise
                //json structure for this, in
                //first complete the webinar end to end.
            } catch (error: Exception) {
                Toast.makeText(requireActivity(), "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setAdapterBlog(response: Response<List<BlogItem>>) {
        if (response.code() == 200) {
            mRecyclerViewBlogs.adapter = BlogAdapter()
        } else {
            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getWebinarList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.getWebinar(
                        tokenSharedPreference,
                        "Ew"
                    )
                //will this api call be to education or to webinar??
                setAdapterWebinar(response)
            } catch (error: Exception) {
                Toast.makeText(requireActivity(), "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun setAdapterWebinar(response: Response<List<WebinarItem>>) {

        if (response.code() == 200) {
            val webinarItemList: List<WebinarItem>? = response.body()
            if (webinarItemList != null) {
                for (webinarItem in webinarItemList) {
                    webinarMutableList.add(
                        WebinarItem(
                            webinarItem.title,
                            webinarItem.short_desc,
                            webinarItem.image_uri,
                            webinarItem.hosted_by,
                            webinarItem.other_host_name,
                            webinarItem.webinar_redirect_url
                        )
                    )
                }
            }
            webinarMutableList.add(WebinarItem())
            //why add an empty item? why write all things as null?
            mRecyclerViewWebinar.adapter = webinarAdapter

            //why are we attaching the adapter twice?

        } else {
            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_SHORT).show()
            Log.d("Response code",response.toString()+"\n"+response.code())
        }

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