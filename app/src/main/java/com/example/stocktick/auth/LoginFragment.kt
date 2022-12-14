package com.example.stocktick.auth

import android.app.Activity.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stocktick.MainActivity
import com.example.stocktick.SmsBroadcastReceiver
import com.example.stocktick.auth.model.GetOtpModel
import com.example.stocktick.auth.model.PhoneModel
import com.example.stocktick.databinding.CreateAccountLayoutBinding
import com.example.stocktick.databinding.FragmentLoginOtpBinding
import com.example.stocktick.models.requests.UpdateUserProfileDTO
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant.LOG_TAG
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import com.hbb20.CountryCodePicker
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern


//TODO () -- add a loader to request otp button
//TODO() -- sms reader make fully functional.

//link: https://stackoverflow.com/questions/55060626/android-how-to-display-transparent-loading-layer-above-the-activity

class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginOtpBinding

    //xml variables submitPhone = button = bt_login_request_otp
    //edit text = phoneEdit = et_login_phone
    //otp card and phone card are crazy...they are visibility toggles have to find a way to integrate that into ui

    private lateinit var mButtonSubmitPhone: Button
    private lateinit var mEditTextPhoneEdit: EditText
    private lateinit var mCountryCodePicker: CountryCodePicker
    private lateinit var mButtonSubmitOtp: Button
    private lateinit var mTextViewOtpPhoneNumber: TextView
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
    private lateinit var authToken: String


    private fun initialiseVariables() {
        mButtonSubmitPhone = _binding.btLoginRequestOtp
        mEditTextPhoneEdit = _binding.etLoginPhoneNumber
        mCountryCodePicker = _binding.loginCountryCodePicker
        mButtonSubmitOtp = _binding.btOtpSubmit
        mCreateAccountLayoutBinding = _binding.ca
        mTextViewOtpPhoneNumber = _binding.otpTvUserPhoneNumber

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        startSmartUserConsent()
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //SUBMIT PONE AND OTP retrofit calls.
        submitOtpRetrofitCalls()
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
    private fun submitOtpRetrofitCalls() {

        //SUBMIT BUTTON WORKINGS
        mButtonSubmitPhone.setOnClickListener {

            phone = mEditTextPhoneEdit.text.toString().trim()

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
                RetrofitClientInstance.retrofitService.getOtp(phoneModel)

                _binding.otpCard.visibility = View.VISIBLE

                val ccp = mCountryCodePicker.selectedCountryCode.toString()
                val otpPhoneString = "+$ccp-$phone"
                mTextViewOtpPhoneNumber.text = otpPhoneString

                _binding.phoneCard.visibility = View.GONE

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
        mButtonBackOtp.setOnClickListener {
            _binding.otpCard.visibility = View.INVISIBLE
            _binding.phoneCard.visibility = View.VISIBLE
        }

        val mButtonResendOtp = _binding.tvLoginResendOtp
        mButtonResendOtp.setOnClickListener {

            submitPhoneNumberButtonResponse()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    @DelicateCoroutinesApi
    private fun handleSubmitOTP(phoneModel: PhoneModel) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val resp = RetrofitClientInstance.retrofitService.validateOtp(phoneModel)
                Log.d(LOG_TAG, resp.toString())
                handleViewPostOTP(resp)

            } catch (error: Exception) {
                showToast("Request failed")
            }
        }
    }

    private fun handleViewPostOTP(res: GetOtpModel) {
        val old = res.Old_User
        val sharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences(USER, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(TOKEN, res.authToken)
        editor.apply()

        authToken = res.authToken.toString()

        if (old == true) {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        } else {
            _binding.otpCard.visibility = View.GONE

            mCreateAccountLayoutBinding.root.visibility = View.VISIBLE

            val name = mCreateAccountLayoutBinding.etCreateAccountUserName
            val email = mCreateAccountLayoutBinding.etCreateAccountEmailAddress
            val submitButton = mCreateAccountLayoutBinding.btCreateAccountSubmit
            val skipButton = mCreateAccountLayoutBinding.btCreateAccountSkip

            submitButton.setOnClickListener {
                if (name.text.isEmpty()) {
                    name.error = "Please enter your name"
                } else if (email.text.isEmpty() || !isEmailValid(email.text.toString())) {
                    email.error = "Please enter a valid email id"
                } else {

                    val userProfile = UpdateUserProfileDTO(name = name.text.toString(), email = email.text.toString())
                    updateProfile(userProfile)
                }
            }

            skipButton.setOnClickListener {
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    @DelicateCoroutinesApi
    private fun updateProfile(userProfile: UpdateUserProfileDTO) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val resp = RetrofitClientInstance.retrofitService.updateUserProfile(authToken, userProfile)
                Log.d(LOG_TAG, resp.toString())

                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)

            } catch (error: Exception) {
                showToast("Request failed")
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


    //    links: //https://github.com/androidmads/SMSRetrieverApiSample/tree/master/app/src/main/java/com/androidmad/smsretrieverapisample
    //        https://www.c-sharpcorner.com/article/verify-otp-without-sms-permission-in-android-using-kotlin/
//    private fun registerBroadcastListener() {
//        smsBroadCastReceiver = SmsBroadcastReceiver()
//
//        smsBroadCastReceiver.smsBroadCastReceiverListener =
//            object : SmsBroadcastReceiver.SmsBroadCastReceiverListener {
//                override fun onSuccess(intent: Intent?) {
//                    val resultLauncher =
//                        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//
//                            if (result.resultCode == REQ_USER_CONSENT) {
//                                val data: Intent? = result.data
//                                if (result.resultCode == RESULT_OK && data != null) {
//                                    val message =
//                                        data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
//                                    getOtpFromMessage(message)
//                                }
//                            }
//                        }
//
//                    resultLauncher.launch(intent)
////                    link startActivityResult: https://stackoverflow.com/questions/62671106/onactivityresult-method-is-deprecated-what-is-the-alternative
//                }
//
//                override fun onFailure() {
//                    showToast(" SMS RECEIVER Error occured")
//                }
//
//            }
//        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
//        requireActivity().registerReceiver(smsBroadCastReceiver, intentFilter)
//    }

//    private fun startSmartUserConsent() {
//        val client = SmsRetriever.getClient(requireActivity())
//        client.startSmsRetriever()
//    }

//    override fun onStart() {
//        super.onStart()
//        //registerBroadcastListener()
//    }

    private fun getOtpFromMessage(message: String?) {
        val otpPattern = Pattern.compile("(|^)\\d{6}")
        val matcher = otpPattern.matcher(message)
        if (matcher.find()) {
            _binding.pinview.setText(matcher.group(0))
        }
    }


//    override fun onStop() {
//        super.onStop()
//        //requireActivity().unregisterReceiver(smsBroadCastReceiver)
//    }
}