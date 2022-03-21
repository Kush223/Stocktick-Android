package com.example.stocktick.ui.mutual_funds.risk_factor.fragments

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentUserDetailBinding
import com.example.stocktick.ui.customviews.NeumorphEditText
import com.example.stocktick.ui.mutual_funds.risk_factor.RiskFactorViewModel
import com.example.stocktick.utility.extension_functions.getResizedBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

val genders = listOf(
    "Male",
    "Female",
    "Others"
)


private const val TAG = "UserDetailFragmentT"
class UserDetailFragment : Fragment(R.layout.fragment_user_detail), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private val viewModel: RiskFactorViewModel by activityViewModels()
    private lateinit var binding :FragmentUserDetailBinding
    private lateinit var name: NeumorphEditText
    private lateinit var email: NeumorphEditText
    private lateinit var gender: Spinner
    private lateinit var age : EditText
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
      // (activity as AppCompatActivity)?.supportActionBar?.setIcon(R.drawable.man_with_mic) // set icon from api
        name = binding.nameCard
        email = binding.emailCard
        gender = binding.gender
        age = binding.age

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        lifecycleScope.launch(Dispatchers.Default){
            var profilePic = BitmapFactory.decodeResource(resources,R.mipmap.profile_pic_placeholder)
            profilePic = profilePic.getResizedBitmap(90,90)
            withContext(Dispatchers.Main){
                (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(BitmapDrawable(resources, profilePic));
            }
        }
        (activity as AppCompatActivity).supportActionBar?.title = ""


        gender.onItemSelectedListener= this
        val adapter = ArrayAdapter(requireContext(), R.layout.gender_spinner_dropdown, genders)
        adapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        gender.adapter = adapter

        

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.help, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    

    override fun onClick(v: View?) {
        if (v == null) return
        Log.d(TAG, "onClick: with id :${v.id}")
        when (v.id){
            R.id.submitBtn -> {
                if (name.getText().isEmpty() || email.getText().isEmpty() || age.text.isEmpty() ) {
                    Toast.makeText(requireActivity(), "Please fill the details", Toast.LENGTH_SHORT).show()
                    return
                }
                viewModel.postUserProfile(
                    age = age.text.toString().toInt(), 
                    name = name.getText(),
                    email = email.getText(),
                    gender = gender.selectedItem.toString()
                ){
                    if(it){
                        v.findNavController().navigate(R.id.to_questions_fragment)
                    }
                    else {
                        Toast.makeText(requireActivity(), "Someting went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}