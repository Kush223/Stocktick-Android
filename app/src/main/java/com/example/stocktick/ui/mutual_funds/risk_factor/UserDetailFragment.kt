package com.example.stocktick.ui.mutual_funds.risk_factor

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentUserDetailBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


private const val TAG = "UserDetailFragmentT"
class UserDetailFragment : Fragment(R.layout.fragment_user_detail), View.OnClickListener {


    private lateinit var binding :FragmentUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserDetailBinding.bind(view)
        binding.submitBtn.setOnClickListener(this) //this corresponds to the id R.id.mainLayout, look into the custom button class for more details
        (activity as AppCompatActivity)?.supportActionBar?.setIcon(R.drawable.man_with_mic) // set icon from api

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View?) {
        if (v == null) return
        Log.d(TAG, "onClick: with id :${v.id}")
        when (v.id){
            R.id.submitBtn -> {
                v.findNavController().navigate(R.id.to_questions_fragment)
            }
        }
    }
}