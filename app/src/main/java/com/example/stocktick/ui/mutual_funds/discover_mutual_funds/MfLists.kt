package com.example.stocktick.ui.mutual_funds.discover_mutual_funds

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentMfListsBinding
import com.example.stocktick.ui.mutual_funds.MainViewModel
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.domain_models.MfModel

private const val TAG = "MfLists"
class MfLists : Fragment(R.layout.fragment_mf_lists) , AdapterView.OnItemSelectedListener, View.OnClickListener{
    private lateinit var binding: FragmentMfListsBinding
    private val viewModel: MainViewModel by activityViewModels()


    private lateinit var categorySpinner: Spinner
    private lateinit var mfRecyclerView: RecyclerView
    private lateinit var mfAdapter: MfAdapter
    private var selectedReturnInterval: ReturnType = ReturnType.THREE_YEAR
    private lateinit var tvReturnInterval: TextView

    private val args : MfListsArgs by navArgs()


    private val defCategories = listOf(
        "Category 1",
        "Category 2",
        "Category 3",
        "Category 4",
        "Category 5",
        "Category 6"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).binding.layoutBottomNeumorph.visibility = View.GONE

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMfListsBinding.bind(view)

        tvReturnInterval = binding.tvReturnInterval

        categorySpinner = binding.categorySpinner
        categorySpinner.onItemSelectedListener = this


        val adapter = ArrayAdapter(requireContext(),R.layout.menu_dropdown, viewModel.mfCategoriesTitle.value ?: defCategories)
        categorySpinner.adapter = adapter
        updateSpinner()


        //recycler view
        mfAdapter = MfAdapter(
            listOf(
                MfModel(),
                MfModel(mfName = "ICICI Prudential private limited"),
                MfModel(mfName = "TATA ki bohot achchi mutual fund"),
                MfModel(mfName = "Reliance ki mutual fund")
            ),
            ReturnType.THREE_YEAR,
            requireContext()
        )
        mfRecyclerView = binding.mfRecyclerView
        mfRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mfRecyclerView.adapter = mfAdapter


        binding.tvReturnHeader.setOnClickListener(this)
        tvReturnInterval.setOnClickListener(this)



    }

    private fun updateSpinner() {
        val index = args.categoryIndex
        Log.d(TAG, "updateSpinner: index :$index")
        if (index >= viewModel.mfCategoriesTitle.value?.size ?: 6) return
        Log.d(TAG, "updateSpinner: 2")
        categorySpinner.setSelection(index)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "onItemSelected: Item selected :$position")
        TODO("Here you should call the api and populate the recycler view")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Log.d(TAG, "onNothingSelected: ")
    }

    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.tvReturnHeader -> {
                onReturnIntervalClick()
            }
            R.id.tvReturnInterval ->{
                onReturnIntervalClick()
            }
        }
    }

    private fun onReturnIntervalClick(){
        when (selectedReturnInterval){
            ReturnType.ONE_DAY->{
                selectedReturnInterval = ReturnType.ONE_YEAR
                tvReturnInterval.text = selectedReturnInterval.value

            }
            ReturnType.ONE_YEAR-> {
                selectedReturnInterval = ReturnType.THREE_YEAR
                tvReturnInterval.text = selectedReturnInterval.value
            }
            ReturnType.THREE_YEAR->{
                selectedReturnInterval = ReturnType.ONE_DAY
                tvReturnInterval.text = selectedReturnInterval.value
            }
        }
        mfAdapter.returnType = selectedReturnInterval
        mfAdapter.notifyDataSetChanged()
    }


}