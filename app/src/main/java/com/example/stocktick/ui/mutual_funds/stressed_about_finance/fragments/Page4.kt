package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import br.com.felix.horizontalbargraph.HorizontalBar
import br.com.felix.horizontalbargraph.model.BarItem
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPage4Binding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.HostActivity
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.Page4Dto
import kotlinx.coroutines.*


private const val TAG = "Page4"
class Page4 : Fragment(R.layout.fragment_page4) {
    private lateinit var binding: FragmentPage4Binding

    private lateinit var hBarGraph: HorizontalBar
    private val viewModel: MainViewModel by activityViewModels()

    private var fd = 5000.0
    private var mf = 5000.0
    private var epf = 5000.0
    private var shares = 5000.0

    private lateinit var etFd: NeumorphEditText
    private lateinit var etMf: NeumorphEditText
    private lateinit var etEpf: NeumorphEditText
    private lateinit var etShares: NeumorphEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPage4Binding.bind(view)
        val tracker = (activity as HostActivity).customTracker
        tracker.move(3)

        //bindings
        etFd = binding.etFixedDeposit
        etEpf= binding.etEpf
        etMf = binding.etMutualFunds
        etShares = binding.etShares
        //initializations
        etFd.setText(fd.toString())
        etEpf.setText(epf.toString())
        etMf.setText(mf.toString())
        etShares.setText(shares.toString())



        hBarGraph = binding.hBarGraph
        hBarGraph.init(requireContext()).apply {
            add(BarItem("FD", fd, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("MF's", mf, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("EPF/NPS", epf, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("Share", shares, Color.parseColor("#30BA00"), Color.WHITE))
        }
            .build()

        autofill()



        var job: Job? = null
        etFd.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    fd = it.toDouble()
                    update()

                }
            }

        }
        etMf.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    mf = it.toDouble()
                    update()
                }
            }
        }
        etEpf.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    epf = it.toDouble()
                    update()
                }
            }
        }
        etShares.onTextChangeListener {
            if (job != null) job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Default) {
                delay(500)
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "onViewCreated: text changed :$it")
                    if (it.isEmpty()) return@withContext
                    shares = it.toDouble()
                    update()
                }
            }
        }

        binding.btNext.setOnClickListener{
            handleOnClick()
        }

    }
    private fun handleOnClick() {
        viewModel.postPage4(
            page4Dto = Page4Dto(
                md = fd.toInt(),
                mf = mf.toInt(),
                nps = epf.toInt(),
                shares = shares.toInt()
            )
        ){
            if (it){
                view?.findNavController()?.navigate(R.id.action_page4_to_page5)
            } else {
                view?.findNavController()?.navigate(R.id.action_page4_to_page5) //remove it later
                Toast.makeText(requireContext(), "Something went wrong.\nPlease check your internet connection", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun update(){
        hBarGraph.removeAll()
        hBarGraph.apply {
            add(BarItem("FD", fd, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("MF's", mf, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("EPF/NPS", epf, Color.parseColor("#30BA00"), Color.WHITE))
            add(BarItem("Share", shares, Color.parseColor("#30BA00"), Color.WHITE))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun autofill(){
        viewModel.getPage4{ isSuccessful, page4 ->
            if (isSuccessful && page4!=null){
                etFd.setText(page4!!.md.toString())
                etMf.setText(page4!!.mf.toString())
                etEpf.setText(page4!!.nps.toString())
                etShares.setText(page4!!.shares.toString())
                update()

            }


        }
    }




}