package com.example.stocktick.ui.education

import android.app.Activity
import android.app.Dialog
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.webkit.WebView
import android.widget.ProgressBar
import android.widget.TextView
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
import com.example.stocktick.ui.education.model.RegisterWebinarModel
import com.example.stocktick.ui.education.model.WebinarItem
import com.example.stocktick.utility.Constant.EDUCATION
import com.example.stocktick.utility.Constant.SHAREDPREFERENCES_TOKEN_A
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response


//immediately


//TODO() -- intents
//TODO() -- if youtube_url is given then the template of that yt in blog.
//TODO() -- if only image_url then only image_url
//TODO() -- if webinar is self hosted -
//TODo() -- Change the youtube to integrate this link: <!--https://github.com/PierfrancescoSoffritti/android-youtube-player-->

class EducationFragment : Fragment(), WebinarInterface {

    private var blogMutableList: MutableList<BlogItem> = ArrayList()
    private var webinarMutableList: MutableList<WebinarItem> = ArrayList()

    private lateinit var eduViewModel: EducationViewModel
    private lateinit var _binding: FragmentEducationBinding

    private lateinit var mRecyclerViewWebinar: RecyclerView
    private lateinit var mRecyclerViewBlog: RecyclerView
    private lateinit var mProgressBar: ProgressBar

    private lateinit var webinarAdapter: WebinarAdapter
    private lateinit var blogAdapter: BlogAdapter
    private lateinit var mWebViewWebinar: WebView

    private lateinit var dialog: Dialog


    private lateinit var tokenSharedPreference: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEducationBinding.inflate(inflater, container, false)
        mProgressBar = _binding.progressWebinar

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

        mProgressBar.visibility = View.VISIBLE
        getWebinarList()
        getBlogList()

    }

    private fun getBlogList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                showViewsAfterReload()
                val response =
                    RetrofitClientInstance.retrofitService.getBlogs(tokenSharedPreference)
                setAdapterBlog(response)
                mProgressBar.visibility = View.INVISIBLE

            } catch (error: Exception) {
                showNetworkErrorViews()
            }
        }
    }

    private fun getWebinarList() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                showViewsAfterReload()
                val response =
                    RetrofitClientInstance.retrofitService.getWebinar(tokenSharedPreference)

                setAdapterWebinar(response)
                mProgressBar.visibility = View.INVISIBLE

            } catch (error: Exception) {
                showNetworkErrorViews()
            }

        }
    }

    private fun showViewsAfterReload() {
        _binding.eduNetworkErrorTv.visibility = View.INVISIBLE
        _binding.btTryAgain.visibility = View.INVISIBLE
        _binding.webinarTitleLinearLayout.visibility = View.VISIBLE
        _binding.blogTitleLinearLayout.visibility = View.VISIBLE
    }

    private fun showNetworkErrorViews() {
        _binding.eduNetworkErrorTv.visibility = View.VISIBLE
        _binding.btTryAgain.visibility = View.VISIBLE
        _binding.btTryAgain.setOnClickListener {
            getWebinarList()
            getBlogList()
        }
        _binding.webinarTitleLinearLayout.visibility = View.INVISIBLE
        _binding.blogTitleLinearLayout.visibility = View.INVISIBLE
        //mProgressBar.visibility = View.INVISIBLE
    }

    private fun setAdapterWebinar(response: Response<List<WebinarItem>>) {
        if (response.code() == 200) {
            val webinarItemList: List<WebinarItem>? = response.body()
            if (webinarItemList != null) {
                for (webinarItem in webinarItemList) {
                    webinarMutableList.add(webinarItem)
                }
            } else {
                //Log.d("nullitem", webinarItemList.toString())
            }

            webinarAdapter =
                WebinarAdapter(requireContext(), webinarMutableList, tokenSharedPreference, this)
            mRecyclerViewWebinar.adapter = webinarAdapter
        } else {
            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_SHORT).show()
        }

    }
    private fun setAdapterBlog(response: Response<List<BlogItem>>) {
        if (response.code() == 200) {
            val blogItemList: List<BlogItem>? = response.body()
            if (blogItemList != null) {
                for (blogItem in blogItemList) {
                    blogMutableList.add(blogItem)
                }
            } else {
//                Log.d("nullitemBlog", blogItemList.toString())
            }

            blogAdapter = BlogAdapter(requireContext(), blogMutableList)
            mRecyclerViewBlog.adapter = blogAdapter

        } else {
            Toast.makeText(requireActivity(), "Bad Request", Toast.LENGTH_SHORT).show()
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

    override fun onCellClickListener(id: String?, hostedBy: String?) {
        //use this retrofit call to webinar from here.
        val registerWebinarModel = RegisterWebinarModel(id.toString())
        mProgressBar.visibility = View.VISIBLE
        postRequestWebinar(registerWebinarModel)
        mProgressBar.visibility = View.INVISIBLE

        //if hosted_by is
        Log.d("HOSTEDFRAG", hostedBy.toString())
        if(hostedBy.toString()=="other"){
            //webview

        }

    }

    @DelicateCoroutinesApi
    private fun postRequestWebinar(registerWebinarModel: RegisterWebinarModel) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response =
                    RetrofitClientInstance.retrofitService.postRegisterToWebinar(
                        tokenSharedPreference,
                        registerWebinarModel
                    )
                Log.d("TAGpostreq", response.toString() + "\n")

                //show dialog saying you are already registered.
                dialog = Dialog(requireContext())
                showDialog(dialog, response.code())

            } catch (error: Exception) {
                Toast.makeText(context, "Request failed Network ERROR", Toast.LENGTH_SHORT)
                    .show()
                Log.d("ERROR", error.toString())
                dialog = Dialog(requireContext())
                showDialog(dialog,404)

            }
        }
    }

    private fun showDialog(dialog: Dialog, code: Int) {
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val alreadyTv: TextView? = dialog.findViewById(R.id.already_dialog_text_view)
        val successTv: TextView? = dialog.findViewById(R.id.success_tv)
        val networkTv: TextView? = dialog.findViewById(R.id.edu_network_error_tv)

        if (code == 200) {
            alreadyTv?.visibility = View.INVISIBLE
            successTv?.visibility = View.VISIBLE
            networkTv?.visibility = View.INVISIBLE
            createDialog(dialog)
        } else if (code == 400) {
            alreadyTv?.visibility = View.VISIBLE
            successTv?.visibility = View.INVISIBLE
            networkTv?.visibility = View.INVISIBLE
            createDialog(dialog)
        } else {
            alreadyTv?.visibility = View.INVISIBLE
            successTv?.visibility = View.INVISIBLE
            networkTv?.visibility = View.VISIBLE
            createDialog(dialog)
        }

    }

    private fun createDialog(dialog: Dialog) {
        dialog.setContentView(R.layout.already_registered_dialog_layout)
        dialog.show()
        val metrics: DisplayMetrics? = context?.resources?.displayMetrics
        val width = metrics?.widthPixels
        val height = metrics?.heightPixels
        dialog.window?.setLayout((4 * width!!) / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
