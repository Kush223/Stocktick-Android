package com.example.stocktick.auth

import android.app.Activity.MODE_PRIVATE
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.SmsBroadcastReceiver
import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.auth.model.PhoneModel
import com.example.stocktick.databinding.CreateAccountLayoutBinding
import com.example.stocktick.databinding.FragmentLoginOtpBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant.LOG_TAG
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//TODO() -- Fix the app bar colour

//TODO() -- Fix the phone number shows right part.

//TODO () -- add a loader to request otp button

//TODO() -- reformat the login code to not use deprecated method?

class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginOtpBinding

    //xml variables submitPhone = button = bt_login_request_otp
    //edit text = phoneEdit = et_login_phone
    //otp card and phone card are crazy...they are visibility toggles have to find a way to integrate that into ui

    private lateinit var mButtonSubmitPhone: Button
    private lateinit var mEditTextPhoneEdit: EditText
    private lateinit var mCountryCodePicker: CountryCodePicker
    private lateinit var mButtonSubmitOtp: Button
    private lateinit var mCreateAccountLayoutBinding: CreateAccountLayoutBinding

    private var phoneREGEXPattern = Regex("^[0-9]{9,12}$")
    //for innternational regex link: /^\+(?:[0-9] ?){6,14}[0-9]$/
    //oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s03.html

    //above regex is without ccp
    //another link for country codes
    //link for regex pattern with ccp,  https://stackoverflow.com/questions/35324149/phonenumberutils-isglobalphonenumber-not-returning-proper-results
    private lateinit var smsBroadCastReceiver: SmsBroadcastReceiver
    private val REQ_USER_CONSENT = 200
    private lateinit var phone: String
    private lateinit var otp: String

    private fun initialiseVariables() {
        mButtonSubmitPhone = _binding.btLoginRequestOtp
        mEditTextPhoneEdit = _binding.etLoginPhoneNumber
        mCountryCodePicker = _binding.loginCountryCodePicker
        mButtonSubmitOtp = _binding.btOtpSubmit
        mCreateAccountLayoutBinding = _binding.ca

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSmartUserConsent()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //OTP retrofit calls.
        otpRetrofitCalls()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginOtpBinding.inflate(layoutInflater)
        val view: View = _binding.root
        initialiseVariables()
        return view
    }


    @DelicateCoroutinesApi
    private fun otpRetrofitCalls() {

        //SUBMIT BUTTON WORKINGS
        mButtonSubmitPhone.setOnClickListener {

            phone = mEditTextPhoneEdit.text.toString().trim()
            //val ccp = mCountryCodePicker.selectedCountryCode.toString()

            if (!phone.matches(phoneREGEXPattern)) {
                mEditTextPhoneEdit.error = "Please enter a correct number"
            } else {
                submitPhoneNumberButtonResponse()
            }
        }

        //OTP BUTTON WORKINGS
        submitOtpButtonRetrofit()
    }


    //coroutine api calls not view model.
    @DelicateCoroutinesApi
    fun submitPhoneNumberButtonResponse() {
        GlobalScope.launch(Dispatchers.Main) {
            val phoneModel = PhoneModel(phone)
            try {
                val resp = RetrofitClientInstance.retrofitService.getOtp(phoneModel)
                _binding.otpCard.visibility = View.VISIBLE
                val str = "+91-$phone"
                _binding.otpUserPhoneNumber.text = str
                _binding.phoneCard.visibility = View.INVISIBLE
                Log.d(LOG_TAG,"OTP RESEND CHECK")

            } catch (error: Exception) {
                showToast("Request failed CATCH ERROR LOGIN")
            }

        }
    }

    private fun submitOtpButtonRetrofit() {
        //SUBMIT OTP BUTTON WORKINGS
        mButtonSubmitOtp.setOnClickListener {
            otp = _binding.pinview.text.toString()
            if (otp.length == 6) {
                val phoneModel = PhoneModel(phone, otp)
                handleSubmitOTP(phoneModel)
            } else {
                showToast("Otp should be of 6 digits")
            }
        }
        val mButtonBackOtp = _binding.vectorOtpBackArrow
        mButtonBackOtp.setOnClickListener{
            _binding.otpCard.visibility = View.INVISIBLE
            _binding.phoneCard.visibility = View.VISIBLE
        }

        val mButtonResendOtp = _binding.tvLoginResendOtp
        mButtonResendOtp.setOnClickListener{

            submitPhoneNumberButtonResponse()
        }
    }

    @DelicateCoroutinesApi
    private fun handleSubmitOTP(phoneModel: PhoneModel) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val resp = RetrofitClientInstance.retrofitService.validateOtp(phoneModel)
                Log.d(LOG_TAG, resp.toString())
                handleViewPostOTP(resp)

            } catch (error: Exception) {
                Log.d(LOG_TAG, "Error occurred: " + error.message)
                showToast("Request failed")
            }
        }
    }

    private fun handleViewPostOTP(res: GetOtpModel) {
        val old = res.old_user
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(USER, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(TOKEN, res.authToken)
        editor.apply()

        if (old == true) {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        } else {

            _binding.otpCard.visibility = View.INVISIBLE


            mCreateAccountLayoutBinding.root.visibility = View.VISIBLE
            val name =  mCreateAccountLayoutBinding.etCreateAccountUserName
            val email =mCreateAccountLayoutBinding.etCreateAccountEmailAddress
            val submitButton = mCreateAccountLayoutBinding.btCreateAccountSubmit
            val skipButton = mCreateAccountLayoutBinding.btCreateAccountSkip

            submitButton.setOnClickListener {
                if (name.text.isEmpty()) {
                    name.error = "Please enter your name"
                } else if (email.text.isEmpty()) {
                    email.error = "Please enter your email id"
                } else {
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            skipButton.setOnClickListener {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }

        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    //NOT TOUCHING FOR NOW??
    private fun registerBroadcastListener() {
        smsBroadCastReceiver = SmsBroadcastReceiver()
        smsBroadCastReceiver.smsBroadCastReceiverListener =
            object : SmsBroadcastReceiver.SmsBroadCastReceiverListener {
                override fun onSuccess(intent: Intent?) {
                    startActivityForResult(intent, REQ_USER_CONSENT)
//                    link: https://stackoverflow.com/questions/62671106/onactivityresult-method-is-deprecated-what-is-the-alternative
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