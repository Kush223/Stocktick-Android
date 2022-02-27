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

//TODo() -- Change the youtube to integrate this link: <!--https://github.com/PierfrancescoSoffritti/android-youtube-player-->

class EducationFragment : Fragment() {

    private var blogMutableList: MutableList<BlogItem> = ArrayList()
    private var webinarMutableList: MutableList<WebinarItem> = ArrayList()

    //taken from kush's code in loanfragment
    private lateinit var eduViewModel: EducationViewModel
    private lateinit var _binding: FragmentEducationBinding

    private lateinit var mRecyclerViewWebinar: RecyclerView
    private lateinit var mRecyclerViewBlog: RecyclerView

    private lateinit var webinarAdapter: WebinarAdapter
    private lateinit var blogAdapter: BlogAdapter


    private lateinit var tokenSharedPreference: String

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

        //webinar
        mRecyclerViewWebinar = _binding.eduWebinarList
        mRecyclerViewWebinar.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        //blogs
        mRecyclerViewBlog = _binding.eduBlogList
        mRecyclerViewBlog.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)


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
                    RetrofitClientInstance.retrofitService.getBlogs(tokenSharedPreference)

                setAdapterBlog(response)
                //                - logic wise
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
            val blogItemList: List<BlogItem>? = response.body()
            Log.d("blogItem", blogItemList.toString())
            if (blogItemList != null) {
                for (blogItem in blogItemList) {
                    Log.d("itemlistBlog1", blogItemList.toString())
                    blogMutableList.add(
                        BlogItem(
                            blogItem.short_desc,
                            blogItem.long_desc,
                            blogItem.image_url,
                            blogItem.video_link,
                            blogItem.blog_link
                        )
                    )
                    Log.d("itemlistBlog", blogItemList.toString())
                }
                Log.d("itemlistBlog", blogItemList.toString())
            } else {
                Log.d("nullitemBlog", blogItemList.toString())
            }

            blogAdapter = BlogAdapter(requireContext(), blogMutableList)
            mRecyclerViewBlog.adapter = blogAdapter
            Log.d(
                "blogAdapter2",
                blogAdapter.toString() + "\n" + mRecyclerViewWebinar.adapter.toString()
            )

            //why are we attaching the adapter twice???

        } else {
            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_SHORT).show()
            Log.d("Response code", response.toString() + "\n" + response.code())
        }

    }


    private fun getWebinarList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.getWebinar(tokenSharedPreference)
                //will this api call be to education or to webinar??
                Log.d("response", response.body().toString())
                setAdapterWebinar(response)

            } catch (error: Exception) {
                Log.d("errorWEbi", error.toString())
                Toast.makeText(
                    requireActivity(),
                    "Request failed CATCH ERROR webinar",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }
    }

    private fun setAdapterWebinar(response: Response<List<WebinarItem>>) {
        if (response.code() == 200) {
            val webinarItemList: List<WebinarItem>? = response.body()
            Log.d("webinarItem", webinarItemList.toString())
            if (webinarItemList != null) {
                for (webinarItem in webinarItemList) {
                    webinarMutableList.add(
                        WebinarItem(
                            webinarItem.title,
                            webinarItem.short_desc,
                            webinarItem.image_url,
                            webinarItem.hosted_by,
                            webinarItem.other_host_name,
                            webinarItem.webinar_redirect_url
                        )
                    )
                    Log.d("itemlist", webinarItemList.toString())
                }
                Log.d("itemlist", webinarItemList.toString())
            } else {
                Log.d("nullitem", webinarItemList.toString())
            }

//            webinarMutableList.add(WebinarItem())
            webinarAdapter = WebinarAdapter(requireContext(), webinarMutableList)
            mRecyclerViewWebinar.adapter = webinarAdapter
            Log.d(
                "webinarAdapter2",
                webinarAdapter.toString() + "\n" + mRecyclerViewWebinar.adapter.toString()
            )

            //why are we attaching the adapter twice???

        } else {
            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_SHORT).show()
            Log.d("Response code", response.toString() + "\n" + response.code())
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
