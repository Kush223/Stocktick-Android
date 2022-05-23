package com.example.stocktick.ui.get_assistant


import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentGetAssistantDialogBinding
import com.example.stocktick.models.UserProfile


class GetAssistantDialog : DialogFragment(R.layout.fragment_get_assistant_dialog) {
    private val TAG = "GetAssistantDialog"

    private lateinit var binding: FragmentGetAssistantDialogBinding
    private val viewModel : GetAssistantViewModel by activityViewModels()

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMessage: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGetAssistantDialogBinding.bind(view)
        etName = binding.etName
        etEmail = binding.etEmail
        etMessage = binding.etMessage

        if (viewModel.message.isNotEmpty())
            etMessage.setText(viewModel.message)

        val userProfileObserver = Observer<UserProfile> { userProfile ->
            Log.d(TAG, "onViewCreated: Observer called $userProfile")
            if (userProfile == null) return@Observer

            if (etName.text.isEmpty())
            etName.setText(userProfile.name ?: "")
            if (etEmail.text.isEmpty())
            etEmail.setText(userProfile.email ?: "")

        }
        viewModel.userDetail.observe(requireActivity(), userProfileObserver)

        binding.btSubmit.setOnClickListener{
            if (viewModel.userDetail.value == null){
                Toast.makeText(requireContext(), "Something went wrong.\nPlease check your internet connection!", Toast.LENGTH_SHORT).show()
                viewModel.updateUserDetails()
                return@setOnClickListener
            }
            if (fieldsLeftEmpty()) return@setOnClickListener
            viewModel.postQuery(
                QueryModel(
                    name = etName.text.toString(),
                    email = etEmail.text.toString(),
                    phone = viewModel.userDetail.value!!.phone ?: "",
                    query = etMessage.text.toString()
                )
            ){
                if (it){
                    Toast.makeText(requireContext(), "Query sent successfully", Toast.LENGTH_SHORT).show()
                    this.dismiss()
                }
                else {
                    Toast.makeText(requireContext(), "Something went wrong.\nPlease check your internet connection!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.message = etMessage.text.toString()
    }

    private fun fieldsLeftEmpty(): Boolean {
        if (etName.text.isEmpty()) {
            etName.error = "Please enter your name"
            etName.requestFocus()
            return true
        }
        if (etEmail.text.isEmpty()) {
            etEmail.error = "Please enter your email"
            etEmail.requestFocus()
            return true
        }
        if (etMessage.text.isEmpty()){
            etMessage.error = "Please enter your query"
            etMessage.requestFocus()
            return true
        }
        return false
    }


    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }
}