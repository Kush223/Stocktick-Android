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
import com.hbb20.CountryCodePicker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var _binding: FragmentLoginBinding
    private val binding get() = _binding
    private lateinit var submitPhone: Button, phoneEdit : EditText,


//    xml variables submitPhone = button = bt_login_request_otp
    //edit text = phoneEdit = et_login_phone

    private lateinit var mButtonSubmitPhone: Button
    private lateinit var mEditTextPhoneEdit: EditText
    private lateinit var mCountryCodePicker: CountryCodePicker

    private var phonePattern = Regex("^[6789]\\d{9}$")
    private var smsBroadCastReceiver: SmsBroadcastReceiver? = null
    private val REQ_USER_CONSENT = 200
    private var phone: String? = null
    private var otp: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSmartUserConsent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Assign variables
        mButtonSubmitPhone = _binding.btLoginRequestOtp
        mCountryCodePicker = _binding.loginCountryCodePicker


        // Inflate the layout for this fragment
        val view: View = _binding.root
        _binding.submitPhone.setOnClickListener {
            phone = _binding.phoneEdit.text.toString()
            if (!phone.matches(phonePattern)) {
                _binding.phoneEdit.error = "Please enter a correct number"
            } else {
                //Log.d("abc", phone)
                val phoneModel = PhoneModel(phone)
                val call: Call<GetOtpModel> = RetrofitClientInstance.getClient.getOtp(phoneModel)
                call.enqueue(object : Callback<GetOtpModel> {
                    override fun onResponse(
                        call: Call<GetOtpModel>,
                        response: Response<GetOtpModel>
                    ) {
                        if (response.code() == 200) {
                            _binding.otpCard.visibility = View.VISIBLE
                            _binding.phoneCard.visibility = View.GONE
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
        _binding.loginButton.setOnClickListener {
            otp = _binding.enterOtp.text.toString()
            Toast.makeText(requireActivity(), otp.length.toString(), Toast.LENGTH_SHORT).show()
            if (otp.length == 6) {
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
                _binding.enterOtp.error = "Otp should be of 6 digits"
            }

        }
        return view
    }

    private fun registerBroadcastListener() {
        smsBroadCastReceiver = SmsBroadcastReceiver()
        smsBroadCastReceiver.smsBroadCastReceiverListener =
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


    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(smsBroadCastReceiver)
    }
}