package com.example.stocktick.LoginSignup

import android.app.Activity.MODE_PRIVATE
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stocktick.LoginSignup.Models.GetOtpModel
import com.example.stocktick.LoginSignup.Models.PhoneModel
import com.example.stocktick.MainActivity
import com.example.stocktick.Network.RetrofitClientInstance
import com.example.stocktick.R
import com.example.stocktick.SmsBroadcastReceiver
import com.example.stocktick.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.phone.SmsRetriever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//TODO() Cleanup the generic fragment files into usable formats- Discuss.
//Not added ViewModelFactory to this as no ViewModel in this yet.

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    private var phonePattern = Regex("^[6789]\\d{9}$")
    private var smsBroadCastReceiver: SmsBroadcastReceiver? = null
    private val REQ_USER_CONSENT = 200
    private var phone: String? = null
    private var otp: String? = null

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSmartUserConsent()
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        binding!!.submitPhone.setOnClickListener {
            phone = binding!!.phoneEdit.text.toString()
            if (!phone!!.matches(phonePattern)) {
                binding!!.phoneEdit.error = "Please enter a correct number"
            } else {
                //Log.d("abc", phone!!)
                val phoneModel = PhoneModel(phone)
                val call: Call<GetOtpModel> = RetrofitClientInstance.getClient.getOtp(phoneModel)
                call.enqueue(object : Callback<GetOtpModel> {
                    override fun onResponse(
                        call: Call<GetOtpModel>,
                        response: Response<GetOtpModel>
                    ) {
//                        val str : String? = response.body()?.message
//                        if (str != null) {
//                            Log.d("cdc",str)
//                        }
                        //Toast.makeText(requireActivity(),response.body()?.message,Toast.LENGTH_SHORT).show()
                        if (response.code() == 200) {
                            binding!!.otpCard.visibility = View.VISIBLE
                            binding!!.phoneCard.visibility = View.GONE
                        } else {
                            Toast.makeText(
                                requireActivity(),
                                "Request not sent",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                    override fun onFailure(call: Call<GetOtpModel>, t: Throwable) {
                        Toast.makeText(requireActivity(), "Request failed", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
            }
        }
        binding!!.loginButton.setOnClickListener {
            otp = binding!!.enterOtp.text.toString()
            Toast.makeText(requireActivity(), otp!!.length.toString(), Toast.LENGTH_SHORT).show()
            if (otp!!.length == 6) {
                val phoneModel = PhoneModel(phone, otp)
                val call: Call<GetOtpModel> =
                    RetrofitClientInstance.getClient.validateOtp(phoneModel)
                call.enqueue(object : Callback<GetOtpModel> {
                    override fun onResponse(
                        call: Call<GetOtpModel>,
                        response: Response<GetOtpModel>
                    ) {
                        if (response.code() == 200) {
                            val res = response.body()
                            val old = res?.old_user
                            val sharedPreferences: SharedPreferences =
                                requireActivity().getSharedPreferences("USER", MODE_PRIVATE)
                            val editor: SharedPreferences.Editor = sharedPreferences.edit()
                            editor.putString("token", res?.authToken)
                            editor.apply()
                            if (old == true) {
                                val intent = Intent(activity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
//                                val fragment: Fragment = SignupFragment()
//                                val fragmentManager = requireActivity().supportFragmentManager
//                                val fragmentTransaction = fragmentManager.beginTransaction()
//                                fragmentTransaction.replace(R.id.login_signup_activity, fragment)
//                                fragmentTransaction.addToBackStack(null)
//                                fragmentTransaction.commit()
                                val dialog = Dialog(requireActivity())
                                dialog.setTitle("Information")
                                dialog.setCancelable(false)
                                dialog.setContentView(R.layout.login_dialog)
                                val name = dialog.findViewById(R.id.name_signup) as EditText
                                val email = dialog.findViewById(R.id.email_signup) as EditText
                                val submitBtn = dialog.findViewById(R.id.signup_button) as Button
                                val skipBtn = dialog.findViewById(R.id.skip_button) as Button
                                submitBtn.setOnClickListener {
                                    if (name.text.isNotEmpty()) {
                                        name.error = "Please enter your name"
                                    } else if (!email.text.isEmpty()) {
                                        email.error = "Please enter your email id"
                                    } else {
                                        val intent = Intent(activity, MainActivity::class.java)
                                        startActivity(intent)
                                    }
                                }
                                skipBtn.setOnClickListener {
                                    val intent = Intent(activity, MainActivity::class.java)
                                    startActivity(intent)
                                }
                                dialog.show()
                                val metrics: DisplayMetrics = resources.displayMetrics;
                                val width = metrics.widthPixels;
                                val height = metrics.heightPixels;
                                //yourDialog.getWindow().setLayout((6 * width)/7, )
                                dialog.window?.setLayout(width, (4 * height) / 5);
                            }
                        } else {
                            Toast.makeText(
                                requireActivity(),
                                "Request not sent",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }

                    override fun onFailure(call: Call<GetOtpModel>, t: Throwable) {
                        Toast.makeText(requireActivity(), "Request failed", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
            } else {
                binding!!.enterOtp.error = "Otp should be of 6 digits"
            }

        }
        return view
    }

    private fun registerBroadcastListener() {
        smsBroadCastReceiver = SmsBroadcastReceiver()
        smsBroadCastReceiver!!.smsBroadCastReceiverListener =
            object : SmsBroadcastReceiver.SmsBroadCastReceiverListener {
                override fun onSuccess(intent: Intent?) {
                    startActivityForResult(intent, REQ_USER_CONSENT)
                }

                override fun onFailure() {

                }

            }
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        requireActivity().registerReceiver(smsBroadCastReceiver, intentFilter)
    }

    private fun startSmartUserConsent() {
        val client = SmsRetriever.getClient(requireActivity())
        client.startSmsRetriever()
    }

    override fun onStart() {
        super.onStart()
        registerBroadcastListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_USER_CONSENT) {
            if (resultCode == RESULT_OK && data != null) {
                val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                //getOtpFromMessage(message)
            }
        }
    }

//    private fun getOtpFromMessage(message: String?) {
//
//    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(smsBroadCastReceiver)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): LoginFragment {
            val fragment = LoginFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}