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
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentEducationBinding
import com.example.stocktick.network.RetrofitClientInstance
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
//TO CONTINUE -- complete the adapter based code, in blog adapter, webinar adapter
// add their codes to models and check the api response if that is coming up or not
// check along with the data is being received or not.
//Start at the adapters. and once they are done integrate the youtube view and the webview parts.

//THEN ofc debugging part will be left well :)


//TODO() WEBINAR-- API - self, hosted, <===>
//self hosted then API hit and success register -- popup success
//third party --> 1)API hit backend then webview. redirect

//TODO() BLOGS --- API - video type or link type <===>
//video type, we will be taken to a webview.
//on the app only video will run youtube.


//TODO() -- adapters attatch ?? need hai? why do we need adapters?
//TODO() -- use constants instead in places.
//TODO() -- API integration to webinar and youtube cards
//TODo() -- Change the youtube to integrate this link: <!--https://github.com/PierfrancescoSoffritti/android-youtube-player-->

class EducationFragment : Fragment() {
    private lateinit var eduViewModel: EducationViewModel
    private lateinit var _binding: FragmentEducationBinding

    private lateinit var mRecyclerViewWebinar: RecyclerView
    private lateinit var mRecyclerViewBlogs: RecyclerView
//    private lateinit var mWebinarList : List<WebinarItem>

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

        val linearLayoutManagerWebinar =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)
        val linearLayoutManagerBlogs =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, true)

        mRecyclerViewWebinar.layoutManager = linearLayoutManagerWebinar
        mRecyclerViewBlogs.layoutManager = linearLayoutManagerBlogs

        mRecyclerViewWebinar = _binding.eduWebinarList
        mRecyclerViewBlogs = _binding.eduBlogList


        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(TOKEN, SHAREDPREFERENCES_TOKEN_A).toString()

        getWebinarList()
        getBlogList()


    }

    private fun getBlogList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.getEducations(tokenSharedPreference, "E")
                //have used E instead of M here.
                setAdapterBlog(response)
            } catch (error: Exception) {
                Toast.makeText(requireActivity(), "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                    .show()
//                Log.d("ERROR EDU WEBI", error.toString())
            }
        }
    }

    private fun setAdapterBlog(response: Response<List<WebinarItem>>) {
        if (response.code() == 200) {
            mRecyclerViewBlogs.adapter = BlogAdapter()
        }else {
            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getWebinarList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.getEducations(tokenSharedPreference, "E")
                //have used E instead of M here.
                setAdapterWebinar(response)
            } catch (error: Exception) {
                Toast.makeText(requireActivity(), "Request failed CATCH ERROR", Toast.LENGTH_SHORT)
                    .show()
//                Log.d("ERROR EDU WEBI", error.toString())
            }

        }
    }

    private fun setAdapterWebinar(response: Response<List<WebinarItem>>) {
        //one way from LoanFragment
        //another way from, https://github.com/Ana2k/JSONApp-NotesApp/blob/main/JsonExtractorFirebase/app/src/main/java/com/example/androidjsonextractor/UsersFragment.kt
//        trying method 1 for now
        mRecyclerViewWebinar.adapter = WebinarAdapter(,)
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