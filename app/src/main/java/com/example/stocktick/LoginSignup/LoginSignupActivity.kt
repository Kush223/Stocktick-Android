package com.example.stocktick.LoginSignup

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.stocktick.R
import com.example.stocktick.databinding.ActivityLoginSignupBinding

//TODO : Change fragment transactions to navgraph format
//WHY is LoginFragment not shown??

class LoginSignupActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityLoginSignupBinding
    private val binding get() = _binding
    private lateinit var mButtonGetStarted: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginSignupBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

//        setContentView(R.layout.activity_login_signup)
//        supportFragmentManager.beginTransaction().add(
//            R.id.login_signup_activity,
//            LoginFragment()
//        ).commit()

        mButtonGetStarted = findViewById(R.id.bt_welcome_get_started)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_login) as NavHostFragment
        val navController = navHostFragment.navController
        mButtonGetStarted.setOnClickListener {
            navController.navigate(R.id.action_navigation_welcome_page_to_navigation_login_otp_)
        }
//        https://stackoverflow.com/questions/63007726/navigation-component-how-to-navigate-from-activity-to-a-fragment
//        and the android documentation link:
//        https://developer.android.com/guide/navigation/navigation-navigate


    }
}